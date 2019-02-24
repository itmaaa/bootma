package cn.maaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication // 代表为SpringBoot应用的运行主类
@MapperScan("cn.maaa.**.mapper")
@EnableCaching
public class BootmaApplication {
	public static void main(String[] args) {
		/** 运行SpringBoot应用 */
		SpringApplication.run(BootmaApplication.class, args);
	}
}
