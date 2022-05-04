package pers.aiden.aliyundisk.module.enums;

/**
 * @Author: 范淼
 * @Date: 2022-04-27
 * @Description: 配置实体 todo
 */

public enum BaseConfigEnum {
    ALIYUN_DISK_API_HOST("aliyun.disk.api.host","api地址"),
    ALIYUN_DISK_TOKEN_LOGIN_PATH("aliyun.disk.token.login.path","刷新token"),
    ALIYUN_DISK_FILE_LIST_PATH("aliyun.disk.file.list.path","目录")
    ;
    private final String configKey;

    private final String msg;


    BaseConfigEnum(String configKey,String msg) {
        this.configKey = configKey;
        this.msg = msg;
    }

    public String getConfigKey() {
        return configKey;
    }

    public String getMsg() {
        return msg;
    }
}
