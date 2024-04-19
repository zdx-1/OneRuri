package com.zdx.oneruri.service.impl;

import com.zdx.oneruri.entity.User;
import com.zdx.oneruri.mapper.UserMapper;
import com.zdx.oneruri.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int loginService(User user) {
        User user1 = userMapper.findUserByUsernameAndPassword(user);
        if (user1!=null)
            return 1;
        return 0;
    }

    @Override
    public int registerService(User user) {
        User user1 = userMapper.findUserByUsernameAndPassword(user);
        if (user1!=null)
            return 0;
        return userMapper.insert(user);
    }

    @Override
    public User getOne(String username) {
        User user=userMapper.findUserByUsername(username);
        return user;
    }

}
