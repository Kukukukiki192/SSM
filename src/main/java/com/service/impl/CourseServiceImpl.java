package com.service.impl;

import com.dao.ICourseDao;
import com.domain.Course;
import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.service.ICourseService;
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
@Service("courseService")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;
    
    @Override
    public int insert(Course course) {
        log.info("业务层-插入一条课程记录");
        return courseDao.insert(course);
    }

    @Override
    public int deleteById(String id) {
        log.info("业务层-根据ID删除课程记录");
        return courseDao.deleteById(id);
    }

    @Override
    public int updateById(Course course) {
        log.info("业务层-根据ID修改课程记录");
        return courseDao.updateById(course);
    }

    @Override
    public Course selectById(String id) {
        log.info("业务层-根据ID查询课程记录");
        return courseDao.selectById(id);
    }

    @Override
    public Integer selectCount() {
        log.info("业务层-查询课程总记录数");
        return courseDao.selectCount();
    }

    @Override
    public List<Course> selectList() {
        log.info("业务层-查询所有课程记录");
        return courseDao.selectList();
    }

    @Override
    public List<Course> selectSelection() {
        log.info("业务层-查询所有课程及选修该课程的所有学生");
        return courseDao.selectSelection();
    }

    @Override
    public List<Course> selectInstruction() {
        log.info("业务层-查询所有课程及教授该课程的所有教师");
        return courseDao.selectInstruction();
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
        List<Course> courses=new ArrayList<>();
        for (int i = 0; i < listObj.size(); i++) {
            List<Object> r = listObj.get(i);//行对象
            Course course=new Course();
            course.setId((String) r.get(0));//单元格对象->属性值
            course.setName((String) r.get(1));
            course.setPeriod(Integer.valueOf(r.get(2).toString()));
            course.setCredit(Double.valueOf(r.get(3).toString()));
            courses.add(course);
            log.info("import line "+(i+1)+": "+course);
//            excelDao.importExcel(course);//一行一行导入
//            courseDao.insert(course);
        }
//        excelDao.importExcelByListCourse(courses);//整张表导入
        courseDao.insertList(courses);
        log.info("coursesSize:"+courses.size());
    }

    /**
     * 从DB导出课程表
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
        String[] titles = { "c_id", "c_name", "c_period", "c_credit" };
        List<Course> courses=courseDao.selectList();
        ExcelUtil.export(out, titles, "course", courses);
    }
    
}
