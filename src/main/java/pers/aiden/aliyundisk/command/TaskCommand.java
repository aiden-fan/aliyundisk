package pers.aiden.aliyundisk.command;

import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import pers.aiden.aliyundisk.module.ShellResult;
import pers.aiden.aliyundisk.module.TaskDetail;
import pers.aiden.aliyundisk.utils.BaseCache;
import pers.aiden.aliyundisk.utils.ThreadPoolUtil;

/**
 * @Author: 范淼
 * @Date: 2022-05-04
 * @Description:
 */
@ShellComponent
public class TaskCommand {

    @ShellMethod(value = "查询下载结果", key = {"taskResult", "tr"}, group = "文件操作")
    public ShellResult<TaskDetail> taskResult(
            @ShellOption String fileId
    ) {
        return new ShellResult<>(BaseCache.getTaskDetail(fileId),"status 1 下载中  2 下载成功  0 下载失败",200);
    }


    @ShellMethod(value = "终止下载", key = {"kill"})
    public ShellResult<Boolean> killTask(
            @ShellOption(defaultValue = ShellOption.NULL) String fileId
    ) {
        if (StringUtils.isBlank(fileId)) {
            ThreadPoolUtil.threadPoolExecutor.shutdownNow();

            throw new ExitRequest();
        }
        TaskDetail detail = BaseCache.getTaskDetail(fileId);
        if (detail != null && detail.getStatus() == 1 && detail.getThread() != null) {
            detail.getThread().interrupt();
        }
        return new ShellResult<>(true,"终止下载，name=" + detail.getName(),200);
    }
}
