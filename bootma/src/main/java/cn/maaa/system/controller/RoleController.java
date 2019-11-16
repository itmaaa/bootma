package cn.maaa.system.controller;

import cn.maaa.common.annotation.Route;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Role;
import cn.maaa.system.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("sys/role")
@Controller
@Route("角色管理")
public class RoleController extends BaseController<Role> {
	String prefix = "system/role";

	RoleService roleService;

	@Resource
	public void setRoleService(RoleService roleService){
		this.roleService = roleService;
		super.setService(roleService);
	}

	@GetMapping()
	@Route("角色页面")
	String role() {
		return prefix + "/role";
	}

	@Route("角色列表")
	@GetMapping("/list")
	@ResponseBody()
	public List<Role> list() {
		//因为role的记录不多，设置了在前端分页，所以后台直接返回list即可
		return super.selectList();
	}

	@Route("添加角色")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Route("编辑角色")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		Role roleDO = roleService.getById(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Route("保存角色")
	@PostMapping("/save")
	@ResponseBody()
	M save(Role role) {
		//return super.insertOrUpdate(role);
		 roleService.save(role);
		return M.ok();
	}

	@Route("删除角色")
	@PostMapping("/remove")
	@ResponseBody()
	M remove (Long id) {
		return super.delete(id);
	}
	
	@Route("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	M batchRemove(@RequestParam("ids[]") Long[] ids) {
		return super.batchDelete(ids);
	}
}
