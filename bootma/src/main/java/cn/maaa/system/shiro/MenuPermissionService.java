package cn.maaa.system.shiro;

import cn.maaa.system.domain.Menu;
import cn.maaa.system.mapper.MenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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

    public Map<String, String>  findMenuPermission() {
        QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
        menuQueryWrapper.select("url","perms")
                         .isNotNull("url")
                         .ne("url","")
                         .isNotNull("perms")
                         .ne("perms","");
        Map<String, String> map = menuMapper.selectList(menuQueryWrapper)
                .stream().collect(Collectors.toMap(Menu::getUrl, menu -> String.format("perms[%s]",menu.getPerms())));
        log.info(map.toString());
        return  map;
    }

}
