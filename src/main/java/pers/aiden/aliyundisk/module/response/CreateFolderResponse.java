package pers.aiden.aliyundisk.module.response;

import pers.aiden.aliyundisk.module.PartInfo;
import pers.aiden.aliyundisk.module.ToStringModel;

import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
public class CreateFolderResponse extends ToStringModel {

    private String file_id;

    private String file_name;

    private String domain_id;

    private String drive_id;

    private String encrypt_mode;

    private String location;

    private String parent_file_id;

    private List<PartInfo> part_info_list;

    /**
     * 为true表示快速上传成功
     */
    private boolean rapid_upload;

    /**
     * file/folder
     */
    private String type;

    private String upload_id;

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getEncrypt_mode() {
        return encrypt_mode;
    }

    public void setEncrypt_mode(String encrypt_mode) {
        this.encrypt_mode = encrypt_mode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent_file_id() {
        return parent_file_id;
    }

    public void setParent_file_id(String parent_file_id) {
        this.parent_file_id = parent_file_id;
    }

    public boolean getRapid_upload() {
        return rapid_upload;
    }

    public void setRapid_upload(boolean rapid_upload) {
        this.rapid_upload = rapid_upload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }


    public List<PartInfo> getPart_info_list() {
        return part_info_list;
    }

    public void setPart_info_list(List<PartInfo> part_info_list) {
        this.part_info_list = part_info_list;
    }
}
