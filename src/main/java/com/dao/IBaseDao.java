package com.dao;

import java.io.Serializable;
import java.util.*;

public interface IBaseDao<T> {
    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    int insert(T entity);//-------------------------------

    /**
     * 批量导入记录
     * @param entityList
     * @return
     */
    int insertList(List<T> entityList);//--------------------

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);//---------------------------

    /**
     * 根据 entity 条件,删除一条记录
     * @param entity
     * @return
     */
    int deleteOne(T entity);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(Map<String, Object> columnMap);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int deleteBatchIds(Collection<? extends Serializable> idList);

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(T entity);//---------------------------------------

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity 实体对象 (set 条件值,可以为 null)
     */
    int update(T entity);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);//---------------------------------

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectBatchIds(Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param entity 实体对象封装操作类（可以为 null）
     */
    T selectOne(T entity);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param entity 实体对象封装操作类（可以为 null）
     */
    Integer selectCount(T entity);

    /**
     * 查询总记录数
     * @return
     */
    Integer selectCount();//--------------------------------------

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param entity 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(T entity);

    /**
     * 查询全部记录
     * @return
     */
    List<T> selectList();//----------------------------------findAll

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param entity 实体对象封装操作类（可以为 null）
     */
    List<Map<String, Object>> selectMaps(T entity);

    /**
     * 根据 entity 条件，查询全部记录
     * <p>注意： 只返回第一个字段的值</p>
     *
     * @param entity 实体对象封装操作类（可以为 null）
     */
    List<Object> selectObjs(T entity);
}
