package com.controller;

import com.domain.*;
import com.domain.vo.CreditsVO;
import com.domain.vo.SelectionVO;
import com.service.*;
import com.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    /**
     * Bean对象传参
     * 保存/提交-POST 修改-PUT 涉及到有请求参数的
     * 注：1.须指定http头为content-type:application/json 2.使用body传输数据
     */


    /**
     * 插入一条学生记录
     * @param student
     * @return
     */
    @PostMapping("/insert")
    public ResultDTO insert(@RequestBody Student student){
        log.info("表现层-插入一条学生记录");
        try{
            studentService.insert(student);//要验证是否保存成功
            log.info("insert: "+student);
        }catch (Exception e){
            log.info("insert error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",student);
    }

    /**
     * 根据ID删除学生记录
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{s_id}")
    public ResultDTO deleteById(@PathVariable("s_id") String id){
        log.info("表现层-根据ID删除学生记录");
        Student student=studentService.selectById(id);
        try{
            studentService.deleteById(id);
            log.info("deleteById(id="+id+"): "+student);
        }catch (Exception e){
            log.info("deleteById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",student);
    }

    /**
     * 根据ID修改学生记录
     * @param student
     * @return
     */
    @PutMapping("/updateById")
    public ResultDTO updateById(@RequestBody Student student){
        log.info("表现层-根据ID修改学生记录");
        try{
            studentService.updateById(student);
            log.info("updateById(id="+student.getId()+"): "+student);
        }catch (Exception e){
            log.info("updateById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",student);
    }

    /**
     * 查询所有学生信息
     * @return
     */
    @GetMapping("/selectList")
    public ResultDTO selectList(){
        log.info("表现层-查询所有学生记录");
        List<Student> students=studentService.selectList();
        int i=0;
        for(Student student:students){
            log.info("Student"+(++i)+": {}",student);//日志要用{}传对象
        }
        if(students==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",students);
    }

    /**
     * 查询所有学生及其选修课记录
     * @return
     */
    @GetMapping("/selectSelection")
    public ResultDTO selectSelection(){
        log.info("表现层-查询学生及其选修课记录");
        int i=0;
        List<Student> students=studentService.selectSelection();
        for(Student student:students){
            log.info("Student"+(++i)+": {}",student);//日志要用{}传对象
        }
        if(students==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",students);
    }

    @GetMapping("/selectCredits")
    public ResultDTO selectCredits(){
        log.info("表现层-查询所有学生已修学分总数");
        int i=0;
        List<CreditsVO> creditsVOs=studentService.selectCredits();
        for(CreditsVO creditsVO:creditsVOs){
            log.info("学生"+(++i)+"总学分: {}",creditsVO);
        }
        if(creditsVOs==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",creditsVOs);
    }

    /** 导入Excel
     * 批量导入数据到DB
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/importExcel")
    public ResultDTO importExcel(@RequestParam("excel") MultipartFile file) throws Exception {
        log.info("importExcel-------------------------------------------");
        try {
            studentService.importExcel(file);
        } catch (Exception e) {
            log.info("importExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("importExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

    /**  导出Excel
     * 从DB导出学生表
     * @param fileName
     * @param response
     * @return
     */
    @GetMapping("/exportExcel/{fileName}")
    public ResultDTO exportExcel(@PathVariable("fileName") String fileName, HttpServletResponse response){
        log.info("exportExcel-------------------------------------------");
        log.info("导出数据对应实体类: student, 导出excel文件名:{}",fileName);
        try{
            studentService.exportExcel(fileName, response);
        } catch(Exception e){
            log.info("exportExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("exportExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

}
