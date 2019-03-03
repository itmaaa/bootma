package cn.maaa.system.controller;

import cn.maaa.common.constants.SystemConst;
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

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DeptController
 * @author maaa
 * @date 2019年03月01日 22:45
 */
@Controller
@RequestMapping("/sys/dept")
public class DeptController extends BaseController<Dept> {
    private String prefix = "system/dept";

    private DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService){
        this.deptService = deptService;
        super.setService(deptService);
    }

    @GetMapping()
    String dept() {
        return prefix + "/dept";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<Dept> list() {
        return super.selectList();
    }

    @GetMapping("/add/{pId}")
    String add(@PathVariable("pId") Long pId, Model model) {
        model.addAttribute("pId", pId);
        if (pId == SystemConst.DEPT_ROOT_ID) {
            model.addAttribute("pName", "总部门");
        } else {
            model.addAttribute("pName", deptService.getById(pId).getName());
        }
        return  prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        Dept dept = deptService.getById(id);
        model.addAttribute("dept", dept);
        if(SystemConst.DEPT_ROOT_ID.equals(dept.getParentId())) {
            model.addAttribute("parentDeptName", "无");
        }else {
            Dept parDept = deptService.getById(dept.getParentId());
            model.addAttribute("parentDeptName", parDept.getName());
        }
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
    public M remove(Long id) {
        return super.delete(id);
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
