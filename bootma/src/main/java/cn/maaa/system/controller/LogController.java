package cn.maaa.system.controller;

import cn.maaa.common.annotation.OperLog;
import cn.maaa.system.domain.Log;
import cn.maaa.common.utils.M;
import cn.maaa.common.utils.PageInfo;
import cn.maaa.system.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 日志
 * @author mazh
 * @date 2019年02月26日 14:33 
 */
@RequestMapping("common/log")
@Controller
public class LogController {
	@Autowired
	LogService logService;

	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}


	@GetMapping("/list")
	@ResponseBody
	public IPage<Log> list(Log log,int offset, int limit ) {
		int pageNum =offset/limit + 1;
		PageInfo<Log> pageInfo = new PageInfo<>();
		pageInfo.setSize(limit);
		pageInfo.setCurrent(pageNum);
		QueryWrapper<Log> wrapper = new QueryWrapper<>(log)
										.orderByDesc("gmt_create");
		IPage<Log> page = logService.page(pageInfo,wrapper);
		return page;
	}

	@OperLog("删除日志")
	@PostMapping("/remove")
	@ResponseBody
	M remove(Long id) {
		if(logService.removeById(id)){
			return M.ok();
		}
		return M.error();
	}

	@OperLog("批量删除日志")
	@PostMapping("/batchRemove")
	@ResponseBody
	M batchRemove(@RequestParam("ids[]") Long[] ids) {
		if(logService.removeByIds(Arrays.asList(ids))){
			return M.ok();
		}
		return M.error();
	}
}
