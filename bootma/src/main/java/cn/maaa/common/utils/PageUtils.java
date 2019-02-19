package cn.maaa.common.utils;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author bootdo 1992lcg@163.com
 */
@Getter
@Setter
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private long total;
	private List<?> rows;
	
	public PageUtils(List<?> list) {
		PageInfo<?> pageInfo = new PageInfo<>(list);
		this.total=pageInfo.getTotal();
		this.rows=pageInfo.getList();
	}
}
