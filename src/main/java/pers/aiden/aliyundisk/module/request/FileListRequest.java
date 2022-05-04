package pers.aiden.aliyundisk.module.request;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: 查询目录详情
 */
public class FileListRequest {

    private boolean all = false;

    private String drive_id;

    /*
        '*',  # 全部字段
        'thumbnail',  # 缩略图
     */
    private String fields = "*";

    private String image_thumbnail_process = "image_thumbnail_process";

    private String image_url_process = "image/resize,w_1920/format,jpeg";

    private int limit = 100;

    private String order_by = "updated_at";

    private String order_direction = "DESC";

    /**
     * 默认根目录
     */
    private String parent_file_id = "root";

    private int url_expire_sec = 1600;

    private String video_thumbnail_process = "video/snapshot,t_1000,f_jpg,ar_auto,w_300";


    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getImage_thumbnail_process() {
        return image_thumbnail_process;
    }

    public void setImage_thumbnail_process(String image_thumbnail_process) {
        this.image_thumbnail_process = image_thumbnail_process;
    }

    public String getImage_url_process() {
        return image_url_process;
    }

    public void setImage_url_process(String image_url_process) {
        this.image_url_process = image_url_process;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getOrder_direction() {
        return order_direction;
    }

    public void setOrder_direction(String order_direction) {
        this.order_direction = order_direction;
    }

    public String getParent_file_id() {
        return parent_file_id;
    }

    public void setParent_file_id(String parent_file_id) {
        this.parent_file_id = parent_file_id;
    }

    public int getUrl_expire_sec() {
        return url_expire_sec;
    }

    public void setUrl_expire_sec(int url_expire_sec) {
        this.url_expire_sec = url_expire_sec;
    }

    public String getVideo_thumbnail_process() {
        return video_thumbnail_process;
    }

    public void setVideo_thumbnail_process(String video_thumbnail_process) {
        this.video_thumbnail_process = video_thumbnail_process;
    }
}
