package cn.maaa.system.controller;

import cn.maaa.common.annotation.Route;
import cn.maaa.common.constants.SystemConst;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@RequestMapping("/sys/dept")
@Route("部门管理")
public class DeptController extends BaseController<Dept> {
    private String prefix = "system/dept";

    private DeptService deptService;

    @Resource
    public void setDeptService(DeptService deptService){
        this.deptService = deptService;
        super.setService(deptService);
    }

    @GetMapping()
    @Route("部门页面")
    String dept() {
        return prefix + "/dept";
    }

    @ResponseBody
    @GetMapping("/list")
    @Route("部门列表")
    public List<Dept> list() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<Dept>().orderByAsc("order_num");
        return super.selectList(wrapper);
    }

    @GetMapping("/add/{pId}")
    @Route(value = "添加部门")
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
    @Route("编辑部门")
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
    @Route("保存部门")
    public M save(Dept sysDept) {
        return super.insertOrUpdate(sysDept);
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @Route("删除部门")
    public M remove(Long id) {
        //return super.delete(id);
        deptService.delete(id);
        return M.ok();
    }


    @GetMapping("/tree")
    @ResponseBody
    public Tree<Dept> tree() {
        return deptService.getTree();
    }

    @GetMapping("/treeView")
    @Route("部门树")
    String treeView() {
        return  prefix + "/deptTree";
    }
}
