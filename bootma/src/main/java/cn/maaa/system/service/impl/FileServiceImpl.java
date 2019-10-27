package cn.maaa.system.service.impl;

import cn.maaa.common.config.BootmaConfig;
import cn.maaa.system.mapper.FileMapper;
import cn.maaa.system.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;


@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, cn.maaa.system.domain.File> implements FileService {
	@Autowired
	private BootmaConfig bootmaConfig;

    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootmaConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
	}
