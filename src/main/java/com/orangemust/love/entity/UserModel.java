package com.orangemust.love.entity;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "t_user")
public class UserModel {

    @Column(isKey = true)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private Long phone;
    private Double money;
    private Integer gender;
    private String birthday;
    private Integer solar;
    @Column(name = "love_date")
    private String loveDate;
    @Column(name = "vip_date")
    private String vipDate;
    @Column(name = "love_uid")
    private Long loveUid;
    private String date;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
}
