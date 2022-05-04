package pers.aiden.aliyundisk.module.response;

import pers.aiden.aliyundisk.module.ToStringModel;

import java.util.Date;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: listFile返回的数据结构
 */
public class FileInfo extends ToStringModel {

    private String name;

    private String file_id;



    /**
     *  folder/file
     */
    private String type;

    private Date created_at;

    private Date updated_at;

    private boolean hidden;

    private boolean starred;

    private String drive_id;

    private String domain_id;
    /**
     * "available"
     */
    private String status;

    /**
     *  {\"client\":\"desktop\"}
     */
    private String user_meta;

    /**
     * 根目录root
     */
    private String parent_file_id;

    /**
     * 是否加密 none
     */
    private String encrypt_mode;

    /**
     *  User
     */
    private String creator_type;

    private String creator_id;

    private String creator_name;

    private String last_modifier_type;

    private String last_modifier_id;

    private String last_modifier_name;

    /**
     * 修订号
     */
    private String revision_id;

    private String download_url;

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_meta() {
        return user_meta;
    }

    public void setUser_meta(String user_meta) {
        this.user_meta = user_meta;
    }

    public String getParent_file_id() {
        return parent_file_id;
    }

    public void setParent_file_id(String parent_file_id) {
        this.parent_file_id = parent_file_id;
    }

    public String getEncrypt_mode() {
        return encrypt_mode;
    }

    public void setEncrypt_mode(String encrypt_mode) {
        this.encrypt_mode = encrypt_mode;
    }

    public String getCreator_type() {
        return creator_type;
    }

    public void setCreator_type(String creator_type) {
        this.creator_type = creator_type;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getLast_modifier_type() {
        return last_modifier_type;
    }

    public void setLast_modifier_type(String last_modifier_type) {
        this.last_modifier_type = last_modifier_type;
    }

    public String getLast_modifier_id() {
        return last_modifier_id;
    }

    public void setLast_modifier_id(String last_modifier_id) {
        this.last_modifier_id = last_modifier_id;
    }

    public String getLast_modifier_name() {
        return last_modifier_name;
    }

    public void setLast_modifier_name(String last_modifier_name) {
        this.last_modifier_name = last_modifier_name;
    }

    public String getRevision_id() {
        return revision_id;
    }

    public void setRevision_id(String revision_id) {
        this.revision_id = revision_id;
    }
}
