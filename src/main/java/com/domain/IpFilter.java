package com.domain;

import lombok.Data;

import java.util.Date;

/**
 * IP名单 实体类
 */
@Data
public class IpFilter {
    private Integer id;
    private String ip;
    private String module;
    private Integer mark;
    private Date ctime;
    private String cuser;
    private Date utime;
    private String lable;
}
