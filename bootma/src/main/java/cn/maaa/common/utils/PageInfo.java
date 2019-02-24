package cn.maaa.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义pageInfo对象，将page对象的records封装为rows
 * @author maaa
 * @date 2019年02月23日 20:59
 */
@Data
public class PageInfo<T> extends Page<T> {

    private List<T> rows;

    @Override
    public Page<T> setRecords(List<T> records) {
        this.rows = records;
        return super.setRecords(records);
    }
}
