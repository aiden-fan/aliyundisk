package pers.aiden.aliyundisk.module.request;

import pers.aiden.aliyundisk.module.ToStringModel;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
public class RefreshTokeRequest extends ToStringModel {

    private String refresh_token;

    private String grant_type = "refresh_token";

    public RefreshTokeRequest(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
