package com.example.demo.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value="账单月详情数据", description="" )
@Data
public class BookeepMonthDetailsModel {

    @ApiModelProperty(value = "每日统计列表")
    private List<BookeepDayModel> dayList;

    @ApiModelProperty(value = "该月统计数据")
    private BookeepMonthInfoModel info;
}
