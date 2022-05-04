package pers.aiden.aliyundisk.Handler.mapping;

import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.module.TaskDetail;
import pers.aiden.aliyundisk.module.response.FileInfo;

/**
 * @Author: 范淼
 * @Date: 2022-05-04
 * @Description:
 */
@Component
public class TaskDetailHandler  extends DataMappingHandler<TaskDetail>{
    @Override
    Class<TaskDetail> getItemType() {
        return TaskDetail.class;
    }
}
