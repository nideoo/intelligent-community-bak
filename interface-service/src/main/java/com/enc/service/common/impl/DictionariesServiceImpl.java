package com.enc.service.common.impl;

import com.enc.dao.mapper.common.DictionariesMapper;
import com.enc.service.common.DictionariesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-21 20:48
 */
@Slf4j
@Service
public class DictionariesServiceImpl implements DictionariesService {

    @Autowired
    DictionariesMapper dictionariesMapper;

    @Override
    public List<Map<String, Object>> getDictionaryTypeList() {
        return dictionariesMapper.getDictionaryTypeListSql();
    }

    @Override
    public List<Map<String, Object>> getDictionaryByTypeList(String type) {
        return dictionariesMapper.getDictionaryByTypeListSql(type);
    }
}