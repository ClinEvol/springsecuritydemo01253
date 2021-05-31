package com.example.springsecuritydemo0125.mapper;

import com.example.springsecuritydemo0125.pojo.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zs
 * @since 2021-05-27
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> selectMenuPermsByUserId(Integer userId);
}
