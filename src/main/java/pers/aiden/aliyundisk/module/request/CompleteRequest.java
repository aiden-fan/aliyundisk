package pers.aiden.aliyundisk.module.request;

/**
 * @Author: 范淼
 * @Date: 2022-05-03
 * @Description: 文件上传完成后调用
 */
public class CompleteRequest {

    private String drive_id;

    private String file_id;

    private String upload_id;

    public CompleteRequest(String drive_id, String file_id, String upload_id) {
        this.drive_id = drive_id;
        this.file_id = file_id;
        this.upload_id = upload_id;
    }

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

    public String getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }
}
