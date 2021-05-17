package com.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 教师 实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher implements Serializable {
//    private Integer id;  //教师ID   自增长
    private String id;  //教师编号  非空
    private String name;//教师姓名 非空

    //n:n关系映射 1个教师可教授多门课程 课程ID(外键)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//空字段不返回
    private List<Course> courses;

}
