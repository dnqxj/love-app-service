package com.orangemust.love.service;

import com.orangemust.love.entity.Resources;

public interface ResourcesService {

  /**
   * 查询数据
   */
  Resources queryObject(Long id);

  /**
   * 查询数据
   */
  Resources queryObjectByUidAndUUid(Long uid, String uuid);

  /**
   * 新增数据
   */
  Long add(Long uid, String uuid, String originalName, String name, String urlPath, String path, String ext);

}
