package pers.aiden.aliyundisk.module;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
public class PartInfo extends ToStringModel{
    private String content_type;

    private String internal_upload_url;

    private int part_number;

    private String upload_url;


    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getInternal_upload_url() {
        return internal_upload_url;
    }

    public void setInternal_upload_url(String internal_upload_url) {
        this.internal_upload_url = internal_upload_url;
    }

    public int getPart_number() {
        return part_number;
    }

    public void setPart_number(int part_number) {
        this.part_number = part_number;
    }

    public String getUpload_url() {
        return upload_url;
    }

    public void setUpload_url(String upload_url) {
        this.upload_url = upload_url;
    }
}
