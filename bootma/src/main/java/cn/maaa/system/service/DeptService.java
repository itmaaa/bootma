package cn.maaa.system.service;

import cn.maaa.common.domain.Tree;
import cn.maaa.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * DeptService
 * @author maaa
 * @date 2019年03月01日 22:47
 */
public interface DeptService extends IService<Dept> {

    Tree<Dept> getTree();
}
