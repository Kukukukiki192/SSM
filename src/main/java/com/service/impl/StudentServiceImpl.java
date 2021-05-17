package com.service.impl;

import com.dao.*;
import com.domain.*;
import com.domain.vo.CreditsVO;
import com.domain.vo.SelectionVO;
import com.service.IStudentService;
import com.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("studentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDao studentDao;

    @Override
    public int insert(Student student) {
        log.info("业务层-插入一条学生记录");
        return studentDao.insert(student);
    }

    @Override
    public int deleteById(String id) {
        log.info("业务层-根据ID删除学生记录");
        return studentDao.deleteById(id);
    }

    @Override
    public int updateById(Student student) {
        log.info("业务层-根据ID修改学生记录");
        return studentDao.updateById(student);
    }

    @Override
    public Student selectById(String id) {
        log.info("业务层-根据ID查询学生记录");
        return studentDao.selectById(id);
    }

    @Override
    public Integer selectCount() {
        log.info("业务层-查询学生总记录数");
        return studentDao.selectCount();
    }

    @Override
    public List<Student> selectList() {
        log.info("业务层-查询所有学生记录");
        return studentDao.selectList();
    }

    @Override
    public List<Student> selectSelection() {
        log.info("业务层-查询学生及其选修课记录");
        return studentDao.selectSelection();
    }

    @Override
    public List<CreditsVO> selectCredits() {
        log.info("业务层-查询所有学生已修学分总数");
        return studentDao.selectCredits();
    }

    /**
     * 读表-取数据封装到实体对象-将实体类映射到DB表导入
     * @param file
     * @throws Exception
     */
    @Override
    public void importExcel(MultipartFile file) throws Exception {
        if(file.isEmpty()){
            log.info("Excel file does not exist");
            throw new Exception("Excel file does not exist！");
        }
        String fileName = file.getOriginalFilename();// 原文件名
        log.info("Excel file name: "+fileName);
        InputStream in = file.getInputStream();// 获取输入流
        List<List<Object>> listObj=new ExcelUtil().getBankListByExcel(in,fileName);//表对象
        in.close();
        List<Student> students=new ArrayList<>();
        for (int i = 0; i < listObj.size(); i++) {
            List<Object> r = listObj.get(i);//行对象
            Student student=new Student();
            student.setId((String) r.get(0));//单元格对象->属性值
            student.setName((String) r.get(1));
            students.add(student);
            log.info("import line "+(i+1)+": "+student);
//            studentDao.insert(student); //一行一行导入
        }
        studentDao.insertList(students); //整张表导入
        log.info("studentsSize:"+students.size());
    }

    /**
     * 从DB导出学生表
     * @param fileName
     * @param response
     * @throws Exception
     */
    @Override
    public void exportExcel(String fileName, HttpServletResponse response) throws Exception {
        OutputStream out=response.getOutputStream();
        response.setContentType("application/binary;charset=UTF-8");
        try {
            //设置文件头：最后一个参数是设置下载文件名                                      文件名称转码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName+".xls", "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String[] titles = { "s_id", "s_name" };
        List<Student> students=studentDao.selectList();
        ExcelUtil.export(out, titles, "student", students);
    }
}
