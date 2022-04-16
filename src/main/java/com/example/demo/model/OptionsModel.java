package com.example.demo.model;

import com.example.demo.swagger.annotation.ApiGroupProperty;
import com.example.demo.validate.group.OptionsGroups;
import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "选项", description = "选项数据")
@Getter
@Setter
@Table(name = "t_options")
public class OptionsModel {
    @Column(isKey = true)
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiGroupProperty({OptionsGroups.Detail.class})
    @NotBlank(message = "选项类型不能为空", groups = {OptionsGroups.Detail.class})
    @ApiModelProperty(value = "选项类型", required = true)
    private String type;

    @ApiModelProperty(value = "选项名称")
    private String label;

    @ApiModelProperty(value = "选项的值")
    private String value;
}
