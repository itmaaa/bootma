package cn.maaa.system.mapper;

import cn.maaa.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * UserMapper
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
// @Mapper
public interface UserMapper extends BaseMapper<User> {

	User selectOne(User user);

}
