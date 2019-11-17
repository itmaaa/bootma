package cn.maaa.system.service;

import cn.maaa.system.controller.UserController;
import cn.maaa.system.domain.User;
import cn.maaa.system.dto.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService extends IService<User> {
    boolean saveUser(User user);

    void resetPwd(UserDTO userDTO, User user);

    void adminResetPwd(UserDTO userDTO);

    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
