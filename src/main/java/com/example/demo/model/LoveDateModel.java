package com.example.demo.model;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "t_love_date")
public class LoveDateModel {
    @Column(isKey = true)
    private Long id;
    @NotNull(message = "用户id不能为空")
    private Long uid;
    @NotNull(message = "日期类型不能为空")
    private Integer type;
    @NotBlank(message = "妹妹姓名不能为空")
    private String name;
    @NotBlank(message = "妹妹生日不能为空")
    private String date;
    @NotBlank(message = "通知日期不能为空")
    @Column(name = "message_day")
    private String messageDay;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
}
