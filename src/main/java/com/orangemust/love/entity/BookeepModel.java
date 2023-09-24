package com.orangemust.love.entity;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import com.orangemust.core.swagger.annotation.ApiGroupProperty;
import com.orangemust.love.validate.group.BookeepGroups;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "记账模块", description = "记账数据")
@Getter
@Setter
@Table(name = "t_bookeep")
public class BookeepModel {

        @ApiGroupProperty({ BookeepGroups.Delete.class, BookeepGroups.Update.class })
        @NotNull(message = "ID不能为空", groups = { BookeepGroups.Delete.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "ID", example = "", required = true, notes = "ID")
        @Column(isKey = true)
        private Long id;

        @ApiModelProperty(value = "用户ID")
        private Long uid;

        @ApiGroupProperty({ BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotBlank(message = "账务类型不能为空", groups = { BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "(支出: expenditure/收入: income)", required = true)
        private String type;

        @ApiGroupProperty({ BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotNull(message = "金额不能为空", groups = { BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "金额", required = true)
        private Double total;

        @ApiGroupProperty({ BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotBlank(message = "分类不能为空", groups = { BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "分类", required = true)
        private String classify;

        @ApiGroupProperty({ BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotNull(message = "年份不能为空", groups = { BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "详情")
        private String details;

        @ApiGroupProperty({ BookeepGroups.MonthDetails.class, BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotNull(message = "年份不能为空", groups = { BookeepGroups.MonthDetails.class, BookeepGroups.Insert.class,
                        BookeepGroups.Update.class })
        @ApiModelProperty(value = "年", required = true)
        private Integer year;

        @ApiGroupProperty({ BookeepGroups.MonthDetails.class, BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotNull(message = "月份不能为空", groups = { BookeepGroups.MonthDetails.class, BookeepGroups.Insert.class,
                        BookeepGroups.Update.class })
        @ApiModelProperty(value = "月", required = true)
        private Integer month;

        @ApiGroupProperty({ BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @NotNull(message = "日期不能为空", groups = { BookeepGroups.Insert.class, BookeepGroups.Update.class })
        @ApiModelProperty(value = "日", required = true)
        private Integer day;

        @ApiModelProperty(value = "创建时间")
        @Column(name = "create_time")
        private Long createTime;

        @ApiModelProperty(value = "更新时间")
        @Column(name = "update_time")
        private Long updateTime;
}
