package cn.maaa.common.utils;

import cn.maaa.system.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

@Slf4j
public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        try {
            Object object = getSubjct().getPrincipal();
            return (User)object;
        } catch (InvalidSessionException e) {
             log.error(ExceptionUtils.errorMsg(e));
             return null;
        }
    }
    public static Long getUserId() {
        return getUser().getId();
    }

    public static void logout() {
        getSubjct().logout();
    }
}
