package com.example.springsecuritydemo0125.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springsecuritydemo0125.mapper.SysMenuMapper;
import com.example.springsecuritydemo0125.mapper.SysRoleMapper;
import com.example.springsecuritydemo0125.mapper.SysUserMapper;
import com.example.springsecuritydemo0125.pojo.SysMenu;
import com.example.springsecuritydemo0125.pojo.SysRole;
import com.example.springsecuritydemo0125.pojo.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysMenuMapper menuMapper;
    //这个方法是springsecurity自动调用
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、通过用户名进行登录（密码交springsecurity判断），只要我们查有这个用户名就可以了。查到一个用户对象
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        if(sysUser==null){//用户名不存在
            throw  new UsernameNotFoundException("用户不存在!");
        }
        List<GrantedAuthority> authorityList=new ArrayList<>();//这个集用来保存当前这个用户有什么角色与权限
        //用名存在，找到用户对象
        //2、获取这个用户有什么角色
        List<SysRole> sysRoles = roleMapper.selectRoleCodesByUserId(sysUser.getId());
        for (SysRole sysRole : sysRoles) {
            String roleCode = sysRole.getRoleCode();
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+roleCode));//一般都要加前缀
        }
        //3、获取这个用户有什么菜单权限
        List<SysMenu> sysMenus = menuMapper.selectMenuPermsByUserId(sysUser.getId());
        for (SysMenu sysMenu : sysMenus) {
            String perms = sysMenu.getPerms();
            authorityList.add(new SimpleGrantedAuthority(perms));
        }
        //User这个是springsecurity提供的，不是自写的
        return new User(sysUser.getUserName(),sysUser.getPassword(),authorityList);
    }


}
