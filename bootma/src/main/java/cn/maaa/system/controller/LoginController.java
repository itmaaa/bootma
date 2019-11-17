package cn.maaa.system.controller;

import cn.maaa.common.annotation.Route;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.M;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.common.utils.ShiroUtils;
import cn.maaa.system.domain.File;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.service.FileService;
import cn.maaa.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@Route(value = "登录管理",exclusive = true)
public class LoginController extends BaseController {

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;

    @GetMapping({ "/", "" })
    @Route("welcome")
    String welcome(Model model) {
        return "redirect:/index";
    }

    @Route("登录页面")
    @GetMapping("/login")
    String login() {
        //已登录再访问/login直接进入index
        if(ShiroUtils.getUser() != null){
            return "redirect:/index";
        }
        return "login";
    }

    @Route("用户登录")
    @PostMapping("/login")
    @ResponseBody
	M ajaxLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //调用login方法会进入realm中执行认证操作
            subject.login(token);
            return M.ok();
        } catch (AuthenticationException e) {
            return M.error(e.getMessage());
        }
    }

    @Route("首页")
    @GetMapping({ "/index" })
	ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<>();
		List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
        map.put("menus", menus);
        map.put("username", getUser().getUsername());
		map.put("name", getUser().getName());
        File file = fileService.getById(getUser().getPicId());
        if(file!=null&&file.getUrl()!=null){
            if(fileService.isExist(file.getUrl())){
                map.put("picUrl",file.getUrl());
            }
        }else {
            map.put("picUrl","/img/photo_s.jpg");
        }

        mav.addAllObjects(map);
        mav.setViewName("index");
        return mav;
    }

    /*shiroConfig中配置了filterChainDefinitionMap.put("/logout", "logout");
    * 退出会走logout拦截器，由shiro实现了退出功能，默认跳转为“/”路径
    * 不会进入这个方法
    * */

    @GetMapping("/logout")
    @Route("退出登录")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @Route("主页前言")
    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/403")
    String unauthorizedUrl( HttpServletRequest request) {
        //ajax请求
        String requestType = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestType)){
            throw new AuthorizationException();
        }
        return "error/403";
    }

}
