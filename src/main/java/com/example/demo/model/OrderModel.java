package com.example.demo.model;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "t_order")
public class OrderModel {

    @Column(isKey = true)
    private Long id;
    private Long uid;
    private Long gid;
    private Double price;
    private Long count;
    private Double total;
    @Column(name = "pay_status")
    private String payStatus;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "create_time")
    private Long createTime;
    @Column(name = "update_time")
    private Long updateTime;
}