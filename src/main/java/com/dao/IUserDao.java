package com.dao;

import com.domain.*;
import org.springframework.stereotype.Repository;

/**
 * ip拦截 dao 接口
 */
@Repository
public interface IUserDao extends IBaseDao<User> {
    //List<T> selectList(T entity);
}
