package com.enc.dao.mapper.statistics;

import com.enc.domain.platform.vo.statistics.PersonListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/***
 *
 */
@Mapper
public interface PersonMapper {

    List<Map<String,Object>> getPersonListSql(PersonListVo vo);

}
