package cn.maaa.system.mapper;

import cn.maaa.system.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectOnesResource(@Param("id") Long id);
}
