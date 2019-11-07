package com.ratta.entity.user;

import java.util.Date;

import lombok.Data;

@Data
public class SensitiveRecordDO {
    private Long id;

    private Long userId;

    private String operateRecord;

    private String ip;

    private Date createTime;

    private Date updateTime;

}