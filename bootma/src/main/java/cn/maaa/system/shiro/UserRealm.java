package cn.maaa.system.shiro;

import cn.maaa.common.config.ApplicationContextRegister;
import cn.maaa.common.utils.ShiroUtils;
import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.MenuMapper;
import cn.maaa.system.mapper.UserMapper;
//import com.alibaba.druid.sql.visitor.functions.Char;
import cn.maaa.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class UserRealm extends AuthorizingRealm {


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("======================doGetAuthorizationInfo=======================");
        Long userId = ShiroUtils.getUserId();
        MenuMapper menuMapper = ApplicationContextRegister.getBean(MenuMapper.class);
        List<String> perms = menuMapper.selectOnesResource(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("======================doGetAuthenticationInfo========================");
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = new User().setUsername(userName);
        UserMapper userMapper = ApplicationContextRegister.getBean(UserMapper.class);
        User dbUser = userMapper.selectOne(user);
        // 账号不存在
        if (dbUser == null) {
            throw new UnknownAccountException("账号不存在");
        }

        // 密码错误
        if (!password.equals(dbUser.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        if (dbUser.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(dbUser, password, getName());
        return info;

    }
}
