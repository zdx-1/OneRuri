package com.zdx.oneruri.controller;


import com.zdx.oneruri.entity.User;
import com.zdx.oneruri.service.MenuService;
import com.zdx.oneruri.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Tag(name = "菜单CRUD")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Operation(summary = "获取所有菜单")
    @PostMapping("/getMenu")
    public Result getMenu(){
        return Result.success(menuService.getMenu());
    }

    @Operation(summary = "获取菜单通过权限")
    @PostMapping("/getMenuByPower")
    public Result getMenuByPower(@RequestBody User user){
        return Result.success(menuService.getMenuByPower(user.getPower()));
    }

}

