package com.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生已修学分 实体展示类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditsVO  implements Serializable {
    private String sId;     //学号
    private String sName;   //姓名
    private String credits; //总学分
}
