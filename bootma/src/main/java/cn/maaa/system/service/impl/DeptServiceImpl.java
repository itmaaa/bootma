package cn.maaa.system.service.impl;

import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.BuildTree;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.mapper.DeptMapper;
import cn.maaa.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * DeptServiceImpl
 * @author maaa
 * @date 2019年03月01日 22:47
 */
@Transactional
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {



    @Override
    public Tree<Dept> getTree() {
        List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
        List<Dept> list = list();
        list.forEach(dept -> {
            Tree<Dept> tree = new Tree<Dept>();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = Collections.singletonMap("opened", true);
            tree.setState(state);
            trees.add(tree);
        });
        return BuildTree.build(trees);
    }
}
