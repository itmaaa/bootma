package cn.maaa.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.maaa.system.domain.User;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
// @Mapper
public interface UserMapper {

	
	List<User> list(User user);
	
}
