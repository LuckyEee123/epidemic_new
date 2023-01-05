package com.mai.epidemic.commons.utils;

import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IDUtil {

    public static Integer FastUid() {
        return Math.abs(IdUtil.fastSimpleUUID().hashCode());
    }

}
