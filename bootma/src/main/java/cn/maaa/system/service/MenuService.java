package cn.maaa.system.service;

import cn.maaa.common.domain.Tree;
import cn.maaa.system.domain.Menu;

import java.util.List;

public interface MenuService {
    List<Tree<Menu>> listMenuTree(Long id);
}
