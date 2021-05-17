package com.dao;

import com.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 教师 dao 接口
 */
@Repository
public interface ITeacherDao extends IBaseDao<Teacher> {

    /**
     * 查询所有教师及其教授课程信息
     * @return
     */
    List<Teacher> selectInstruction();
}
