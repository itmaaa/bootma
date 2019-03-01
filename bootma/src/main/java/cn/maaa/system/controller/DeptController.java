package cn.maaa.system.controller;

import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.service.DeptService;
import com.baomidou.mybatisplus.extension.api.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DeptController
 * @author maaa
 * @date 2019年03月01日 22:45
 */
@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController<Dept> {
    private String prefix = "system/dept";

    @Autowired
    private DeptService deptService;

    @GetMapping()
    String dept() {
        return prefix + "/dept";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<Dept> list() {
        return super.selectList();
    }

    @GetMapping("/add")
    String add() {
        return  prefix + "/add";
    }

    @GetMapping("/edit/{deptId}")
    String edit(@PathVariable("deptId") Long deptId, Model model) {
        Dept sysDept = deptService.getById(deptId);
        model.addAttribute("sysDept", sysDept);
        return  prefix + "/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public M save(Dept sysDept) {
        return super.insertOrUpdate(sysDept);
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public M remove(Long deptId) {
        return super.delete(deptId);
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public M batchRemove(@RequestParam("ids[]") Long[] deptIds) {
        return super.batchDelete(deptIds);
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<Dept> tree() {
        Tree<Dept> tree = new Tree<Dept>();
       // tree = deptService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    String treeView() {
        return  prefix + "/deptTree";
    }
}
