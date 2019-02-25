package cn.maaa.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.maaa.common.Constants.SystemConst;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.common.utils.Ma;
import cn.maaa.common.utils.PageInfo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.maaa.system.domain.User;
import cn.maaa.system.service.UserService;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	
	private String prefix="system"  ;
	
	@Autowired
	UserService userService;

	@GetMapping("")
	String user(Model model,User user,
				@RequestParam(name = "offset",defaultValue = "0") int offset,
				@RequestParam(name = "limit",defaultValue = "10") int limit ) {
		IPage<User>  page = list(user,offset,limit);
		model.addAttribute("pageInfo", page);
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	public IPage<User> list(User user,int offset, int limit ) {
		int pageNum =offset/limit;
		PageInfo<User> pageInfo = new PageInfo<>();
		pageInfo.setSize(limit);
		pageInfo.setCurrent(pageNum);
		IPage<User> page = userService.page(pageInfo);
		return page;
	}

	@GetMapping(value = {"/add"})
	String add(Model model) {
		return prefix+"/add";
	}

	@GetMapping(value = {"/edit/{id}"})
	String edit(Model model, @PathVariable(value = "id",required = false) Long id) {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return prefix+"/edit";
	}

	@PostMapping("/save")
	@ResponseBody
	Ma save(User user) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return Ma.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if(userService.saveOrUpdate(user)){
			return Ma.ok();
		}
		return Ma.error();
	}

	@PostMapping("/remove")
	@ResponseBody
	Ma remove(Long id) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return Ma.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(userService.removeById(id)){
			return Ma.ok();
		}
		return Ma.error();
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	Ma batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return Ma.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(userService.removeByIds(Arrays.asList(ids))){
			return Ma.ok();
		}
		return Ma.error();
	}

	@PostMapping("/exist")
	@ResponseBody
	boolean exist(String username) {
		// 存在，不通过，false
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username",username);
		return userService.count(wrapper) < 1;
	}

}
