package cn.maaa.system.service.impl;

import cn.maaa.system.domain.Role;
import cn.maaa.system.domain.RoleMenu;
import cn.maaa.system.domain.User;
import cn.maaa.system.domain.UserRole;
import cn.maaa.system.mapper.RoleMapper;
import cn.maaa.system.mapper.RoleMenuMapper;
import cn.maaa.system.mapper.UserRoleMapper;
import cn.maaa.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * RoleServiceImpl
 * @author mazh
 * @date 2019年02月27日 11:24 
 */
@Transactional
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> list(Long id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().eq("user_id", id).select("role_id");
        List<Long> rolesIds = userRoleMapper.selectList(wrapper).parallelStream().map(UserRole::getRoleId).collect(Collectors.toList());

        List<Role> roles = list();
        for (Role role : roles) {
            role.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(role.getId(), roleId)) {
                    role.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public boolean save(Role role) {
        boolean b = saveOrUpdate(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getId();
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        UpdateWrapper wrapper = new UpdateWrapper<>().eq("role_id", roleId);
        roleMenuMapper.delete(wrapper);
        if (rms.size() > 0) {
            rms.forEach(roleMenuMapper::insert);
        }
        return true;
    }
}
