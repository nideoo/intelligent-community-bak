package com.enc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 马晓光 on 2019/1/7.
 */
@Data
public class ResponseMaxResult implements Serializable {
    private int resultCode;
    private String msg;
}
