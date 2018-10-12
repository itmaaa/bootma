package cn.maaa.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.maaa.system.domain.User;

@Service
public interface UserService {

	List<User> list(User user);

}
