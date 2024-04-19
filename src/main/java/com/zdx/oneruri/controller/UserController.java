package com.zdx.oneruri.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zdx.oneruri.entity.User;
import com.zdx.oneruri.service.UserService;
import com.zdx.oneruri.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Tag(name = "用户CRUD")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        System.out.println(user);
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return Result.error("403","用户名或密码不能为空");
        }
        int n= userService.loginService(user);
        if(n==1){
            return Result.success(user,"登录成功");
        }else{
            return Result.error("403","用户名或密码错误");
        }
    }
    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user){
        //注册默认权限为0，默认状态为正常
        user.setStatus("正常");
        user.setPower(0);
        LocalDateTime now = LocalDateTime.now();
        user.setRegDate(now);
        System.out.println(user);
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return Result.error("403","用户名或密码不能为空");
        }
        int n = userService.registerService(user);
        if(n==1){
            return Result.success(user,"注册成功");
        }else{
            return Result.error("403","用户名已存在");
        }

    }
    @Operation(summary = "修改密码")
    @PostMapping("/updatePassword")
    public Result<User> updatePassword(@RequestBody User user){
        System.out.println(user);
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return Result.error("403","用户名或密码不能为空");
        }
        User user1 = userService.getById(user.getUid());
        System.out.println(user1);
        user1.setPassword(user.getPassword());
        if (userService.updateById(user1)){
            return Result.success(user1,"修改成功");
        }else{
            return Result.error("403","修改失败");
        }
    }
    @Operation(summary = "删除用户")
    @PostMapping("/deleteUser")
    public Result<User> deleteUser(@RequestBody User user){
        User user1 = userService.getById(user.getUid());
        System.out.println(user1);
        if(userService.removeById(user1)){
            return Result.success(user1,"删除成功");
        }else{
            return Result.error("403","删除失败");
        }
    }
    @Operation(summary = "修改用户信息")
    @PostMapping("/updateUser")
    public Result<User> updateUser(@RequestBody User user){
        User user1 = userService.getById(user.getUid());
        user1.setUsername(user.getUsername());
        if(userService.updateById(user1))
            return Result.success(user1,"修改成功");
        return Result.error("403","修改失败");
    }
    @Operation(summary = "查询用户信息")
    @PostMapping("/queryUser")
    public Result<User> queryUser(@RequestBody User user){
        if(user.getUid() != null && user.getUsername() != null){
            return Result.success(userService.getById(user.getUid()),"查询成功");
        }
        if (user.getUid() != null){
            return Result.success(userService.getById(user.getUid()),"查询成功");
        }
        if(user.getUsername() != null){
            return Result.success(userService.getOne(user.getUsername()),"查询成功");
        }
        return Result.error("403","用户不存在");
    }
}

