package com.orangemust.love.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import com.orangemust.love.model.BookeepModel;

@ApiModel(value = "每日统计数据", description = "")
@Data
public class BookeepDayModel {

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "当日消费统计")
    private Double expenditure;

    @ApiModelProperty(value = "当日支出统计")
    private Double income;

    @ApiModelProperty(value = "当天的账单列表")
    private List<BookeepModel> children;
}
