package com.service.impl;

import com.dao.ITeacherDao;
import com.domain.Course;
import com.domain.Teacher;
import com.domain.Teacher;
import com.service.ITeacherService;
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
@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherDao teacherDao;

    @Override
    public int insert(Teacher teacher) {
        log.info("业务层-插入一条教师记录");
        return teacherDao.insert(teacher);
    }

    @Override
    public int deleteById(String id) {
        log.info("业务层-根据ID删除教师记录");
        return teacherDao.deleteById(id);
    }

    @Override
    public int updateById(Teacher teacher) {
        log.info("业务层-根据ID修改教师记录");
        return teacherDao.updateById(teacher);
    }

    @Override
    public Teacher selectById(String id) {
        log.info("业务层-根据ID查询教师记录");
        return teacherDao.selectById(id);
    }

    @Override
    public Integer selectCount() {
        log.info("业务层-查询教师总记录数");
        return teacherDao.selectCount();
    }

    @Override
    public List<Teacher> selectList() {
        log.info("业务层-查询所有教师记录");
        return teacherDao.selectList();
    }

    @Override
    public List<Teacher> selectInstruction() {
        log.info("业务层-教师任课查询");
        return teacherDao.selectInstruction();
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
        List<Teacher> teachers=new ArrayList<>();
        for (int i = 0; i < listObj.size(); i++) {
            List<Object> r = listObj.get(i);//行对象
            Teacher teacher=new Teacher();
            teacher.setId((String) r.get(0));//单元格对象->属性值
            teacher.setName((String) r.get(1));
            teachers.add(teacher);
            log.info("import line "+(i+1)+": "+teacher);
//            excelDao.importExcel(teacher);//一行一行导入
//            teacherDao.insert(teacher);
        }
//        excelDao.importExcelByListTeacher(teachers);//整张表导入
        teacherDao.insertList(teachers);
        log.info("teachersSize:"+teachers.size());
    }

    /**
     * 从DB导出教师表
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
        String[] titles = { "t_id", "t_name" };
        List<Teacher> teachers=teacherDao.selectList();
        ExcelUtil.export(out, titles, "teacher", teachers);
    }
    
}
