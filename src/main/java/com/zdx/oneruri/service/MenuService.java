package com.zdx.oneruri.service;

import com.zdx.oneruri.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getMenu();

    List<Menu> getMenuByPower(Integer power);
}
