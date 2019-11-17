package cn.maaa.system.service.impl;

import cn.maaa.common.config.BootmaConfig;
import cn.maaa.common.exception.BizException;
import cn.maaa.common.utils.FileType;
import cn.maaa.common.utils.FileUtil;
import cn.maaa.common.utils.ImageUtils;
import cn.maaa.common.utils.MD5Utils;
import cn.maaa.system.domain.File;
import cn.maaa.system.domain.User;
import cn.maaa.system.domain.UserRole;
import cn.maaa.system.dto.UserDTO;
import cn.maaa.system.mapper.UserMapper;
import cn.maaa.system.mapper.UserRoleMapper;
import cn.maaa.system.service.FileService;
import cn.maaa.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

//@CacheConfig(cacheNames = "user")
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BootmaConfig bootmaConfig;

    @Autowired
    private FileService fileService;


    @Transactional
    @Override
    public boolean saveUser(User user) {
        saveOrUpdate(user);
        Long userId = user.getId();
        List<Long> roleIds = user.getRoleIds();
        UpdateWrapper<UserRole> wrapper = new UpdateWrapper<UserRole>().eq("user_id", userId);
        userRoleMapper.delete(wrapper);

        List<UserRole> userRoles = roleIds.parallelStream().map(roleId -> new UserRole(userId, roleId)).collect(Collectors.toList());

        if (userRoles.size() > 0) {
            userRoles.parallelStream().forEach(userRoleMapper :: insert);
        }
        return true;
    }

    @Override
    public void resetPwd(UserDTO userDTO, User user) {
        if (Objects.equals(userDTO.getUser().getId(), user.getId())) {
            if (Objects.equals(MD5Utils.encrypt(user.getUsername(), userDTO.getPwdOld()), user.getPassword())) {
                user.setPassword(MD5Utils.encrypt(user.getUsername(), userDTO.getPwdNew()));
                updateById(user);
            } else {
                throw new BizException("输入的旧密码有误！");
            }
        } else {
            throw new BizException("你修改的不是你登录的账号！");
        }
    }

    @Override
    public void adminResetPwd(UserDTO userDTO) {
        User user = getById(userDTO.getUser().getId());
        if ("admin".equals(user.getUsername())) {
            throw new BizException("超级管理员的账号不允许直接重置！");
        }
        user.setPassword(MD5Utils.encrypt(user.getUsername(), userDTO.getPwdNew()));
         updateById(user);
    }

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        File sysFile = new File(FileType.fileType(fileName), "/files/" + fileName, new Date());
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatar_data.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, bootmaConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        Map<String, Object> result = new HashMap<>();
        if (fileService.save(sysFile)) {
            User user = new User();
            user.setId(userId);
            user.setPicId(sysFile.getId());
            if (updateById(user)) {
                result.put("url", sysFile.getUrl());
            }
        }
        return result;
    }


}
