package com.enc.dao.mapper.common;

import java.util.List;
import java.util.Map;

public interface DictionariesMapper {

    /***
     * 获取字典表所有类型
     * @return
     */
    List<Map<String,Object>> getDictionaryTypeListSql();

    /***
     * 根据类型获取状态列表
     * @param type
     * @return
     */
    List<Map<String,Object>> getDictionaryByTypeListSql(String type);

}
