package com.example.demo.controller;

import com.example.demo.model.OptionsModel;
import com.example.demo.response.Result;
import com.example.demo.swagger.annotation.ApiGroup;
import com.example.demo.validate.group.OptionsGroups;
import com.llqqww.thinkjdbc.D;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api(tags = "获取选项数据")
@RestController
@RequestMapping("/options")
public class Options {

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = OptionsModel.class),
    })
    @ApiOperation(value = "获取账务类型")
    @GetMapping(path = "bookeep_type")
    public Result bookeepType() {
        List<OptionsModel> optionsModelList = null;
        try {
            optionsModelList = D.M(OptionsModel.class).where("type=?", "bookeep_type").select();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (optionsModelList != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("list", optionsModelList);
            return Result.ok().data(hashMap);
        } else {
            return Result.error().message("获取失败~");
        }
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = OptionsModel.class),
    })
    @ApiOperation(value = "根据类型，获取分类")
    @GetMapping(path = "classify")
    public Result classify(@ApiGroup(OptionsGroups.Detail.class) @Validated(OptionsGroups.Detail.class) OptionsModel optionsModel) {
        String type = optionsModel.getType();
        List<OptionsModel> optionsModelList = null;
        try {
            optionsModelList = D.M(OptionsModel.class).where("type=?", type).select();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (optionsModelList != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("list", optionsModelList);
            return Result.ok().data(hashMap);
        } else {
            return Result.error().message("获取失败~");
        }
    }
}
