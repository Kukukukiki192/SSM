package com.dao;

import com.domain.*;
import com.domain.vo.SelectionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 选课 dao 接口
 */
@Repository
public interface ISelectionDao  extends IBaseDao<Selection>{

//    /**
//     * 插入一条记录
//     *
//     * @param entity 实体对象
//     */
//    int insert(T entity);

    //entity 条件:根据学生ID和课程ID

//    /**
//     * 根据 entity 条件,删除一条记录
//     * @param entity
//     * @return
//     */
//    int deleteOne(T entity);

    //只能改成绩
//    /**
//     * 根据 whereEntity 条件，更新记录
//     *
//     * @param entity 实体对象 (set 条件值,可以为 null)
//     */
//    int update(T entity);

//    /**
//     * 根据 entity 条件，查询一条记录
//     *
//     * @param entity 实体对象封装操作类（可以为 null）
//     */
//    T selectOne(T entity);
//
    /**
     * 查询所有学生及其选修课信息 详细
     * @return
     */
    List<SelectionVO> selectSelection();


}
