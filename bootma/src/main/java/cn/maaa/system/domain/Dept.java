package cn.maaa.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 部门对象
 * @author maaa
 * @date 2019年03月01日 22:37
 */
@Data
@TableName("sys_dept")
@Accessors(chain = true)
public class Dept implements Serializable {
    private static final long serialVersionUID = -5613141947806864517L;

    //
    private Long id;
    //上级部门ID，一级部门为0
    private Long parentId;
    //部门名称
    private String name;
    //排序
    private Integer orderNum;
    //是否删除  -1：已删除  0：正常
    private Integer delFlag;
}
