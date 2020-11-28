package com.enc.service.common;

import java.util.List;
import java.util.Map;

public interface DictionariesService {

    /***
     * 获取字典表所有类型
     * @return
     */
    List<Map<String,Object>> getDictionaryTypeList();

    /***
     * 根据类型获取状态列表
     * @param type
     * @return
     */
    List<Map<String,Object>> getDictionaryByTypeList(String type);

}
