package cn.maaa.system.service.impl;

import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.BeanConvert;
import cn.maaa.common.utils.BuildTree;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.domain.RoleMenu;
import cn.maaa.system.mapper.MenuMapper;
import cn.maaa.system.mapper.RoleMenuMapper;
import cn.maaa.system.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private BeanConvert beanConvert;

	@Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
      //  List<Menu> menus = menuMapper.listMenuByUserId(id);
        QueryWrapper<Menu> wrapper = new QueryWrapper<Menu>().orderByAsc("order_num");
        List<Menu> menus = list(wrapper);
		//将菜单对象包装成树节点
		for (Menu menu : menus) {
			Tree<Menu> tree = (Tree<Menu>) beanConvert.apacheForceExec(menu, new Tree<Menu>());
			//attributes可以存放需要的关于树节点的信息
			Map<String, Object> attributes = new HashMap<>(2);
			attributes.put("url", menu.getUrl());
            attributes.put("icon", menu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        /*组建树节点之间的关系，封装成一棵树
          默认顶级菜单为０，根据数据库实际情况调整*/
        List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public Tree<Menu> getTree(Long id) {
        // 根据roleId查询权限
        List<Menu> menus = list();
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<RoleMenu>().eq("role_id", id);
        List<Long> menuIds =  roleMenuMapper.selectList(wrapper).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Long> temp = menuIds;

        for (Menu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        QueryWrapper<Menu> orderWrapper = new QueryWrapper<Menu>().orderByAsc("order_num");
        List<Menu> menuDOs = list(orderWrapper);

        for (Menu sysMenu : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenu.getId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            Map<String, Object> state = new HashMap<>(16);
            Long menuId = sysMenu.getId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<Menu> getTree() {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        List<Menu> menuDOs = list();
        for (Menu sysMenu : menuDOs) {
            Tree<Menu> tree = new Tree<Menu>();
            tree.setId(sysMenu.getId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<Menu> t = BuildTree.build(trees);
        return t;
    }
}
