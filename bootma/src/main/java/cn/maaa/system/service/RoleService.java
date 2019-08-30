package cn.maaa.system.service;

import cn.maaa.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * RoleService
 * @author mazh
 * @date 2019年02月27日 11:23 
 */
public interface RoleService extends IService<Role> {

    List<Role> list(Long id);
}
