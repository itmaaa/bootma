package cn.maaa.system.domain;

import cn.maaa.common.annotation.Convert;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = -5560443196867060661L;
	//
	private Long id;

	// 父菜单ID，一级菜单为0
	private Long parentId;
	// 菜单名称
	@Convert("text")
	private String name;
	// 菜单URL
	private String url;
	// 授权(多个用逗号分隔，如：user:list,user:create)
	private String perms;
	// 类型 0：目录 1：菜单 2：按钮
	private Integer type;
	// 菜单图标
	private String icon;
	// 排序
	private Integer orderNum;
	// 创建时间
	private Date gmtCreate;
	// 修改时间
	private Date gmtModified;

}
