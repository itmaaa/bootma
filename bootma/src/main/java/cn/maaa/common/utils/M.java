package cn.maaa.common.utils;

import java.util.HashMap;
import java.util.Map;
/*
* 状态返回类
* */
public class M extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public static M ok() {
		return new M();
	}

	public M() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public static M ok(String msg) {
		M m = new M();
		m.put("msg", msg);
		return m;
	}

	public static M ok(Map<String, Object> map) {
		M m = new M();
		m.putAll(map);
		return m;
	}


	public static M error() {
		return error("操作失败");
	}

	public static M error(String msg) {
		return error(500, msg);
	}

	public static M error(int code, String msg) {
		M m = new M();
		m.put("code", code);
		m.put("msg", msg);
		return m;
	}



}
