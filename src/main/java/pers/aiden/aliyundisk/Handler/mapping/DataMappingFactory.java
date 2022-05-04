package pers.aiden.aliyundisk.Handler.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 范淼
 * @Date: 2022-05-01
 * @Description:
 */
@Component
public class DataMappingFactory {

    @Autowired
    private List<DataMappingHandler> dataMappingHandler;

    public DataMappingHandler getDataMappingHandler(Class clazz) {
        for (DataMappingHandler mappingHandler : dataMappingHandler) {
            if (mappingHandler.support(clazz)) {
                return mappingHandler;
            }
        }
        return null;
    }
}
