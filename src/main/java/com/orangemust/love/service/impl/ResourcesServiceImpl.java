package com.orangemust.love.service.impl;

import com.orangemust.core.utils.FuncUtil;
import com.orangemust.love.dao.ResourcesDao;
import com.orangemust.love.entity.Resources;
import com.orangemust.love.service.ResourcesService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

  @Override
  public Resources queryObject(Long id) {
    return ResourcesDao.getById(id);
  }

  @Override
  public Resources queryObjectByUidAndUUid(Long uid, String uuid) {
    Map<String, Object> map = new HashMap<>();
    map.put("uid", uid);
    map.put("uuid", uuid);
    return ResourcesDao.getResources(map);
  }

  @Override
  public Long add(Long uid, String uuid, String originalName, String name, String urlPath, String path, String ext) {
    Resources resources = new Resources();
    resources.setUid(uid);
    resources.setUuid(uuid);
    resources.setOriginalName(originalName);
    resources.setName(name);
    resources.setUrlPath(urlPath);
    resources.setPath(path);
    resources.setExt(ext);
    resources.setCreateTime(FuncUtil.getTimeStamp());
    return ResourcesDao.add(resources);
  }

}
