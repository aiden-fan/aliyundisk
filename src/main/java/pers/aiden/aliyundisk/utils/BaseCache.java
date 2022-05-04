package pers.aiden.aliyundisk.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.manager.LoginManger;
import pers.aiden.aliyundisk.module.TaskDetail;
import pers.aiden.aliyundisk.module.response.FileInfo;
import pers.aiden.aliyundisk.module.response.UserInfo;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@Component
public class BaseCache {

    @Autowired
    private LoginManger loginManger;

    @Value("${aliyun.disk.refresh.token}")
    private String refreshToken;

    private static final Map<String, String> userCache = new ConcurrentHashMap<>(16);

    public static final Map<String, TaskDetail> taskCache = new ConcurrentHashMap<>(32);

    /**
     * 缓存目录结构 todo
     */
    private static final Map<String, FileInfo> fileCache = new ConcurrentHashMap<>(128);

    @PostConstruct
    public void init() throws Exception {
        initUserCache();
    }

    public void initUserCache() {
        UserInfo userInfo = loginManger.refreshToken(refreshToken);
        userCache.put("driveId",userInfo.getDefault_drive_id());
        userCache.put("accessToken",userInfo.getAccess_token());
        userCache.put("currentFolderId","root");
    }

    public void addFileInfo(List<FileInfo>fileInfos) {
        for (FileInfo fileInfo : fileInfos) {

        }
    }

    public static void updateFolder(String currentFolderId) {
        userCache.put("currentFolderId",currentFolderId);
    }

    public static String getUserCache(String fileId) {
        return userCache.get(fileId);
    }

    public static synchronized boolean addTaskDetail( TaskDetail taskDetail) {
        TaskDetail cacheDetail = getTaskDetail(taskDetail.getFileId());
        if (cacheDetail != null && cacheDetail.getStatus() != 0) {
            //此任务已提交过
            return false;
        }
        taskCache.put(taskDetail.getFileId(),taskDetail);
        return true;
    }
    public static TaskDetail getTaskDetail(String fileId) {
        return taskCache.get(fileId);
    }
}
