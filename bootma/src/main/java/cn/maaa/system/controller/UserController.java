package cn.maaa.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.maaa.common.utils.PageUtils;
import cn.maaa.system.domain.User;
import cn.maaa.system.service.UserService;

@RequestMapping("/sys/user")
@Controller
public class UserController{
	
	private String prefix="system"  ;
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	String user(Model model,User user,int pageNum, int pageSize ) {
		PageInfo<User> pageInfo = list(user, pageNum, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	public PageInfo<User> list(User user,int pageNum, int pageSize ) {
		// 查询列表数据
		//PageHelper.startPage(pageNum, pageSize, true);
		PageHelper.startPage(pageNum, pageSize);
		List<User> sysUserList = userService.list(user);
		PageInfo<User> page = new PageInfo<>(sysUserList);
		return page;
	}

	
}
