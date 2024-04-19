package com.zdx.oneruri.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id; //菜单ID

    private String name;//菜单名称

    private Integer power; //菜单状态 -1为管理员，所有都可见，0为普通用户，不可见，1为普通用户，可见，2为管理员，可见

    private String level;    //菜单级别 例如：一级菜单、二级菜单

    private String parentId; //父级ID 一级菜单

    private Integer sortOrder; //排序 0表示最前面

    private String path;    //路径

    private String icon;    //图标

    //存放子菜单目录
    @TableField(exist = false)
    private List<Menu> children;

}
