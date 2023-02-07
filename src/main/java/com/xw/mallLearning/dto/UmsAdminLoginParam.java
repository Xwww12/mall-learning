package com.xw.mallLearning.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UmsAdminLoginParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
