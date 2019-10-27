package cn.maaa.system.dto;

import cn.maaa.system.domain.User;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述信息
 *
 * @author maaa
 * @date 2019年09月07日 15:56
 */
@Data
public  class UserDTO implements Serializable {
    private static final long serialVersionUID = -5559180775779609615L;
    private User user = new User();
    private String pwdOld;
    private String pwdNew;
}
