package com.zdx.oneruri.service;

import com.zdx.oneruri.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
public interface UserService extends IService<User> {

    int loginService(User user);

    int registerService(User user);


    User getOne(String username);
}
