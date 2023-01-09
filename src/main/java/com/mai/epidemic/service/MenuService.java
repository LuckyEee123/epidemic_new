package com.mai.epidemic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mai.epidemic.commons.Result;
import com.mai.epidemic.pojo.Menu;


/**
 * (Menu)表服务接口
 *
 * @author mai
 * @since 2023-01-09 17:05:41
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取权限菜单列表
     * @return
     */
    Result getMenuList();

    /**
     * 添加权限菜单
     * @return
     */
    Result addPerm(Menu menu);

    /**
     * 编辑权限菜单
     * @param menu
     * @return
     */
    Result updatePermById(Menu menu);

    /**
     * 删除权限
     * @param id
     * @return
     */
    Result deletePermById(Integer id);
}

