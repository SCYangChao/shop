package com.shop.upms.api.model.dto.response.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@ApiModel(value = "文件上传返回",description = "文件上传返回")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse implements Serializable {

    @ApiModelProperty(value = "url", required = true)
    private String url;

    @ApiModelProperty(value = "文件名称", required = true)
    private String fileName;


}
