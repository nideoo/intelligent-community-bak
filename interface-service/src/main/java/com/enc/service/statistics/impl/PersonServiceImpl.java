package com.enc.service.statistics.impl;

import com.enc.dao.mapper.statistics.PersonMapper;
import com.enc.domain.platform.vo.statistics.PersonListVo;
import com.enc.service.statistics.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: BOND
 * @description:
 * @create: 2019-05-15 17:29
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public PageInfo getPersonList(PersonListVo vo) {
        PageHelper.startPage(vo.getPageIndex(),vo.getPageSize());
        List<Map<String,Object>> list = personMapper.getPersonListSql(vo);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}