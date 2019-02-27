package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.utils.M;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.system.domain.User;
import cn.maaa.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController<User> {
	
	private String prefix="system/user" ;

	UserService userService;

	/**
	 * 传递给basecontroller指定Iservice的实际类型
	 */
	@Resource
	public void setUserService(UserService userService){
		this.userService = userService;
		super.setService(userService);
	}


	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@OperLog("访问用户列表")
	@GetMapping("/list")
	@ResponseBody
	public IPage<User> list(User user,int offset, int limit ) {
		return super.page(user,offset ,limit );
	}

    @OperLog("添加用户")
	@GetMapping(value = {"/add"})
	String add(Model model) {
		return prefix+"/add";
	}

	@OperLog("编辑用户")
	@GetMapping(value = {"/edit/{id}"})
	String edit(Model model, @PathVariable(value = "id",required = false) Long id) {
		User user = userService.getById(id);
		model.addAttribute("user", user);
		return prefix+"/edit";
	}

	@OperLog("保存用户")
	@PostMapping("/save")
	@ResponseBody
	M save(User user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return super.insertOrUpdate(user);
	}

	@OperLog("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	M remove(Long id) {
		return super.delete(id);
	}

	@OperLog("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	M batchRemove(@RequestParam("ids[]") Long[] ids) {
		return super.batchDelete(ids);
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
