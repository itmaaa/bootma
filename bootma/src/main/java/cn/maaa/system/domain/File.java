package cn.maaa.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件上传
 */
@Data
@NoArgsConstructor
@TableName("sys_file")
public class File implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    // 文件类型
    private Integer type;
    // URL地址
    private String url;
    // 创建时间
    private Date createDate;

    public File(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
