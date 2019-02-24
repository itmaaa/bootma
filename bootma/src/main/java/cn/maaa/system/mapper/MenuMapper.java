package cn.maaa.system.mapper;

import cn.maaa.system.domain.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    List<Menu> list(Map<String,Object> map);
    public List<Menu> listMenuByUserId(Long id);
}
