package pers.aiden.aliyundisk.manager;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pers.aiden.aliyundisk.module.HttpResult;
import pers.aiden.aliyundisk.module.PartInfo;
import pers.aiden.aliyundisk.module.ShellResult;
import pers.aiden.aliyundisk.module.TaskDetail;
import pers.aiden.aliyundisk.module.request.*;
import pers.aiden.aliyundisk.module.response.CreateFolderResponse;
import pers.aiden.aliyundisk.module.response.FileInfo;
import pers.aiden.aliyundisk.module.response.GetDownLoadUrlResponse;
import pers.aiden.aliyundisk.utils.BaseCache;
import pers.aiden.aliyundisk.utils.CodeUtil;
import pers.aiden.aliyundisk.utils.HttpClientUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description:
 */
@Service
public class FileManger {

    @Value("${aliyun.disk.api.host}")
    private String apiHost;

    @Value("${aliyun.disk.file.list.path}")
    private String listFilePath;

    @Value("${aliyun.disk.get.download.url.path}")
    private String downloadPath;

    @Value("${aliyun.disk.search.path}")
    private String searchPath;

    @Value("${aliyun.disk.upload.folder}")
    public String uploadPath;

    @Value("${aliyun.disk.upload.complete}")
    public String completePath;

    @Value("${aiiyun.disk.recycle.file}")
    private String recyclePath;

    public List<FileInfo> listFiles(String parentFileId,String fields) {
        String url = apiHost + listFilePath;
        FileListRequest request = new FileListRequest();
        request.setDrive_id(BaseCache.getUserCache("driveId"));
        request.setParent_file_id(parentFileId);
        request.setFields(fields);

        HttpResult result = HttpClientUtil.sendPostJson(url, JSONObject.toJSONString(request),
                getTokenHeaders(), null);
        if (result.getStatusCode() != 200) {
            throw new RuntimeException("list file error:" + result.getErrorMsg());
        }
        return JSONObject.parseArray(JSONObject.parseObject(result.getData()).getString("items"),
                FileInfo.class);
    }

    public GetDownLoadUrlResponse getDownloadUrl(String fileId) {
        String url = apiHost + downloadPath;
        GetDownloadUrlRequest request = new GetDownloadUrlRequest();
        request.setDrive_id(BaseCache.getUserCache("driveId"));
        request.setFile_id(fileId);

        HttpResult httpResult = HttpClientUtil.sendPostJson(url,
                JSONObject.toJSONString(request), getTokenHeaders(), null);
        if (httpResult.getStatusCode() != 200) {
            throw new RuntimeException("get download url error:" + httpResult.getErrorMsg());
        }
        return JSONObject.parseObject(httpResult.getData(), GetDownLoadUrlResponse.class);
    }

    public ShellResult<String> downloadFile(String url, String localPath, String filename, TaskDetail taskDetail, boolean whetherCover) {
        BasicHeader header = new BasicHeader("referer", "https://www.aliyundrive.com/");
        Header[] headers = new Header[] {header};
        if (StringUtils.isBlank(localPath)) {
            localPath = "aliyunDw/";
        }
        File file = new File(localPath,filename);
        // mkdirs会判断是否已存在
        file.getParentFile().mkdirs();
        if (!whetherCover && file.exists()) {
            return new ShellResult<>(file.getAbsolutePath(),"file is exists",501);
        }
        HttpClientUtil.download(url,file,headers,taskDetail);
        return new ShellResult<>(file.getAbsolutePath(),"success",200);
    }

    public List<FileInfo> matchFile(String query) {
        String url = apiHost + searchPath;
        SearchFileRequest request = new SearchFileRequest();
        request.setDrive_id(BaseCache.getUserCache("driveId"));
        request.setQuery(query);
        HttpResult httpResult = HttpClientUtil.sendPostJson(url,
                JSONObject.toJSONString(request), getTokenHeaders(), null);
        return JSONObject.parseArray(JSONObject.parseObject(httpResult.getData()).getString("items"),
                FileInfo.class);
    }

    private Header[] getTokenHeaders() {
        Header header = new BasicHeader("Authorization",
                "Bearer " + BaseCache.getUserCache("accessToken"));
        Header[] headers = new Header[] {header};
        return headers;
    }

    private CreateFolderResponse createFile(String name, String parentFileId, String contentHash,
                                            String type, long size, String proofCode) {
        String url = apiHost + uploadPath;
        CreateFolderRequest request = new CreateFolderRequest();
        request.setDrive_id(BaseCache.getUserCache("driveId"));
        request.setName(name);
        request.setContent_hash(contentHash);
        request.setParent_file_id(parentFileId);
        request.setContent_hash_name("sha1");
        List<PartInfo> partInfos = new ArrayList<>();
        PartInfo partInfo = new PartInfo();
        //暂时不支持分块上传 todo
        partInfo.setPart_number(1);
        partInfos.add(partInfo);
        request.setPart_info_list(partInfos);
        request.setProof_code(proofCode);
        request.setProof_version("v1");
        request.setSize(size);
        request.setType(type);
        HttpResult result = HttpClientUtil.sendPostJson(url, JSONObject.toJSONString(request),
                getTokenHeaders(), null);
        CreateFolderResponse response = JSONObject.parseObject(result.getData(), CreateFolderResponse.class);
        return response;
    }

    /**
     * 暂时不支持分片上传 todo
     * @return
     */
    public ShellResult<Boolean> uploadFile(String localPath, String parentId) {
        File file = new File(localPath);
        if (!file.exists()) {
            return new ShellResult<>(false,localPath + "不存在",401);
        }
        String proofCode = CodeUtil.proofCode(BaseCache.getUserCache("accessToken"),file);
        String contentHash = CodeUtil.contentCode(file);
        CreateFolderResponse response = createFile(file.getName(), parentId, contentHash, "file", file.length(), proofCode);
        if (response.getRapid_upload()) {
            return new ShellResult<>(true,"快速上传成功",200);
        }
        List<PartInfo> partInfos = response.getPart_info_list();
        for (PartInfo partInfo : partInfos) {
            String uploadUrl = partInfo.getUpload_url();
            BasicHeader header = new BasicHeader("referer", "https://www.aliyundrive.com/");
            Header[] headers = new Header[] {header};
            HttpClientUtil.uploadFile(uploadUrl,file,headers);
        }
        CompleteRequest completeRequest = new CompleteRequest(response.getDrive_id(),
                response.getFile_id(), response.getUpload_id());
        String completeUrl = apiHost + completePath;
        HttpResult result = HttpClientUtil.sendPostJson(completeUrl, JSONObject.toJSONString(completeRequest), getTokenHeaders(), null);
        return new ShellResult<>(true,result.getData(),200);
    }

    public ShellResult<Boolean> recycleFile(String fileId) {
        String url = apiHost + recyclePath;
        RecycleFileRequest request = new RecycleFileRequest(BaseCache.getUserCache("driveId"), fileId);
        HttpResult result = HttpClientUtil.sendPostJson(url, JSONObject.toJSONString(request), getTokenHeaders(), null);
        if (result.getStatusCode() == 202) {
            return new ShellResult<>(true,"recycle success",200);
        }
        return new ShellResult<>(false,result.getErrorMsg(),500);
    }

    public ShellResult<Boolean> createFolder(String parentFileId, String name) {
        String url = apiHost + uploadPath;
        CreateFolderRequest request = new CreateFolderRequest();
        request.setType("folder");
        request.setName(name);
        request.setDrive_id(BaseCache.getUserCache("driveId"));
        request.setCheck_name_mode("refuse");
        request.setParent_file_id(parentFileId);
        HttpResult result = HttpClientUtil.sendPostJson(url, JSONObject.toJSONString(request), getTokenHeaders(), null);
        if (result.getStatusCode() == 201) {
            return new ShellResult<>(true,"create success",200);
        }
        return new ShellResult<>(false,result.getErrorMsg(),500);
    }
}
