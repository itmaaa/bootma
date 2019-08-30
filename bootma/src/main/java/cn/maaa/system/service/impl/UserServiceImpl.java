package cn.maaa.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.maaa.system.domain.UserRole;
import cn.maaa.system.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.UserMapper;
import cn.maaa.system.service.UserService;

//@CacheConfig(cacheNames = "user")
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Transactional
    @Override
    public boolean save(User user) {
        saveOrUpdate(user);
        Long userId = user.getId();
        List<Long> roleIds = user.getRoleIds();
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper<UserRole>().eq("user_id", userId);
        userRoleMapper.delete(wrapper);

        List<UserRole> userRoles = roleIds.parallelStream().map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toList());

        if (userRoles.size() > 0) {
            userRoles.parallelStream().forEach(userRoleMapper :: insert);
        }
        return true;
    }
}
