package com.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生选课详细信息 实体类
 */
@NoArgsConstructor  //没有的话响应会出现415错误-请求格式错误
@AllArgsConstructor
@Data
public class SelectionVO implements Serializable {
//    private Student student;    //s_id s_name 非空
//    private Course course;      //c_id c_name c_period c_credit 非空
//    private Selection selection;//sel_year sel_term sel_grade

//    private Integer id;     //选修课ID  自增长
    private String sId;     //学号    非空
    private String sName;   //姓名    非空
    private String cId;     //课程编号  非空
    private String cName;   //课程名    非空
    private Integer cPeriod;//学时     非空
    private Double cCredit; //学分     非空
    private String year;    //开课学年
    private String term;    //开课学期
    private Integer grade;  //课程成绩
}
