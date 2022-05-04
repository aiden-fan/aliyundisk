package pers.aiden.aliyundisk.Handler.mapping;

import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.module.ShellResult;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@Component
public class ShellResultMappingHandler extends DataMappingHandler<ShellResult>{
    @Override
    Class<ShellResult> getItemType() {
        return ShellResult.class;
    }

}
