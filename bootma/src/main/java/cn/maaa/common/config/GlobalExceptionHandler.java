package cn.maaa.common.config;

import cn.maaa.common.exception.BizException;
import cn.maaa.common.utils.ExceptionUtils;
import cn.maaa.common.utils.M;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 *
 * @author maaa
 * @date 2019年08月17日 18:57
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public M bizExceptionHandler(BizException bizException){
        return M.error(ExceptionUtils.errorMsg(bizException));
    }
}
