package com.example.demo.controller;

import com.example.demo.model.AlbumModel;
import com.example.demo.model.ResourcesModel;
import com.example.demo.response.Result;
import com.example.demo.swagger.annotation.ApiGroup;
import com.example.demo.utils.FuncUtil;
import com.example.demo.validate.group.AlbumGroups;
import com.llqqww.thinkjdbc.D;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(tags = "相册管理")
@RequestMapping(path = "/album")
@RestController
public class Album {

//    相册列表
    @ApiOperation(value = "获取相册列表数据", notes = "无参数，需要传递token", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = AlbumModel.class),
    })
    @GetMapping("list")
    public Result list(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        try {
                List list = D.M(AlbumModel.class).field("t_album.*, t_resources.url_path as urlPath").join("left join t_resources on resources_uuid=t_resources.uuid").where("t_album.uid=?", userId).select();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("list", list);
            return Result.ok().message("获取成功").data(hashMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("获取失败");
        }
    }


//    添加图片到相册
    @ApiOperation(value = "添加图片到相册", httpMethod = "POST")
    @PostMapping("add")
    public Result add(
            @ApiGroup(AlbumGroups.Insert.class)
            @Validated(AlbumGroups.Insert.class)
            @RequestBody AlbumModel albumModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
//        检查该资源uuid是否真实存在
        String resourcesUuid = albumModel.getResourcesUuid();
        ResourcesModel resourcesModel;
        try {
            resourcesModel = D.M(ResourcesModel.class).where("uid=? and uuid=?",uid, resourcesUuid).find();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message("保存失败~");
        }
        if(resourcesModel == null) {
            return Result.error().message("该图片上传失败，请刷新重试~");
        }

        Long timeStamp = FuncUtil.getTimeStamp();
        //  避免前端传递
        albumModel.setId(null);
        albumModel.setUid(uid);
        albumModel.setCreateTime(timeStamp);
        albumModel.setUpdateTime(timeStamp);
        long id = 0;
        try {
            id = D.M(albumModel).add();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(id != 0) {
            return Result.ok().message("添加图片成功");
        } else {
            return Result.error().message("添加图片失败");
        }
    }

//    删除图片
    @ApiOperation(value = "删除相册中的图片", httpMethod = "POST")
    @PostMapping("/delete")
    public Result delte(
            @ApiGroup(AlbumGroups.Delete.class)
            @Validated(AlbumGroups.Delete.class)
            @RequestBody AlbumModel albumModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
//        检查该资源uuid是否真实存在
        Long id = albumModel.getId();
        AlbumModel findAlbumModel = null;
        try {
            findAlbumModel = D.M(AlbumModel.class).where("uid=? and id=?",uid, id).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(findAlbumModel == null) {
            return Result.error().message("未找到该图片");
        }
//        执行删除
        Long deleteNum = null;
        try {
            deleteNum = D.M(albumModel).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(deleteNum != null) {
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");
    }
}
