package cn.maaa.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
		/** 查询 */
		@GetMapping("/test")
		public String testFreeMarker(Model model){
			/** 添加响应数据 */
			model.addAttribute("tip", "测试数据");
			/** 返回视图 */
			return "test";
		}
		
		@Autowired
		private Environment env;
		
		@Value("${server.port}")
		private String port;
		
		@Value("${server.context-path}")
		private String path;
		
		
		@ResponseBody
		@GetMapping("/hello")
		public String hello(){
			System.out.println(env.getProperty("server.port"));
			System.out.println(env.getProperty("server.context-path"));
			
			System.out.println("@value-port="+port);
			System.out.println("@value-context-path="+path);
			return "Hello World";
		}

}
