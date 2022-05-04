package pers.aiden.aliyundisk.module.request;

import pers.aiden.aliyundisk.module.ToStringModel;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description: 搜索
 */
public class SearchFileRequest extends ToStringModel {

    private String drive_id;

    private String image_thumbnail_process = "image/resize,w_1920/format,jpeg";

    private int limit = 100;

    private String order_by = "updated_at DESC";

    /**
     * name match \"test\"   文件名匹配
     * name match \"test\" and category = \"image\"   指定类型文件名匹配
     * video,image,folder,doc,audio
     */
    private String query ;

    private String video_thumbnail_process = "video/snapshot,t_1000,f_jpg,ar_auto,w_300";


    public String getDrive_id() {
        return drive_id;
    }

    public void setDrive_id(String drive_id) {
        this.drive_id = drive_id;
    }

    public String getImage_thumbnail_process() {
        return image_thumbnail_process;
    }

    public void setImage_thumbnail_process(String image_thumbnail_process) {
        this.image_thumbnail_process = image_thumbnail_process;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getVideo_thumbnail_process() {
        return video_thumbnail_process;
    }

    public void setVideo_thumbnail_process(String video_thumbnail_process) {
        this.video_thumbnail_process = video_thumbnail_process;
    }
}
