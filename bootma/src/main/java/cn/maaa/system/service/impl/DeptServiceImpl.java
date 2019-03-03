package cn.maaa.system.service.impl;

import cn.maaa.system.domain.Dept;
import cn.maaa.system.mapper.DeptMapper;
import cn.maaa.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DeptServiceImpl
 * @author maaa
 * @date 2019年03月01日 22:47
 */
@Transactional
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
