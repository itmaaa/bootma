package cn.maaa.system.service.impl;

import cn.maaa.system.domain.Dict;
import cn.maaa.system.domain.User;
import cn.maaa.system.mapper.DictMapper;
import cn.maaa.system.service.DictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2019年09月01日 11:51
 */
@Transactional
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Override
    public List<Dict> getSexList() {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>().eq("type", "sex");
        return list(wrapper);
    }

    @Override
    public List<Dict> getHobbyList(User user) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<Dict>().eq("type", "hobby");
        List<Dict> hobbyList = list(wrapper);

        if (StringUtils.isNotEmpty(user.getHobby())) {
            String userHobbys[] = user.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (Dict hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }
}
