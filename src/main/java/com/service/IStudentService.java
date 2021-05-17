package com.service;

import com.domain.Student;
import com.domain.vo.CreditsVO;
import com.domain.vo.SelectionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

public interface IStudentService {

    /**
     * 插入一条记录
     *
     * @param student 实体对象
     */
    int insert(Student student);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(String id);

    /**
     * 根据 ID 修改
     *
     * @param student 实体对象
     */
    int updateById(Student student);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Student selectById(String id);

    /**
     * 查询总记录数
     * @return
     */
    Integer selectCount();

    /**
     * 查询全部记录
     * @return
     */
    List<Student> selectList();

    /**
     * 查询所有学生及其选修课程
     * @return
     */
    public List<Student> selectSelection();

    /**
     * 查询所有学生已修学分总数
     * @return
     */
    public List<CreditsVO> selectCredits();

    /**
     * Excel学生记录批量导入DB
     * @param file
     * @throws Exception
     */
    void importExcel(MultipartFile file) throws Exception;

    /**
     * 从DB导出学生表到Excel
     * @param fileName
     * @param response
     * @throws Exception
     */
    void exportExcel(String fileName, HttpServletResponse response) throws Exception;

}
