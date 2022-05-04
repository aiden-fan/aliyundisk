package pers.aiden.aliyundisk.manager;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.aiden.aliyundisk.config.AppPropertiesService;
import pers.aiden.aliyundisk.module.enums.BaseConfigEnum;
import pers.aiden.aliyundisk.module.HttpResult;
import pers.aiden.aliyundisk.module.request.RefreshTokeRequest;
import pers.aiden.aliyundisk.module.response.UserInfo;
import pers.aiden.aliyundisk.utils.HttpClientUtil;


/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: 登录
 */
@Component
public class LoginManger {

    @Autowired
    private AppPropertiesService appPropertiesService;


    public UserInfo refreshToken(String refreshToke) {
        String url = appPropertiesService.getConfig(BaseConfigEnum.ALIYUN_DISK_API_HOST) + appPropertiesService.getConfig(BaseConfigEnum.ALIYUN_DISK_TOKEN_LOGIN_PATH);
        RefreshTokeRequest request = new RefreshTokeRequest(refreshToke);
        HttpResult result = HttpClientUtil.sendPostJson(url, JSONObject.toJSONString(request), null, null);
        if (result.getStatusCode() != 200) {
            throw new RuntimeException("refresh token error:" + result.getErrorMsg());
        }
        return JSONObject.parseObject(result.getData(),UserInfo.class);
    }


}
