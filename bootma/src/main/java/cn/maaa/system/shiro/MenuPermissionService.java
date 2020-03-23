package cn.maaa.system.shiro;

import cn.maaa.common.utils.MenuScanner;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.mapper.MenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配置需要授权访问的菜单
 *
 * @author maaa
 * @date 2019年11月03日 16:01
 */
@Service
@Slf4j
public class MenuPermissionService  {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuScanner menuScanner;

    public Map<String, String>  findMenuPermission() {
        //先初始化菜单
        menuScanner.initMenu("cn.maaa.system.controller");
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.select("url","perms")
                         .isNotNull("url")
                         .ne("url","")
                         .isNotNull("perms")
                         .ne("perms","");
        Map<String, String> map = menuMapper.selectList(menuQueryWrapper)
                .stream().collect(Collectors.toMap(menu -> subUrl(menu.getUrl()), menu -> String.format("perms[%s]",menu.getPerms())));
        log.info(map.toString());
        return  map;
    }

    private static String subUrl(String url){
        if(url.contains("{"))
            return url.substring(0,url.lastIndexOf("/")+1)+"*";
        return url;
    }

}
