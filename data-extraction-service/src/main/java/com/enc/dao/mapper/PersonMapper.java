package com.enc.dao.mapper;

import com.enc.domain.access.entity.CardInfoEntity;
import com.enc.domain.access.entity.DetailInfoEntity;
import com.enc.domain.access.entity.EntourageEntity;
import com.enc.domain.access.entity.PersonsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {
    //新增人员信息接口
    int addPerson(PersonsEntity vo);
    //新增卡号（CardInfo）信息接口
    int addCardInfo(CardInfoEntity cardInfoEntity);
    //新增人员详细信息接口
    int addDetailInfo(DetailInfoEntity detailInfoEntity);
    //新增伴随人员信息接口
    int addEntourage(EntourageEntity entourageEntity);
    //通过页数值获取本地人员信息并返回
    List<PersonsEntity> getLocalPersons(Map map);
    //删除所有人数据
    void deletePerson(int personId);
    //删除所有人详细信息数据
    void deleteDetailInfo(int personId);
    //删除所有卡信息数据
    void deteleCardInfo(int personId);
    //删除所有随行信息数据
    void deleteEntourage(int personId);

    // 获取去重的所有人员名称
    List<Map<String,Object>> getPersDisNameList();

    int personCountById(String id);

}
