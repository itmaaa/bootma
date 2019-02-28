package cn.maaa.system.service.impl;

import cn.maaa.common.domain.Tree;
import cn.maaa.common.utils.BeanConvert;
import cn.maaa.common.utils.BuildTree;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.mapper.MenuMapper;
import cn.maaa.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	private BeanConvert beanConvert;

    @Override
    public List<Tree<Menu>> listMenuTree(Long id) {
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
      //  List<Menu> menus = menuMapper.listMenuByUserId(id);
		List<Menu> menus = list();
		Map<String, Object> attributes = new HashMap<>(2);
		//将菜单对象包装成树节点
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<Menu>();
			//BeanConvert.execute(menu,tree );
			Tree<Menu> exec = (Tree<Menu>) beanConvert.exec(menu, tree);
			tree.setId(menu.getId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getName());
            //attributes可以存放需要的关于树节点的信息
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
}
