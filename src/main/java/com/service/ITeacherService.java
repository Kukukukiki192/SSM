package com.service;

import com.domain.Teacher;
import com.domain.Teacher;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITeacherService {
//    /**
//     * 查询所有教师信息
//     * @return
//     */
//    public List<Teacher> findAll();
//
//    /**
//     * 根据教师ID查询
//     * @return
//     */
//    public Teacher findById(String id);
//
//    /**
//     * 保存
//     */
//    public void save(Teacher teacher);
//
//    /**
//     * 根据教师ID更新
//     * @param teacher
//     */
//    public void updateById(Teacher teacher);
//
//    /**
//     * 根据教师ID删除
//     * @param id
//     */
//    public void delById(String id);
//
//    /**
//     * 查询所有教师及其教授课程信息
//     * @return
//     */
//    public List<Teacher> findInstruction();


    /**
     * 插入一条记录
     *
     * @param teacher 实体对象
     */
    int insert(Teacher teacher);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(String id);

    /**
     * 根据 ID 修改
     *
     * @param teacher 实体对象
     */
    int updateById(Teacher teacher);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    Teacher selectById(String id);

    /**
     * 查询总记录数
     * @return
     */
    Integer selectCount();

    /**
     * 查询全部记录
     * @return
     */
    List<Teacher> selectList();

    /**
     * 教师任课查询
     * @return
     */
    List<Teacher> selectInstruction();

    /**
     * Excel教师记录批量导入DB
     * @param file
     * @throws Exception
     */
    void importExcel(MultipartFile file) throws Exception;

    /**
     * 从DB导出教师表到Excel
     * @param fileName
     * @param response
     * @throws Exception
     */
    void exportExcel(String fileName, HttpServletResponse response) throws Exception;

}
