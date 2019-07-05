package cn.maaa.common.logback.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * LogWebMvcConfigurer
 * @author mazh
 * @date 2019年06月14日 17:25 
 */
@Configuration
public class LogWebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Bean
	public HandlerInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
