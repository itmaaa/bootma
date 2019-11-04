package cn.maaa.system.controller;

import cn.maaa.common.annotation.IsMenu;
import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.service.MenuService;
import cn.maaa.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * MenuController
 * @author mazh
 * @date 2019年03月01日 11:03 
 */
@Controller
@RequestMapping("sys/menu")
public class MenuController extends BaseController<Menu> {
	
	String prefix = "system/menu";

	MenuService menuService;

	@Resource
	public void setMenuService(MenuService menuService){
		this.menuService = menuService;
		super.setService(menuService);
	}

	@GetMapping()
	@IsMenu
	String menu(Model model) {
		return prefix+"/menu";
	}

	@GetMapping("/list")
	@ResponseBody
	@IsMenu
	List<Menu> list() {
		return super.selectList();
	}

	@OperLog("添加菜单")
	@GetMapping("/add/{pId}")
	String add() {
		return prefix + "/add";
	}

	@OperLog("编辑菜单")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		Menu menu = menuService.getById(id);
		Long pId = menu.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.getById(pId).getName());
		}
		model.addAttribute("menu", menu);
		return prefix+"/edit";
	}

	@OperLog("保存菜单")
	@PostMapping("/save")
	@ResponseBody
	M save(Menu menu) {
		return super.insertOrUpdate(menu);
	}

	@OperLog("删除菜单")
	@PostMapping("/remove")
	@ResponseBody
	M remove(Long id) {
		return  super.delete(id);
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<Menu> tree() {
		Tree<Menu>  tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<Menu> tree(@PathVariable("roleId") Long roleId) {
		Tree<Menu> tree = menuService.getTree(roleId);
		return tree;
	}

}
