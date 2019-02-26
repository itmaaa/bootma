package cn.maaa.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志对象
 * @author mazh
 * @date 2019年02月26日 11:22 
 */
@Data
@TableName("sys_log")
@Accessors(chain = true)
public class Log implements Serializable {
	private static final long serialVersionUID = -761345648026815064L;

	private Long id;

	private Long userId;

	private String username;

	private String operation;

	private String time;

	private String method;

	private String params;

	private String ip;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date gmtCreate;
}
