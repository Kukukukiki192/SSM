package com.dao;

import com.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 课程 dao 接口
 */
@Repository
public interface ICourseDao extends IBaseDao<Course> {

    /**
     * 查询所有课程及选修该课程的所有学生
     * @return
     */
    List<Course> selectSelection();

    /**
     * 查询所有课程及教授该课程的所有教师
     * @return
     */
    List<Course> selectInstruction();
}

