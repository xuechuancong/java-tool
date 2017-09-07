package org.jeff.javatool.tool.myactiviti.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 新增表单实例与附件的关系
 * Created by weijianfu on 2017/4/27.
 */
public class TProcAttAdd implements Serializable {

    private Long formInstId;// 表单实例Id

    private MultipartFile file;//  文件

    public Long getFormInstId() {
        return formInstId;
    }

    public void setFormInstId(Long formInstId) {
        this.formInstId = formInstId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
