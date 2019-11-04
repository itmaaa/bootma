package cn.maaa.common.config;

import cn.maaa.common.annotation.IsMenu;
import cn.maaa.common.annotation.OperLog;
import cn.maaa.system.domain.Menu;
import cn.maaa.system.service.MenuService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author :  mazh
 * @date :  2019/11/4 14:06
 */

@Component
public class MenuScanner {

    @Autowired
    private MenuService menuService;


    /**
     * @param scanPackage 需要扫描的包路径
     */
    private  void getRequestMappingMethod(String scanPackage) {
        //设置扫描路径
        Reflections reflections = new Reflections(scanPackage, new TypeAnnotationsScanner(),new SubTypesScanner());

        ArrayList<Menu> list = Lists.newArrayList();

        Method[] methods;
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> clazz : classes) {
            RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
            String  url_prefix = "";
            if(annotation != null)
                url_prefix = annotation.value().length == 0 ? "" : annotation.value()[0];
            methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                String url =  getUrl(method);
                if(StringUtils.isEmpty(url)) {
                    continue;
                }
                url = url_prefix + url;
                Menu menu = new Menu();

                menu.setUrl(url).setType(getType(method)).setPerms(getPerms(url));
                if(getName(method) != null)
                    menu.setName(getName(method));
                list.add(menu);
            }
        }

        List<String> oldUrls = menuService.list().stream().map(Menu::getUrl).collect(Collectors.toList());
        List<String> newUrls = list.stream().map(Menu::getUrl).collect(Collectors.toList());
        for (String oldUrl : oldUrls) {
            for (String newUrl : newUrls) {
                if(!oldUrls.contains(newUrl))
                    menuService.save(list.stream().filter(m -> newUrl.equals(m.getUrl())).findFirst().get());
                if(!newUrls.contains(oldUrl))
                    menuService.remove(new UpdateWrapper<Menu>().set("url",oldUrl ));
            }
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

    private static int getType(Method method){
        boolean isMenu = method.isAnnotationPresent(IsMenu.class);
        return isMenu ? 1 : 2;
    }


    private static String getPerms(String url){
        if(StringUtils.isEmpty(url))
            return null;
        String[] path = url.split("/");
        if(path.length == 0)
            return null;
        return String.join(":", path).substring(1);
    }

    private static String getName(Method method){
        if(method.isAnnotationPresent(OperLog.class))
            return  method.getAnnotation(OperLog.class).value();
        return null;
    }


}