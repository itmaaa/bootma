package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.constants.SystemConst;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * DeptController
 * @author maaa
 * @date 2019年03月01日 22:45
 */
@Controller
@RequestMapping("sys/dept")
@OperLog("部门管理")
public class DeptController extends BaseController<Dept> {
    private String prefix = "system/dept";

    private DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService){
        this.deptService = deptService;
        super.setService(deptService);
    }

    @GetMapping()
    @OperLog("部门页面")
    String dept() {
        return prefix + "/dept";
    }

    @ResponseBody
    @GetMapping("/list")
    @OperLog("部门列表")
    public List<Dept> list() {
        return super.selectList();
    }

    @GetMapping("/add/{pId}")
    @OperLog(value = "添加部门")
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
    @OperLog(value = "编辑部门")
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
    @OperLog("保存部门")
    public M save(Dept sysDept) {
        return super.insertOrUpdate(sysDept);
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @OperLog("删除部门")
    public M remove(Long id) {
        return super.delete(id);
    }


    @GetMapping("/tree")
    @ResponseBody
    @OperLog("部门树")
    public Tree<Dept> tree() {
        return deptService.getTree();
    }

    @GetMapping("/treeView")
    @OperLog("部门树页面")
    String treeView() {
        return  prefix + "/deptTree";
    }
}
