package cn.maaa.system.service.impl;

import cn.maaa.system.domain.Role;
import cn.maaa.system.mapper.RoleMapper;
import cn.maaa.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleServiceImpl
 * @author mazh
 * @date 2019年02月27日 11:24 
 */
@Transactional
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
