package cn.maaa.ztest.redission;

import cn.maaa.common.utils.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author :  mazh
 * @date :  2019/11/13 17:22
 */
@Controller
public class RedissionController {

    @Autowired
    private RedissionService testService;

    /** 查询 */
    @GetMapping("/test/redisson")
    @ResponseBody
    public M testFreeMarker(Model model){
        testService.saveUser();
        return M.ok();
    }
}
