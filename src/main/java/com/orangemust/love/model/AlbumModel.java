package com.orangemust.love.model;

import com.llqqww.thinkjdbc.Column;
import com.llqqww.thinkjdbc.Table;
import com.orangemust.love.swagger.annotation.ApiGroupProperty;
import com.orangemust.love.validate.group.AlbumGroups;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "相册", description = "相册数据")
@Getter
@Setter
@Table(name = "t_album")
public class AlbumModel {

    @ApiGroupProperty({ AlbumGroups.Delete.class })
    @NotNull(message = "ID不能为空", groups = { AlbumGroups.Delete.class })
    @ApiModelProperty(value = "ID", example = "", required = true, notes = "ID")
    @Column(isKey = true)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long uid;

    @ApiGroupProperty({ AlbumGroups.Insert.class })
    @NotBlank(message = "图片名称不能为空", groups = { AlbumGroups.Insert.class })
    @ApiModelProperty(value = "图片名称")
    private String name;

    @ApiGroupProperty({ AlbumGroups.Insert.class })
    @NotBlank(message = "图片描述不能为空", groups = { AlbumGroups.Insert.class })
    @ApiModelProperty(value = "图片描述")
    private String details;

    // @ApiGroupProperties({
    // @ApiGroupProperty(value = {GroupsUser.Save.class}, description = "姓名(描述变了)"),
    // @ApiGroupProperty(value = {GroupsUser.Update.class})
    // })
    //
    @ApiGroupProperty({ AlbumGroups.Insert.class })
    @NotBlank(message = "图片的UUID不能为空", groups = { AlbumGroups.Insert.class })
    @ApiModelProperty(value = "图片的UUID")
    @Column(name = "resources_uuid")
    private String resourcesUuid;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Long updateTime;

    @ApiModelProperty(value = "图片url地址")
    @Column(isColumn = false)
    private String urlPath;
}
