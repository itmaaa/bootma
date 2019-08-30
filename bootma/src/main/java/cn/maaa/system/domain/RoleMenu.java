package cn.maaa.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
public class RoleMenu {
	private Long id;
	private Long  roleId;
	private Long menuId;
	
}
