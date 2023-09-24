package com.orangemust.love.controller;

import com.llqqww.thinkjdbc.D;
import com.orangemust.core.response.Result;
import com.orangemust.core.swagger.annotation.ApiGroup;
import com.orangemust.love.entity.DateReminderModel;
import com.orangemust.core.utils.FuncUtil;
import com.orangemust.love.validate.group.DateReminderGroups;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(tags = "日期提醒")
@RestController
@RequestMapping("/date_reminder")
public class DateReminder {

    @ApiOperation(value = "获取日期提醒列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = DateReminderModel.class),
    })
    @GetMapping(path = "list")
    public Result list(HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
        List<DateReminderModel> list;
        try {
            list = D.M(DateReminderModel.class).where("uid=?", uid).select();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("list", list);
            return Result.ok().data(hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error().message("获取失败~");
    }

    @ApiOperation(value = "新增日期提醒")
    @PostMapping(path = "add")
    public Result add(
            @ApiGroup(DateReminderGroups.Insert.class) @Validated(DateReminderGroups.Insert.class) @RequestBody DateReminderModel dateReminderModel,
            HttpServletRequest request) {

        Long uid = FuncUtil.getUid(request);
        Long timeStamp = FuncUtil.getTimeStamp();
        // 清除前端可能传递的ID
        dateReminderModel.setId(null);
        dateReminderModel.setUid(uid);
        dateReminderModel.setCreateTime(timeStamp);
        dateReminderModel.setUpdateTime(timeStamp);
        // 插入数据库
        Long id = null;
        try {
            id = D.M(dateReminderModel).add();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id != 0) {
            return Result.ok().message("添加日期成功");
        } else {
            return Result.error().message("添加日期失败");
        }
    }

    @ApiOperation(value = "修改日期提醒")
    @PostMapping(path = "update")
    public Result update(
            @ApiGroup(DateReminderGroups.Update.class) @Validated(DateReminderGroups.Update.class) @RequestBody DateReminderModel dateReminderModel,
            HttpServletRequest request) {

        Long id = dateReminderModel.getId();
        Long uid = FuncUtil.getUid(request);
        Long timeStamp = FuncUtil.getTimeStamp();
        // 查找是否存在数据
        DateReminderModel oldDateReminderModel = null;
        try {
            oldDateReminderModel = D.M(DateReminderModel.class).where("id=? and uid=?", id, uid).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (oldDateReminderModel == null) {
            return Result.error().message("未找到该记录~");
        }
        dateReminderModel.setUid(uid);
        dateReminderModel.setUpdateTime(timeStamp);
        Long updateNum = null;
        try {
            updateNum = D.M(dateReminderModel).save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (updateNum != null) {
            return Result.ok().message("更新成功");
        }
        return Result.error().message("更新失败");
    }

    // 删除
    @ApiOperation(value = "删除日期提醒")
    @PostMapping("delete")
    public Result delete(
            @ApiGroup(DateReminderGroups.Delete.class) @Validated(DateReminderGroups.Delete.class) @RequestBody DateReminderModel dateReminderModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
        Long id = dateReminderModel.getId();
        Long deleteNum = null;
        try {
            deleteNum = D.M(DateReminderModel.class).where("id=? and uid=?", id, uid).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deleteNum == null) {
            return Result.error().message("删除记录失败~");
        }
        if (deleteNum == 0) {
            return Result.error().message("该记录未找到~");
        }
        return Result.ok().message("删除成功~");
    }
}
