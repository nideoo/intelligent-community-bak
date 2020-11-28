package com.enc.domain.access.vo.person;

import lombok.Data;

@Data
public class PersonPushRespVo {
    private int resultCode;
    private String msg;
    private int totalCount ;
}
