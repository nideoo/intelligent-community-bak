package com.enc.service.impl;

import com.enc.dao.mapper.RecordsMapper;
import com.enc.domain.access.entity.TRecords;
import com.enc.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    RecordsMapper recordsMapper;

    @Override
    public void addTRecords(TRecords records) {
        recordsMapper.addTRecords(records);
    }
}
