package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.constants.SystemConst;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.utils.M;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.domain.Role;
import cn.maaa.system.domain.User;
import cn.maaa.system.dto.UserDTO;
import cn.maaa.system.service.DeptService;
import cn.maaa.system.service.DictService;
import cn.maaa.system.service.RoleService;
import cn.maaa.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("sys/user")
@Controller
@OperLog("用户管理")
public class UserController extends BaseController<User> {
	
	private String prefix="system/user" ;

	UserService userService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DictService dictService;

	/**
	 * 传递给basecontroller指定Iservice的实际类型
	 */
	@Resource
	public void setUserService(UserService userService){
		this.userService = userService;
		super.setService(userService);
	}


	@GetMapping("")
	@OperLog("用户页面")
	String user(Model model) {
		return prefix + "/user";
	}

	@OperLog("用户列表")
	@GetMapping("/list")
	@ResponseBody
	public IPage<User> list(User user,int offset, int limit ) {
		return super.page(user,offset ,limit );
	}

    /**
     * @Description:
     *  动态数据源测试
     */
	@OperLog("bootdo用户列表")
	@GetMapping("/bootdoList")
	@ResponseBody
	//@DS("bootdo")
	public IPage<User> bootdoList(User user,int offset, int limit ) {
		QueryWrapper<User> wrapper = new QueryWrapper<User>().select("username","name");
		return super.page(wrapper,offset ,limit );
	}

    @OperLog("添加用户")
	@GetMapping(value = {"/add"})
	String add(Model model) {

		List<Role> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix+"/add";
	}

	@OperLog("编辑用户")
	@GetMapping(value = {"/edit/{id}"})
	String edit(Model model, @PathVariable(value = "id",required = false) Long id) {
		User user = userService.getById(id);
		Dept dept = deptService.getById(user.getDeptId());
		if (dept != null) {
			user.setDeptName(dept.getName());
		}
		model.addAttribute("user", user);

		List<Role> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

	@OperLog("保存用户")
	@PostMapping("/save")
	@ResponseBody
	M save(User user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user)) {
			return M.ok();
		}
		return M.error();
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
	@OperLog(exclusive = true,record = false)
	boolean exist(String username) {
		// 存在，不通过，false
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username",username);
		return userService.count(wrapper) < 1;
	}

	@GetMapping("/personal")
	@OperLog("个人信息页面")
	String personal(Model model) {
		User user  = userService.getById(getUserId());
		model.addAttribute("user",user);
		model.addAttribute("hobbyList",dictService.getHobbyList(user));

		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}

	@PostMapping("/updatePeronal")
	@ResponseBody
	@OperLog("更新个人信息")
	M updatePeronal(User user) {
		userService.updateById(user);
		return M.ok();
	}

	@PostMapping("/resetPwd")
	@ResponseBody
	@OperLog("重置密码")
	M resetPwd(UserDTO userDTO) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(userDTO,getUser());
			return M.ok();
		}catch (Exception e){
			return M.error(1,e.getMessage());
		}

	}

	@PostMapping("/adminResetPwd")
	@ResponseBody
	@OperLog("管理员重置密码")
	M adminResetPwd(UserDTO userDTO) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userDTO);
			return M.ok();
		}catch (Exception e){
			return M.error(1,e.getMessage());
		}

	}


	@GetMapping("/resetPwd/{id}")
	@OperLog("重置密码页面")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		User user = new User();
		user.setId(userId);
		model.addAttribute("user", user);
		return prefix + "/reset_pwd";
	}

	@ResponseBody
	@PostMapping("/uploadImg")
	@OperLog("上传图片")
	M uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return M.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return M.ok(result);
		}else {
			return M.error("更新图像失败！");
		}
	}

}
