package com.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 选修课 实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Selection  implements Serializable {
    //    private Integer selId;    //选修课程ID   自增长
    private Integer sId;    //学生编号  非空
    private Integer cId;    //课程编号  非空
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String year;    //开课学年
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String term;    //开课学期
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer grade;  //课程成绩
}