package cn.maaa.system.controller;

import java.util.List;
import java.util.Objects;

import cn.maaa.common.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;


import cn.maaa.common.utils.PageUtils;
import cn.maaa.system.domain.User;
import cn.maaa.system.service.UserService;

@RequestMapping("/sys/user")
@Controller
public class UserController {
	
	private String prefix="system"  ;
	
	@Autowired
	UserService userService;

	@GetMapping(value = {"/save/{id}", "/save"})
	String edit(Model model, @PathVariable(value = "id",required = false) Long id) {
		if(Objects.isNull(id)){
			return prefix+"/add";
		}
		return prefix+"/edit";
	}
	
	@GetMapping("")
	//String user(Model model,User user,int pageNum, int pageSize ) {
	String user(Model model) {
		PageUtils page = list(null,1,10);
		model.addAttribute("pageInfo", page);
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	public PageUtils list(User user,int offset, int limit ) {
		int pageNum =(offset-1)/limit;
		//PageHelper.startPage(pageNum, pageSize, true); 查询列表数据，ture代表进行count统计，默认true
		PageHelper.startPage(pageNum, limit);
		List<User> sysUserList = userService.list(user);
		//封装PageUtils保存PageInfo中的list为rows
		PageUtils page = new PageUtils(sysUserList);
		System.out.println(JSON.toJSON(page));
		return page;
	}

	
}
