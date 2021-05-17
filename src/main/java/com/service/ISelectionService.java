package com.service;

import com.domain.Selection;
import com.domain.vo.SelectionVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ISelectionService {

    /**
     * 插入一条记录
     *
     * @param selection 实体对象
     */
    int insert(Selection selection);

    /**
     * 根据学生ID和课程ID删除
     * @param selection
     * @return
     */
    int deleteOne(Selection selection);

    /**
     * 根据学生ID和课程ID修改成绩
     *
     * @param selection 实体对象
     */
    int update(Selection selection);

    /**
     * 根据学生ID和课程ID查询
     * @param selection
     * @return
     */
    Selection selectOne(Selection selection);

    /**
     * 查询总记录数
     * @return
     */
    Integer selectCount();

    /**
     * 查询全部记录
     * @return
     */
    List<Selection> selectList();

    /**
     * 查询所有学生及其选修课信息 详细
     * @return
     */
    List<SelectionVO> selectSelection();

//    /**
//     * Excel选课记录批量导入DB
//     * @param file
//     * @throws Exception
//     */
//    void importExcel(MultipartFile file) throws Exception;
//
//    /**
//     * 从DB导出选课表到Excel
//     * @param fileName
//     * @param response
//     * @throws Exception
//     */
//    void exportExcel(String fileName, HttpServletResponse response) throws Exception;
}
