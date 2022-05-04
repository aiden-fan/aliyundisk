package pers.aiden.aliyundisk.Handler.mapping;


import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.module.ShellResult;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public abstract class DataMappingHandler<T> {
    protected LinkedHashMap<String, Object> headers = new LinkedHashMap<>();

    abstract Class<T> getItemType();

    public boolean support(Class<T> clazz) {
        return getItemType().equals(clazz);
    }

    @PostConstruct
    public void init() {
        Field[] fields = getItemType().getDeclaredFields();
        for (Field field : fields) {
            headers.put(field.getName(),field.getName());
        }
    }

    public LinkedHashMap<String, Object> getHeaders() {
        return headers;
    }

}
