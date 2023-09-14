package com.orangemust.love.controller;

import com.llqqww.thinkjdbc.D;
import com.orangemust.love.model.ResourcesModel;
import com.orangemust.love.response.Result;
import com.orangemust.love.utils.FuncUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @description: 上传文件控制器
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/19 4:09 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/19 4:09 下午
 * @updateRemark:
 * @version: 1.0
 **/
@Api(tags = "文件上传")
@RequestMapping(path = "/file")
@RestController
public class Upload {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @ApiOperation(value = "单个文件上传", notes = "上传单个文件，通过携带的token", httpMethod = "POST")
    @PostMapping("/upload")
    public Result upload(
            @ApiParam(name = "file", value = "file", required = true) @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        if (file.isEmpty()) {
            return Result.error().message("未选择文件");
        }
        HashMap<String, Object> map = saveFile(file);
        if (map != null) {
            // 保存到数据库
            Object userId = request.getAttribute("userId");
            long uid = Long.valueOf(String.valueOf(userId));
            String uuid = FuncUtil.getUUID();

            ResourcesModel resourcesModel = new ResourcesModel();
            resourcesModel.setUid(uid);
            resourcesModel.setUuid(uuid);
            resourcesModel.setOriginalName(map.get("original_name").toString());
            resourcesModel.setName(map.get("name").toString());
            resourcesModel.setUrlPath(map.get("url_path").toString());
            resourcesModel.setPath(map.get("path").toString());
            resourcesModel.setExt(map.get("ext").toString());
            resourcesModel.setCreateTime(FuncUtil.getTimeStamp());
            long id = 0;
            try {
                id = D.M(resourcesModel).add();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (id != 0) {
                map.put("uuid", uuid);
                return Result.ok().message("上传成功").data(map);
            } else {
                return Result.error().message("上传失败");
            }

        } else {
            return Result.error().message("上传失败");
        }
    }

    // @ApiOperation(value = "多个文件上传", notes = "上传多个文件，通过携带的token", httpMethod =
    // "POST")
    // @PostMapping("/multiUpload")
    // public Object multiUpload(@ApiParam(name="file[]",value="file",required=true)
    // @RequestParam("file")MultipartFile[] files){
    // System.out.println("文件的个数:"+files.length);
    // for (MultipartFile f : files){
    // saveFile(f);
    // }
    // return Result.ok().message("上传成功");
    // }

    private HashMap<String, Object> saveFile(MultipartFile file) {
        // 处理存放文件的目录
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            String format = sdf.format(new Date());
            File uploadFolder = new File(path.getAbsolutePath(), "static/upload/" + format);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
                System.out.println(uploadFolder.getAbsolutePath());
                // 在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/upload/
                // 在打成jar正式发布时，得到的地址为:{发布jar包目录}/static/images/upload/
            }
            String uploadFilePath = uploadFolder.getAbsolutePath();
            String filename = file.getOriginalFilename(); // 获取上传文件原来的名称
            String ext = getExt(filename);
            String newFilename = getNewFileName(filename);
            File localFile = new File(uploadFilePath + '/' + newFilename);
            System.out.println(localFile);
            file.transferTo(localFile); // 把上传的文件保存至本地static/upload

            HashMap<String, Object> map = new HashMap();
            map.put("original_name", filename);
            map.put("name", localFile.getName());
            map.put("url_path", "/upload/" + format + newFilename);
            map.put("path", "static/upload/" + format + newFilename);
            map.put("ext", ext);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 新文件名
    public String getNewFileName(String filename) {
        String extension = getExt(filename);
        Long timeMillis = System.currentTimeMillis();
        String md5Str = DigestUtils.md5DigestAsHex(timeMillis.toString().getBytes());
        return md5Str + extension;
    }

    public String getExt(String filename) {
        String extension = filename.substring(filename.lastIndexOf("."));
        return extension;
    }
}