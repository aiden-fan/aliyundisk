package pers.aiden.aliyundisk.Handler.mapping;

import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.module.response.FileInfo;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@Component
public class FileInfoMappingHandler extends DataMappingHandler<FileInfo>{
    @Override
    Class<FileInfo> getItemType() {
        return FileInfo.class;
    }
}
