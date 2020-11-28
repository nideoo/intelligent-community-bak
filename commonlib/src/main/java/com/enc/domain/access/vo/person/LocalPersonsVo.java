package com.enc.domain.access.vo.person;

import com.enc.domain.access.entity.PersonsEntity;
import lombok.Data;

import java.util.List;

@Data
public class LocalPersonsVo {
    private int page;
    private int count;
    private List<PersonsEntity> localPersonsVoList;

}
