package pers.aiden.aliyundisk.module.response;

import pers.aiden.aliyundisk.module.ToStringModel;

import java.util.Date;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description:
 */
public class GetDownLoadUrlResponse extends ToStringModel {

    private String content_hash;
    private String content_hash_name;
    private String crc64_hash;
    private Date expiration;
    private String internal_url;
    private String method;
    private String size;
    private String url;

    public String getContent_hash() {
        return content_hash;
    }

    public void setContent_hash(String content_hash) {
        this.content_hash = content_hash;
    }

    public String getContent_hash_name() {
        return content_hash_name;
    }

    public void setContent_hash_name(String content_hash_name) {
        this.content_hash_name = content_hash_name;
    }

    public String getCrc64_hash() {
        return crc64_hash;
    }

    public void setCrc64_hash(String crc64_hash) {
        this.crc64_hash = crc64_hash;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getInternal_url() {
        return internal_url;
    }

    public void setInternal_url(String internal_url) {
        this.internal_url = internal_url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
