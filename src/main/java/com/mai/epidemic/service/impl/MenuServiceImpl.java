package com.mai.epidemic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.mapper.MenuMapper;
import com.mai.epidemic.pojo.Menu;
import com.mai.epidemic.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * (Menu)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 17:05:41
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public Result getMenuList() {
        return Result.success(list());
    }
}

