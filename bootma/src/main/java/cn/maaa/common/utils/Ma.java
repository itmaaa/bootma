package cn.maaa.common.utils;

import java.util.HashMap;
import java.util.Map;
/*
* 状态返回类
* */
public class Ma extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static Ma ok() {
		return new Ma();
	}

	public Ma() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public static Ma ok(String msg) {
		Ma m = new Ma();
		m.put("msg", msg);
		return m;
	}

	public static Ma ok(Map<String, Object> map) {
		Ma m = new Ma();
		m.putAll(map);
		return m;
	}


	public static Ma error() {
		return error("操作失败");
	}

	public static Ma error(String msg) {
		return error(500, msg);
	}

	public static Ma error(int code, String msg) {
		Ma m = new Ma();
		m.put("code", code);
		m.put("msg", msg);
		return m;
	}



}
