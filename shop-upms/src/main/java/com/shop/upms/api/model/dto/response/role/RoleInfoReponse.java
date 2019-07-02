package com.shop.upms.api.model.dto.response.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class RoleInfoReponse implements Serializable {

    @ApiModelProperty("名稱")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("資源ＩＤ")
    private List<Long> mids;

    @ApiModelProperty("resouceCodes")
    private List<String> resouceCodes;

    @ApiModelProperty(value = "ID")
    private Long id;
}
