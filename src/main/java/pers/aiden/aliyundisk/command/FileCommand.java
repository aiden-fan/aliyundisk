package pers.aiden.aliyundisk.command;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.aiden.aliyundisk.manager.FileManger;
import pers.aiden.aliyundisk.module.ShellResult;
import pers.aiden.aliyundisk.module.TaskDetail;
import pers.aiden.aliyundisk.module.response.FileInfo;
import pers.aiden.aliyundisk.module.response.GetDownLoadUrlResponse;
import pers.aiden.aliyundisk.utils.BaseCache;
import pers.aiden.aliyundisk.utils.ThreadPoolUtil;

import java.util.Date;
import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@ShellComponent
public class FileCommand {

    @Autowired
    private FileManger fileManger;

    @ShellMethod(key = {"listFile", "lf", "ll"}, value = "查询目录详情", group = "文件操作")
    public ShellResult<List<FileInfo>> listFile(
            @ShellOption(defaultValue = "root") String parentFileId,
            @ShellOption(defaultValue = "*") String fields
    ) {
        List<FileInfo> fileInfos = fileManger.listFiles(parentFileId, fields);
        return new ShellResult<>(fileInfos, "success", 200);
    }

    @ShellMethod(key = {"downloadFile", "df"}, value = "下载文件,异步", group = "文件操作")
    public ShellResult<String> downLoadFile(
            @ShellOption String fileId,
            @ShellOption String fileName,
            @ShellOption(defaultValue = ShellOption.NULL) String localPath
    ) {
        ThreadPoolUtil.threadPoolExecutor.execute(() -> {
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setFileId(fileId);
            taskDetail.setStatus(1);
            taskDetail.setName(fileName);
            taskDetail.setThread(Thread.currentThread());
            Date startDate = new Date();
            taskDetail.setMsg(startDate.toString() + " 开始下载任务;");
            boolean b = BaseCache.addTaskDetail(taskDetail);
            if (!b) {
                taskDetail.setMsg(taskDetail.getMsg() + "重复下载");
                return;
            }
            try {
                GetDownLoadUrlResponse downloadUrl = fileManger.getDownloadUrl(fileId);
                fileManger.downloadFile(downloadUrl.getUrl(), localPath, fileName,taskDetail, true);
                Date endDate = new Date();
                taskDetail.setStatus(2);
                taskDetail.setMsg(taskDetail.getMsg() + endDate.toString() + "下载结束;");
            } catch (Exception e) {
                Date endDate = new Date();
                taskDetail.setStatus(0);
                taskDetail.setMsg(taskDetail.getMsg() + endDate.toString() + "下载失败：" + e.getMessage());
            }
        });
        return new ShellResult<>("下载任务开始,查询结果输入 tr " + fileId,"",200);

    }

    @ShellMethod(value = "查询,type为match模糊查询,type为=精确查询", key = {"searchFile", "sf"}, group = "文件操作")
    public ShellResult<List<FileInfo>> searchFile(
            @ShellOption String name,
            @ShellOption(defaultValue = "match") String type,
            @ShellOption(defaultValue = ShellOption.NULL) String category,
            @ShellOption(defaultValue = ShellOption.NULL) String parentFileId
    ) {
        String query = StringUtils.EMPTY;
        if (StringUtils.isBlank(category)) {
            query = String.format("name %s \"%s\"", type, name);
        } else {
            query = String.format("name %s \"%s\" and category = \"%s\" ",type, name, category);
        }
        if (!StringUtils.isBlank(parentFileId)) {
            query = String.format("parent_file_id = \"%s\" and (name = \"%s\")", parentFileId, name);
        }
        List<FileInfo> fileInfos = fileManger.matchFile(query);
        return new ShellResult<>(fileInfos, "success", 200);
    }

    @ShellMethod(value = "上传文件", key = {"uploadFile", "uf"}, group = "文件操作")
    public ShellResult<Boolean> uploadFile(
            @ShellOption String localPath,
            @ShellOption(defaultValue = "root") String parentFileId
    ) {
        return fileManger.uploadFile(localPath, parentFileId);
    }

    @ShellMethod(value = "移到回收站", key = {"recycle", "rf"}, group = "文件操作")
    public ShellResult<Boolean> recycleFile(
            @ShellOption String fileId
    ) {
        return fileManger.recycleFile(fileId);
    }

    @ShellMethod(value = "新建文件夹", key = {"createFolders", "cf"}, group = "文件操作")
    public ShellResult<Boolean> createFolders(
            @ShellOption String name,
            @ShellOption(defaultValue = "root") String parentFileId
    ) {
        return fileManger.createFolder(parentFileId, name);
    }

    @ShellMethod(value = "todo", key = "cd")
    public ShellResult<Boolean> checkFolder(
            @ShellOption(defaultValue = "root") String folderName
    ) {
//        未找到精确查询文件名的方法
        return null;
    }

}
