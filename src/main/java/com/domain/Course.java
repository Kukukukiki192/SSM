package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 课程 实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course implements Serializable {
//    private Integer id;      //课程ID   自增长
    private String id;      //课程编号  非空
    private String name;    //课程名    非空
    private Integer period; //学时     非空
    private Double credit;  //学分     非空

    //n:n关系映射 1门课程可被多个教师教授 教师ID(外键)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//空字段不返回
    private List<Teacher> teachers;

    //n:n关系映射 1门课程可被多个学生选修 学生ID(外键)
//    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Student> students;

    //分别获得选修课信息和教授教师信息


    public Course(String id, String name, Integer period, Double credit) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.credit = credit;
    }
}
