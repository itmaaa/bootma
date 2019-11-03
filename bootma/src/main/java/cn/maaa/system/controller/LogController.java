package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.common.controller.BaseController;
import cn.maaa.common.utils.M;
import cn.maaa.system.domain.Log;
import cn.maaa.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 日志
 * @author mazh
 * @date 2019年02月26日 14:33 
 */
@RequestMapping("/common/log")
@Controller
public class LogController extends BaseController<Log> {

	LogService logService;

	@Resource
	public void setLogService(LogService logService){
		this.logService = logService;
		super.setService(logService);
	}

	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}


	@GetMapping("/list")
	@ResponseBody
	public IPage<Log> list(Log log,int offset, int limit ) {
		QueryWrapper<Log> wrapper = new QueryWrapper<>(log)
				.orderByDesc("gmt_create");
		return super.page(wrapper,offset,limit );
	}

	@OperLog("删除日志")
	@PostMapping("/remove")
	@ResponseBody
	M remove(Long id) {
		return super.delete(id);
	}

	@OperLog("批量删除日志")
	@PostMapping("/batchRemove")
	@ResponseBody
	M batchRemove(@RequestParam("ids[]") Long[] ids) {
		return super.batchDelete(ids);
	}
}
