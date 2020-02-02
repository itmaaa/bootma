package cn.maaa.system.service.impl;

import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.BuildTree;
import cn.maaa.system.domain.Dept;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.mapper.DeptMapper;
import cn.maaa.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
        QueryWrapper<Dept> wrapper = new QueryWrapper<Dept>().orderByAsc("order_num");
        List<Dept> list = list(wrapper);
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

    @Override
    public void delete(Long id) {
        ArrayList<Long> totalMenus = Lists.newArrayList();
        totalMenus.add(id);
        findSubmenu( Arrays.asList(id),totalMenus);
        removeByIds(totalMenus);
    }

    private void findSubmenu(List ids,List totalMenus){
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>().in("parent_id", ids).select("id");
        List<Long> subs = list(queryWrapper).stream().map(Dept::getId).collect(Collectors.toList());
        if(subs.isEmpty())
            return;
        totalMenus.addAll(subs);
        findSubmenu( subs,totalMenus);
    }
}
