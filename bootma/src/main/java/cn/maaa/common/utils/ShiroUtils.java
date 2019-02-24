package cn.maaa.common.utils;

import cn.maaa.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        return (User)object;
    }
    public static Long getUserId() {
        return getUser().getId();
    }

    public static void logout() {
        getSubjct().logout();
    }
}
