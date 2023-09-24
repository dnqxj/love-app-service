package com.orangemust.love.dao;

import com.llqqww.thinkjdbc.D;
import com.llqqww.thinkjdbc.M;
import com.orangemust.love.entity.Resources;
import lombok.SneakyThrows;

import java.util.Map;

public class ResourcesDao {

  /**
   * 可对用户展示的数据字段
   */
  private static String field = "id, uid, uuid, original_name, name, url_path, path, ext, create_time";

  /**
   * 通过ID查询
   */
  @SneakyThrows
  public static Resources getById(Long id) {
    return D.M(Resources.class).field(field).find("id", id);
  }

  /**
   * 根据参数查询
   */
  @SneakyThrows
  public static Resources getResources(Map<String, Object> params) {
    M m = D.M(Resources.class).field(field);
    if (params.get("uid") != null && params.get("uuid") != null) {
      m.where("uid=? and uuid=?", params.get("uid"), params.get("uuid"));
    } else if (params.get("id") != null) {
      m.where("id=?", params.get("id"));
    } else if (params.get("uuid") != null) {
      m.where("uuid=?", params.get("uuid"));
    }

    return m.find();
  }

  /**
   * 新增
   */
  @SneakyThrows
  public static long add(Resources resources) {
    return D.M(resources).add();
  }

}
