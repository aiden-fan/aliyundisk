package pers.aiden.aliyundisk.module;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
public class ShellResult<T> extends ToStringModel{

    private T data;

    private String reqMsg;

    private int reqCode;

    public ShellResult(T data, String reqMsg, int reqCode) {
        this.data = data;
        this.reqMsg = reqMsg;
        this.reqCode = reqCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public int getReqCode() {
        return reqCode;
    }

    public void setReqCode(int reqCode) {
        this.reqCode = reqCode;
    }
}
