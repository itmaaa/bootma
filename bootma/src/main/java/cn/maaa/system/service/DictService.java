package cn.maaa.system.service;

import cn.maaa.system.domain.Dict;
import cn.maaa.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2019年09月01日 11:49
 */
public interface DictService extends IService<Dict> {
    List<Dict> getSexList();

    List<Dict> getHobbyList(User user);
}
