package pers.aiden.aliyundisk.module.request;

import pers.aiden.aliyundisk.module.PartInfo;
import pers.aiden.aliyundisk.module.ToStringModel;

import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
public class CreateFolderRequest extends ToStringModel {

    private String name;

    private String drive_id;

    private String parent_file_id;

    private String check_name_mode = "auto_rename";

    private String content_hash;

    private String content_hash_name = "sha1";

    private String proof_code;

    private String proof_version = "v1";

    private List<PartInfo> part_info_list;

    private long size;

    private String type;

    private String pre_hash;

    public String getPre_hash() {
        return pre_hash;
    }

    public void setPre_hash(String pre_hash) {
        this.pre_hash = pre_hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getParent_file_id() {
        return parent_file_id;
    }

    public void setParent_file_id(String parent_file_id) {
        this.parent_file_id = parent_file_id;
    }

    public String getCheck_name_mode() {
        return check_name_mode;
    }

    public void setCheck_name_mode(String check_name_mode) {
        this.check_name_mode = check_name_mode;
    }

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

    public String getProof_code() {
        return proof_code;
    }

    public void setProof_code(String proof_code) {
        this.proof_code = proof_code;
    }

    public String getProof_version() {
        return proof_version;
    }

    public void setProof_version(String proof_version) {
        this.proof_version = proof_version;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PartInfo> getPart_info_list() {
        return part_info_list;
    }

    public void setPart_info_list(List<PartInfo> part_info_list) {
        this.part_info_list = part_info_list;
    }
}
