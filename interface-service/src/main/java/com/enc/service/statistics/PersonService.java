package com.enc.service.statistics;

import com.enc.domain.platform.vo.statistics.PersonListVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PersonService {

    PageInfo getPersonList(PersonListVo vo);

}
