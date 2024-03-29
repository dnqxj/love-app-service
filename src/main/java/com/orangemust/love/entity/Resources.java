package com.orangemust.love.entity;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "t_resources")
public class Resources {

    @Column(isKey = true)
    private Long id;

    private Long uid;

    private String uuid;

    @Column(name = "original_name")
    private String originalName;

    private String name;

    @Column(name = "url_path")
    private String urlPath;

    private String path;

    private String ext;

    @Column(name = "create_time")
    private Long createTime;
}
