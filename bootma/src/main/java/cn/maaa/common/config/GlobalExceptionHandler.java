package cn.maaa.common.config;

import cn.maaa.common.exception.BizException;
import cn.maaa.common.utils.ExceptionUtils;
import cn.maaa.common.utils.M;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author maaa
 * @date 2019年08月17日 18:57
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public M bizExceptionHandler(BizException bizException){
        return M.error(ExceptionUtils.errorMsg(bizException));
    }

    @ExceptionHandler(AuthorizationException.class)
    public M authorizationExceptionHandler(AuthorizationException authorizationException){
        return M.error(403, "没有操作权限");
    }

}
