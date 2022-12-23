package com.mai.epidemic.commons.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

@Component
public class IDUtil {

    public Integer FastUid() {
        return Math.abs(IdUtil.fastSimpleUUID().hashCode());
    }

}
