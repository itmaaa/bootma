package cn.maaa.common.utils;

import cn.maaa.common.annotation.Route;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.domain.RoleMenu;
import cn.maaa.system.mapper.RoleMenuMapper;
import cn.maaa.system.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  菜单扫描器
 * @author :  mazh
 * @date :  2019/11/4 14:06
 */

@Component
@Slf4j
public class MenuScanner  {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     * @param scanPackage 需要扫描的包路径
     */
    public   void initMenu(String scanPackage) {
        log.info("============================  initMenu start ==============================" );
        //设置扫描路径
        Reflections reflections = new Reflections(scanPackage, new TypeAnnotationsScanner(),new SubTypesScanner());

        ArrayList<Menu> list = Lists.newArrayList();

        List<Menu> oldMenus = menuService.list();
        List<String> oldUrls = oldMenus.stream().map(Menu::getUrl).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> newUrls = Lists.newArrayList();

        Date now = new Date();
        Method[] methods;
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : classes) {
            Route route = clazz.getAnnotation(Route.class);
            if(route == null || route.exclusive())
                continue;

            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
            String  url_prefix = "";
            if(annotation != null) {
                url_prefix = annotation.value().length == 0 ? "" : annotation.value()[0];
            }
            //菜单
            Menu parent = new Menu();
            if(oldUrls.contains(url_prefix)){
                String finalUrl_prefix = url_prefix;
                System.out.println();
                parent = oldMenus.stream().filter(one -> finalUrl_prefix.equals(one.getUrl())).findAny().get();
            }else {
                parent.setUrl(url_prefix).setType(1).setPerms(getPerms(url_prefix)).setName(getName(clazz)).setParentId(0L).setGmtCreate(now).setGmtModified(now);
                menuService.save(parent);
                //超级管理员增加所有菜单权限
                roleMenuMapper.insert(new RoleMenu().setRoleId(1L).setMenuId(parent.getId()));
            }
            newUrls.add(url_prefix);

            methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Route oper = method.getAnnotation(Route.class);
                if(oper == null || oper.exclusive())
                    continue;
                String url =  getUrl(method);
                if(StringUtils.isEmpty(url)) {
                    continue;
                }
                url = url_prefix + url;
                Menu menu = new Menu();

                menu.setUrl(url).setType(2).setPerms(getPerms(url)).setParentId(parent.getId()).setGmtCreate(now).setGmtModified(now);
                if(getName(method) != null) {
                    menu.setName(getName(method));
                }
                list.add(menu);
            }
        }

        newUrls.addAll(list.stream().map(Menu::getUrl).collect(Collectors.toList()));
        //菜单维护
        updateMenu(list, oldUrls, newUrls, now);
        log.info("============================  initMenu end ==============================" );
    }

    private void updateMenu(ArrayList<Menu> list, List<String> oldUrls, List<String> newUrls, Date now) {
        List<String> temp1 = Lists.newArrayList();
        temp1 .addAll(newUrls);
        List<String> temp2 = Lists.newArrayList();
        temp2 .addAll(oldUrls);

        //删除不存在了的菜单
        oldUrls.removeAll(temp1);
        if(!oldUrls.isEmpty()){
            log.info("============ remove menus =============" );
            QueryWrapper<Menu> wrapper = new QueryWrapper<>();
            wrapper.in("url",oldUrls );
           // menuService.remove(wrapper);
            List<Long> menuIds = menuService.list(wrapper).stream().map(Menu::getId).collect(Collectors.toList());
            menuService.removeByIds(menuIds);
            QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
            roleMenuQueryWrapper.in("menu_id",menuIds );
            roleMenuMapper.delete(roleMenuQueryWrapper);
        }


        //添加新菜单
        newUrls.removeAll(temp2);
        List<Menu> ml = list.stream().filter(m -> newUrls.contains(m.getUrl())).collect(Collectors.toList());
        if(!ml.isEmpty()){
            log.info("============ add menus =============" );
          //  menuService.saveBatch(ml);
            for (Menu menu : ml) {
                menuService.save(menu);
                //超级管理员增加所有菜单权限
                roleMenuMapper.insert(new RoleMenu().setRoleId(1L).setMenuId(menu.getId()));
            }

        }

        //更新菜单名
        temp1.removeAll(newUrls);
        List<Menu> um = list.stream().filter(m -> temp1.contains(m.getUrl())).collect(Collectors.toList());
        if(!um.isEmpty()){
            log.info("============ update menus =============" );
            um.forEach(menu -> {
                UpdateWrapper<Menu> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("name", menu.getName()).set("gmt_modified",now).eq("url",menu.getUrl() );
                menuService.update(updateWrapper);
            });
        }

    }


    private static String getUrl(Method method) {
        Annotation[] declaredAnnotations =
                method.getDeclaredAnnotations();

        for (Annotation declaredAnnotation : declaredAnnotations) {
            if (declaredAnnotation instanceof GetMapping){
                return ((GetMapping)declaredAnnotation).value().length == 0 ? "" : ((GetMapping)declaredAnnotation).value()[0];
            }
            if (declaredAnnotation instanceof PostMapping){
                return ((PostMapping)declaredAnnotation).value().length == 0 ? "" : ((PostMapping)declaredAnnotation).value()[0];
            }
            if (declaredAnnotation instanceof DeleteMapping){
                return ((DeleteMapping)declaredAnnotation).value().length == 0 ? "" : ((DeleteMapping)declaredAnnotation).value()[0];
            }
            if (declaredAnnotation instanceof PutMapping){
                return ((PutMapping)declaredAnnotation).value().length == 0 ? "" : ((PutMapping)declaredAnnotation).value()[0];
            }
        }
        return  null;
    }

    private static String getPerms(String url){
        if(StringUtils.isEmpty(url))
            return null;
        String[] path = url.split("/");
        if(path.length == 0)
            return null;
        String s = String.join(":", path);
        if(s.startsWith(":"))
                s = s.substring(1);
        if(s.contains("{"))
                return s.substring(0,s.lastIndexOf(":"));
        return s;
    }

    private static String getName(AnnotatedElement element){
        if(element.isAnnotationPresent(Route.class))
            return  element.getAnnotation(Route.class).value();
        return null;
    }



}