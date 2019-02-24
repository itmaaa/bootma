package cn.maaa.common.controller;


import cn.maaa.common.utils.ShiroUtils;
import cn.maaa.system.domain.User;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public User getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}