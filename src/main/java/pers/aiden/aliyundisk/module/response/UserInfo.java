package pers.aiden.aliyundisk.module.response;

import pers.aiden.aliyundisk.module.ToStringModel;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: token登录返回的数据结构
 */
public class UserInfo extends ToStringModel {

    private String access_token;

    private String refresh_token;

    private String expires_in;

    private String token_type;

    private String user_id;

    private String user_name;

    private String nick_name;

    private String default_drive_id;

    private String default_sbox_drive_id;


    private String role;

    private String status;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getDefault_drive_id() {
        return default_drive_id;
    }

    public void setDefault_drive_id(String default_drive_id) {
        this.default_drive_id = default_drive_id;
    }

    public String getDefault_sbox_drive_id() {
        return default_sbox_drive_id;
    }

    public void setDefault_sbox_drive_id(String default_sbox_drive_id) {
        this.default_sbox_drive_id = default_sbox_drive_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
