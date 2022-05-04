package pers.aiden.aliyundisk.module.request;

import pers.aiden.aliyundisk.module.ToStringModel;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: 查询下载地址
 */
public class GetDownloadUrlRequest extends ToStringModel {

    private String drive_id;

    private String file_id;

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }
}
