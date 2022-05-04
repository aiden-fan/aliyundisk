package pers.aiden.aliyundisk.module;

/**
 * @Author: 范淼
 * @Date: 2022-05-04
 * @Description: 异步任务状态
 */
public class TaskDetail extends ToStringModel {

    private String msg;

    /**
     * 1 下载中  2 下载成功  0 下载失败 3 interrupted
     */
    private int status;

    private String fileId;

    private String name;

    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
