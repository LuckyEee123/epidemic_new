package com.mai.epidemic.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.mapper.MenuMapper;
import com.mai.epidemic.pojo.Menu;
import com.mai.epidemic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Menu)表服务实现类
 *
 * @author mai
 * @since 2023-01-09 17:05:41
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public Result getMenuList() {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getIsDelete, 0);
        return Result.success(list(wrapper));
    }

    @Override
    public Result addPerm(Menu menu) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getPerms, menu.getPerms());

        Menu one = getOne(wrapper);
        if (ObjectUtil.isNotNull(one)) {
            return Result.error("该权限已存在！");
        }
        return menuMapper.insert(menu) == 1 ? Result.success("添加成功！", menu) : Result.error("添加失败！");

    }

    @Override
    public Result updatePermById(Menu menu) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getIsDelete, 0);
        return update(menu, wrapper) ? Result.success("编辑成功！", menu) : Result.error("编辑失败！");
    }

    @Override
    public Result deletePermById(Integer id) {
        return menuMapper.deleteById(id) == 1 ? Result.success("删除成功！") : Result.error("删除失败！");
    }
}

