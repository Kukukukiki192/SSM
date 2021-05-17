package com.service;

import com.domain.Course;
import com.domain.Course;
import com.domain.Teacher;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICourseService {
    /**
     * 插入一条记录
     *
     * @param course 实体对象
     */
    int insert(Course course);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(String id);

    /**
     * 根据 ID 修改
     *
     * @param course 实体对象
     */
    int updateById(Course course);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Course selectById(String id);

    /**
     * 查询总记录数
     * @return
     */
    Integer selectCount();

    /**
     * 查询全部记录
     * @return
     */
    List<Course> selectList();

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

    /**
     * Exce课程记录批量导入DB
     * @param file
     * @throws Exception
     */
    void importExcel(MultipartFile file) throws Exception;

    /**
     * 从DB导出课程表到Excel
     * @param fileName
     * @param response
     * @throws Exception
     */
    void exportExcel(String fileName, HttpServletResponse response) throws Exception;
    
}
