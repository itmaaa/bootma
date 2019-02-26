package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.common.utils.Ma;
import cn.maaa.common.utils.ShiroUtils;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    MenuService menuService;


    @GetMapping({ "/", "" })
    String welcome(Model model) {
        return "redirect:/index";
    }

    @OperLog("访问登录页面")
    @GetMapping("/login")
    String login() {
        //已登录再访问/login直接进入index
        if(ShiroUtils.getUser() != null){
            return "redirect:/index";
        }
        return "login";
    }

    @OperLog("用户登录")
    @PostMapping("/login")
    @ResponseBody
    Ma ajaxLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Utils.encrypt(password));
        try {
            //调用login方法会进入realm中执行认证操作
            subject.login(token);
            return Ma.ok();
        } catch (AuthenticationException e) {
            return Ma.error(e.getMessage());
        }
    }

    @OperLog("访问首页")
    @GetMapping({ "/index" })
	ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<>();
		List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
        map.put("menus", menus);
        map.put("picUrl","/img/photo_s.jpg");
        map.put("username", getUser().getUsername());
		map.put("name", getUser().getName());

        mav.addAllObjects(map);
        mav.setViewName("index");
        return mav;
    }

    /*shiroConfig中配置了filterChainDefinitionMap.put("/logout", "logout");
    * 退出会走logout拦截器，由shiro实现了退出功能，默认跳转为“/”路径
    * 不会进入这个方法
    * */

    @GetMapping("/logout")
    String logout() {
        System.out.println("退出登录...");
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @OperLog("主页前言")
    @GetMapping("/main")
    String main() {
        return "main";
    }

}
