package cn.maaa.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.UserMapper;
import cn.maaa.system.service.UserService;

//@CacheConfig(cacheNames = "user")
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public List<User> list(User user) {
        return userMapper.list(user);
    }


}
