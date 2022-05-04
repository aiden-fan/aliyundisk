package pers.aiden.aliyundisk.module;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description:
 */
public abstract class ToStringModel {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
