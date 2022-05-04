package pers.aiden.aliyundisk.module;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: Http请求的响应结果封装类
 */
public class HttpResult extends ToStringModel{

    /**
     * 响应的Header信息
     */
    private Header[] headers;

    /**
     * 响应的Cookie信息
     */
    private List<Cookie> cookies;

    /**
     * 响应状态码
     */
    private int statusCode;

    /**
     * 响应内容的类型
     */
    private String contentType;

    /**
     * 响应的内容是否是文本类型
     */
    private boolean isTextType;

    /**
     * 响应的内容（字符串形式）
     */
    private String data;

    /**
     * 响应的内容（字节数组形式）
     */
    private byte[] byteArrayContent;

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isTextType() {
        return isTextType;
    }

    public void setTextType(boolean textType) {
        isTextType = textType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte[] getByteArrayContent() {
        return byteArrayContent;
    }

    public void setByteArrayContent(byte[] byteArrayContent) {
        this.byteArrayContent = byteArrayContent;
    }
}

