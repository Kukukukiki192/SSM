package com.dao;

import com.domain.*;
import com.domain.vo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生 dao 接口
 */
@Repository
public interface IStudentDao extends IBaseDao<Student> {

    /**
     * 查询所有学生及其选修课程信息
     * @return
     */
    List<Student> selectSelection();

    /**
     * 根据 VO 中的条件查询--这个是查询条件
     *  把实体类信息封装到vo类,适合多表操作 若查询条件要从多个对象中取出,就需要先把它们包装成1个
     */


    /**
     * 查询所有学生已修学分总数
     * @return
     */
    List<CreditsVO> selectCredits();



}
