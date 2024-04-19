package com.zdx.oneruri.mapper;

import com.zdx.oneruri.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findUserByUsernameAndPassword(@RequestBody User user);

    User findUserByUsername(@Param("username") String username);
}
