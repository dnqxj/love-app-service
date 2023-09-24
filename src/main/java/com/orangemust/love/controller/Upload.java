package com.orangemust.love.controller;

import com.orangemust.core.utils.ImageUtil;
import com.orangemust.core.utils.StringUtil;
import com.orangemust.core.response.Result;
import com.orangemust.core.utils.FuncUtil;
import com.orangemust.love.service.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Api(tags = "文件上传")
@RequestMapping(path = "/file")
@RestController
public class Upload {

    @Value("${path.uploadPath}")
    String pathUploadPath;

    @Value("${path.importantPath}")
    String pathImportantPath;

    @Value("${path.urlPath}")
    String pathUrlPath;

    private final ResourcesService resourcesService;

    public Upload(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @ApiOperation(value = "单个文件上传", notes = "上传单个文件，大小不超过10MB，如果是图片文件，不能大于2M,参数：important:1时保存到勿删目录", httpMethod = "POST")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return Result.error().message("未选择文件");
        }
        Boolean important = false;
        if (request.getParameter("important") != null && Objects.equals(request.getParameter("important"), "1")) {
            important = true;
        }
        Long uid = FuncUtil.getUid(request);
        // 上传文件名
        String originalName = file.getOriginalFilename();
        // 新文件名
        assert originalName != null;
        String ext = StringUtil.getExt(originalName);

        String newFilename = StringUtil.getRandomMd5() + ext;
        // 拼接目标文件路径
        String YMDPath = StringUtil.getYMDDirectory();
        // 绝对路径
        String absolutePath = pathUploadPath + YMDPath + newFilename;
        if (important) {
            absolutePath = pathUploadPath + pathImportantPath + YMDPath + newFilename;
        }
        File newFilePath = new File(absolutePath);
        // 路径不存在就创建一个路径
        if (!newFilePath.getParentFile().exists()) {
            boolean isCreate = newFilePath.getParentFile().mkdirs();
            if (!isCreate) {
                return Result.error().message("文件上传失败");
            }
        }
        // 将上传文件保存到一个目标文件中
        try {
            file.transferTo(newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error().message("文件上传失败");
        }

        // 文件上传后，如果为图片文件
        if (ImageUtil.isImage(newFilePath)) {
            // 图片大小限制
            if (file.getSize() >= 2097152) {
                return Result.error().message("图片文件不能大于2M～");
            }
        }

        // 保存到数据库
        String urlPath = pathUrlPath + YMDPath + newFilename;
        if (important) {
            urlPath = pathUrlPath + pathImportantPath + YMDPath + newFilename;
        }
        Long id = resourcesService.add(uid, StringUtil.getUUID(), originalName, newFilename, urlPath, absolutePath,
                ext);
        return Result.ok().message("上传成功").data(resourcesService.queryObject(id));
    }
}