package com.orangemust.love.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "账单月统计数据", description = "")
@Data
public class BookeepMonthInfoModel {

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "年份")
    private Integer month;

    @ApiModelProperty(value = "当月支出")
    private Double expenditureTotal;

    @ApiModelProperty(value = "当月收入")
    private Double incomeTotal;
}
