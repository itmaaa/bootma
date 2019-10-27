package cn.maaa.system.service;

import cn.maaa.system.domain.File;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件上传
 */
public interface FileService extends IService<File> {

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);
}
