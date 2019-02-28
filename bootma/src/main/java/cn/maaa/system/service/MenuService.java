package cn.maaa.system.service;

import cn.maaa.common.domain.Tree;
import cn.maaa.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Tree<Menu>> listMenuTree(Long id);
}
