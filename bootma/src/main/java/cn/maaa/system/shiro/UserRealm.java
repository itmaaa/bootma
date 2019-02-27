package cn.maaa.system.shiro;

import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.UserMapper;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入了授权...");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user = new User().setUsername(userName);
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
