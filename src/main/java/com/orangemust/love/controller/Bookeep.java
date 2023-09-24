package com.orangemust.love.controller;

import com.llqqww.thinkjdbc.D;
import com.orangemust.core.response.Result;
import com.orangemust.core.swagger.annotation.ApiGroup;
import com.orangemust.love.entity.BookeepDayModel;
import com.orangemust.love.entity.BookeepModel;
import com.orangemust.love.request.BookeepMonthDetailsModel;
import com.orangemust.love.request.BookeepMonthInfoModel;
import com.orangemust.core.utils.FuncUtil;
import com.orangemust.love.validate.group.BookeepGroups;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Api(tags = "记账模块")
@RestController
@RequestMapping("/bookeep")
public class Bookeep {

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BookeepMonthDetailsModel.class),
    })
    @ApiOperation(value = "查询月份详情")
    @GetMapping(path = "/month_details")
    public Result monthDetails(
            @ApiGroup(BookeepGroups.MonthDetails.class) @Validated(BookeepGroups.MonthDetails.class) BookeepModel bookeepModel,
            HttpServletRequest request) {
        // 用户ID
        Long uid = FuncUtil.getUid(request);
        Integer year = bookeepModel.getYear();
        Integer month = bookeepModel.getMonth();
        List<BookeepModel> list = null;
        try {
            list = D.M(BookeepModel.class).where("uid=? and year=? and month=?", uid, year, month).select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null) {
            return Result.error().message("获取失败，请刷新重试");
        }

        // 处理list，返回前端所需的数据结构
        BookeepMonthInfoModel info = new BookeepMonthInfoModel();
        info.setYear(year);
        info.setMonth(month);
        Double expenditureTotal = 0.0;
        Double incomeTotal = 0.0;
        // 处理list，整合同一天的数据
        List<BookeepDayModel> dayList = new ArrayList<>();
        HashMap<String, BookeepDayModel> dayMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            // 统计当月总和
            BookeepModel bookeepModelItem = list.get(i);
            if (Objects.equals(bookeepModelItem.getType(), "expenditure")) {
                expenditureTotal += bookeepModelItem.getTotal();
            } else if (Objects.equals(bookeepModelItem.getType(), "income")) {
                incomeTotal += bookeepModelItem.getTotal();
            }

            String date = year.toString() + '-' + month.toString() + '-' + bookeepModelItem.getDay().toString();
            BookeepDayModel bookeepDayModel = new BookeepDayModel();
            // 该日期没有数据
            if (dayMap.get(date) == null) {
                bookeepDayModel.setDate(date);
                if (Objects.equals(bookeepModelItem.getType(), "expenditure")) {
                    bookeepDayModel.setExpenditure(bookeepModelItem.getTotal());
                    bookeepDayModel.setIncome(0.0);
                } else if (Objects.equals(bookeepModelItem.getType(), "income")) {
                    bookeepDayModel.setIncome(bookeepModelItem.getTotal());
                    bookeepDayModel.setExpenditure(0.0);
                }
                List<BookeepModel> children = new ArrayList<>();
                children.add(bookeepModelItem);
                bookeepDayModel.setChildren(children);
                dayMap.put(date, bookeepDayModel);
            } else {
                // 循环中已存在该日期的数据
                bookeepDayModel = dayMap.get(date);
                if (Objects.equals(bookeepModelItem.getType(), "expenditure")) {
                    // 累加数据
                    bookeepDayModel.setExpenditure(bookeepDayModel.getExpenditure() + bookeepModelItem.getTotal());
                } else if (Objects.equals(bookeepModelItem.getType(), "income")) {
                    bookeepDayModel.setIncome(bookeepDayModel.getIncome() + bookeepModelItem.getTotal());
                }
                bookeepDayModel.getChildren().add(bookeepModelItem);
                dayMap.replace(date, bookeepDayModel);
            }
        }

        // 将处理后的日期：对象 数据，放到列表中
        BookeepDayModel bookeepDayModelNew = new BookeepDayModel();
        for (String key : dayMap.keySet()) {
            // 处理小数点
            bookeepDayModelNew = dayMap.get(key);
            bookeepDayModelNew.setExpenditure(FuncUtil.twoDouble((bookeepDayModelNew.getExpenditure())));
            bookeepDayModelNew.setIncome(FuncUtil.twoDouble((bookeepDayModelNew.getIncome())));
            dayList.add(bookeepDayModelNew);
        }
        info.setExpenditureTotal(FuncUtil.twoDouble(expenditureTotal));
        info.setIncomeTotal(FuncUtil.twoDouble(incomeTotal));

        HashMap<String, Object> hashMap = new HashMap<>();
        // hashMap.put("list", list);
        hashMap.put("info", info);
        hashMap.put("dayList", dayList);
        return Result.ok().data(hashMap);
    }

    // 新增账务记录
    @ApiOperation(value = "新增账务记录")
    @PostMapping("add")
    public Result add(
            @ApiGroup(BookeepGroups.Insert.class) @Validated(BookeepGroups.Insert.class) @RequestBody BookeepModel bookeepModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
        String type = bookeepModel.getType();
        if (!Objects.equals(type, "expenditure") && !Objects.equals(type, "income")) {
            return Result.error().message("财务类型错误");
        }
        Long timeStamp = FuncUtil.getTimeStamp();
        // 避免ID攻击
        bookeepModel.setId(null);
        bookeepModel.setUid(uid);
        bookeepModel.setCreateTime(timeStamp);
        bookeepModel.setUpdateTime(timeStamp);
        Long insertNum = null;
        try {
            insertNum = D.M(bookeepModel).add();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (insertNum == null) {
            return Result.error().message("新增记录失败~");
        }
        return Result.ok().message("新增成功~");
    }

    // 修改
    @ApiOperation(value = "修改账务记录")
    @PostMapping("update")
    public Result update(
            @ApiGroup(BookeepGroups.Update.class) @Validated(BookeepGroups.Update.class) @RequestBody BookeepModel bookeepModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
        String type = bookeepModel.getType();
        if (!Objects.equals(type, "expenditure") && !Objects.equals(type, "income")) {
            return Result.error().message("财务类型错误");
        }
        Long timeStamp = FuncUtil.getTimeStamp();
        Long id = bookeepModel.getId();
        BookeepModel oldBookeepModel = null;
        try {
            oldBookeepModel = D.M(BookeepModel.class).where("id=? and uid=?", id, uid).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (oldBookeepModel == null) {
            return Result.error().message("未找到该记录");
        }

        // 避免修改uid,和create_time
        bookeepModel.setUid(null);
        bookeepModel.setCreateTime(null);
        bookeepModel.setUpdateTime(timeStamp);
        Long updateNum = null;
        try {
            // 使用限定字段方式避免其他字段被修改
            updateNum = D.M(bookeepModel).save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (updateNum == null) {
            return Result.error().message("修改记录失败~");
        }
        return Result.ok().message("修改成功~");
    }

    // 删除
    @ApiOperation(value = "删除财务记录")
    @PostMapping("delete")
    public Result delete(
            @ApiGroup(BookeepGroups.Delete.class) @Validated(BookeepGroups.Delete.class) @RequestBody BookeepModel bookeepModel,
            HttpServletRequest request) {
        Long uid = FuncUtil.getUid(request);
        Long id = bookeepModel.getId();
        Long deleteNum = null;
        try {
            deleteNum = D.M(BookeepModel.class).where("id=? and uid=?", id, uid).delete();
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