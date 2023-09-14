package com.orangemust.love.model;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import com.orangemust.love.swagger.annotation.ApiGroupProperty;
import com.orangemust.love.validate.group.DateReminderGroups;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "日期提醒", description = "纪念日日期提醒")
@Getter
@Setter
@Table(name = "t_date_reminder")
public class DateReminderModel {

    @ApiGroupProperty({ DateReminderGroups.Update.class, DateReminderGroups.Delete.class })
    @NotNull(message = "ID不能为空", groups = { DateReminderGroups.Delete.class, DateReminderGroups.Update.class })
    @ApiModelProperty(value = "ID", required = true)
    @Column(isKey = true)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiGroupProperty({ DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    @ApiModelProperty(value = "日期类型，0：阴历，1：阳历")
    @NotNull(message = "日期类型不能为空", groups = { DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    private Integer type;

    @ApiGroupProperty({ DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    @ApiModelProperty(value = "妹妹姓名")
    @NotBlank(message = "妹妹姓名不能为空", groups = { DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    private String name;

    @ApiGroupProperty({ DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    @ApiModelProperty(value = "妹妹生日")
    @NotBlank(message = "妹妹生日不能为空", groups = { DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    private String date;

    @ApiGroupProperty({ DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    @ApiModelProperty(value = "通知日期")
    @NotBlank(message = "通知日期不能为空", groups = { DateReminderGroups.Insert.class, DateReminderGroups.Update.class })
    @Column(name = "message_day")
    private String messageDay;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Long updateTime;
}
