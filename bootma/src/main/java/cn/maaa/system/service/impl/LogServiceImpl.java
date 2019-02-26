package cn.maaa.system.service.impl;

import cn.maaa.system.domain.Log;
import cn.maaa.system.mapper.LogMapper;
import cn.maaa.system.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
