package com.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 学生 实体类
 */
@NoArgsConstructor  //没有的话响应会出现415错误-请求格式错误
@AllArgsConstructor
@Data
public class Student implements Serializable {

//    private Integer id;  //学生ID  自增长
    private String id;  //学号    非空
    private String name;//姓名    非空

    //n:n关系映射 1个学生可选修多门课程 课程ID(外键)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//空字段不返回
    private List<Course> courses;


}
