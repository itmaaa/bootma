package cn.maaa.system.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 * @author mazh
 * @date 2019年02月27日 11:18 
 */
@Data
@TableName("sys_role")
@Accessors(chain = true)
public class Role implements Serializable {
	private static final long serialVersionUID = 2519104339668182311L;
    //角色id
	private Long id;
	//角色名称
	private String roleName;
	//角色标识
	private String roleSign;
	//角色备注
	private String remark;
	//创建用户id
	private Long userIdCreate;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	//菜单ids
	@TableField(select = false)
	private List<Long> menuIds;
}
