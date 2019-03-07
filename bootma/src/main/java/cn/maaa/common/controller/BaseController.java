package cn.maaa.common.controller;


import cn.maaa.common.constants.SystemConst;
import cn.maaa.common.utils.M;
import cn.maaa.common.utils.PageInfo;
import cn.maaa.common.utils.ShiroUtils;
import cn.maaa.system.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * BaseController
 * @author mazh
 * @date 2019年02月26日 14:33
 */

@Getter
@Setter
public class BaseController<T> {

	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}


	private T t;

	private IService<T>  service;


	/**
	 * 列表分页展示
	 * @param  t,offset, limit
	 * @return
	 */
	protected IPage<T> page(T t,int offset, int limit ) {
		QueryWrapper<T> wrapper = new QueryWrapper<>(t);
		return page(wrapper, offset, limit);
	}


	/**
	 * 列表分页展示
	 * @param   wrapper,offset, limit
	 * @return
	 */
	protected IPage<T> page(QueryWrapper<T> wrapper,int offset, int limit ) {
		int pageNum =offset/limit + 1;
		PageInfo<T> pageInfo = new PageInfo<>();
		pageInfo.setSize(limit);
		pageInfo.setCurrent(pageNum);
		IPage<T> page = service.page(pageInfo,wrapper);
		return page;
	}

	/**
	 * 列表数据
	 * @return
	 */
	protected List<T> selectList(){
		return this.selectList(null);
	}

	/**
	 * 列表数据
	 * @return
	 */
	protected List<T> selectList(QueryWrapper<T> wrapper){
		return service.list(wrapper);
	}


	/**
	 * 新增或修改
	 * @param  t
	 * @return
	 */
	protected M insertOrUpdate(T t) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}

		if(service.saveOrUpdate(t)){
			return M.ok();
		}
		return M.error();
	}

	/**
	 * 单个删除
	 * @param  id
	 * @return
	 */
	protected M delete(Long id) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(service.removeById(id)){
			return M.ok();
		}
		return M.error();
	}

	/**
	 * 批量删除
	 * @param  ids
	 * @return
	 */
	protected M batchDelete(@RequestParam("ids[]") Long[] ids) {
		if (SystemConst.DEMO_ACCOUNT.equals(getUsername())) {
			return M.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(service.removeByIds(Arrays.asList(ids))){
			return M.ok();
		}
		return M.error();
	}


}