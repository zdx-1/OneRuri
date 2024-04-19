package com.zdx.oneruri.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zdx.oneruri.entity.Menu;
import com.zdx.oneruri.mapper.MenuMapper;
import com.zdx.oneruri.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张德鑫
 * @since 2024-04-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<Menu> getMenu() {
        List<Menu> allMenu = baseMapper.selectList(null);//查出全部菜单
        return allMenu.stream()
                .filter(item -> Objects.equals(item.getParentId(), "0"))
                .map(item -> item.setChildren(getChild(item.getId(), allMenu)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSortOrder() == null ? 0 : menu.getSortOrder())))
                .collect(Collectors.toList());
    }

    private List<Menu> getChild(String id, List<Menu> allMenu) {
        return allMenu.stream()
                .filter(item -> Objects.equals(item.getParentId(), id))
                .map(item -> item.setChildren(getChild(item.getId(), allMenu)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSortOrder() == null ? 0 : menu.getSortOrder())))
                .collect(Collectors.toList());
    }


    @Override
    public List<Menu> getMenuByPower(Integer power) {
        QueryWrapper<Menu> sectionQueryWrapper = new QueryWrapper<>();
        if (power == -1) {
            sectionQueryWrapper.ge("power", power);
        } else {
            sectionQueryWrapper.eq("power", power);
        }
        List<Menu> MenuList = baseMapper.selectList(sectionQueryWrapper);
        return MenuList.stream()
                .filter(item -> Objects.equals(item.getParentId(), "0"))
                .map(item -> item.setChildren(getChild(item.getId(), MenuList)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSortOrder() == null ? 0 : menu.getSortOrder())))
                .collect(Collectors.toList());
    }
}
