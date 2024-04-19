package com.zdx.oneruri.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
@Schema(name= "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(description="用户id")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;
    @Schema(description="用户名")
    private String username;
    @Schema(description="密码")
    private String password;
    @Schema(description="权限")
    private Integer power;
    @Schema(description="状态")
    private String status;
    @Schema(description="头像")
    private String avatar;
    @Schema(description="性别")
    private String sex;
    @Schema(description="地址")
    private String address;
    @Schema(description="生日")
    private LocalDateTime birthday;
    @Schema(description="电话")
    private String phone;
    @Schema(description="邮箱")
    private String email;
    @Schema(description="注册时间")
    private LocalDateTime regDate;
    @Schema(description="个性签名")
    private String description;


}
