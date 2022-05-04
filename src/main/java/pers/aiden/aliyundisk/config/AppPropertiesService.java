package pers.aiden.aliyundisk.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.module.enums.BaseConfigEnum;

/**
 * @Author: 范淼
 * @Date: 2022-04-27
 * @Description: 配置文件中加载
 */
@Component
public class AppPropertiesService implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getConfig(BaseConfigEnum baseConfigEnum) {
        return environment.getProperty(baseConfigEnum.getConfigKey());
    }
}
