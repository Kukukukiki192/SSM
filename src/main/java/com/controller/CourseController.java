package com.controller;

import com.domain.*;
import com.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    /**
     * Bean对象传参
     * 保存/提交-Post
     * 注：1.须指定http头为content-type:application/json 2.使用body传输数据
     */

    /**
     * 添加课程
     * @param course
     * @return
     */
    @PostMapping("/insert")
    public ResultDTO insert(@RequestBody Course course){
        log.info("表现层-插入一条课程记录");
        try{
            courseService.insert(course);//要验证是否保存成功
            log.info("insert: "+course);
        }catch (Exception e){
            log.info("insert error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",course);
    }

    /**
     * 根据ID删除课程
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{c_id}")
    public ResultDTO deleteById(@PathVariable("c_id") String id){
        log.info("表现层-根据ID删除课程记录");
        Course course=courseService.selectById(id);
        try{
            courseService.deleteById(id);
            log.info("deleteById(id="+id+"): "+course);
        }catch (Exception e){
            log.info("deleteById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",course);
    }

    /**
     * 根据ID更新课程信息
     * @param course
     * @return
     */
    @PutMapping("/updateById")
    public ResultDTO updateById(@RequestBody Course course){
        log.info("表现层-根据ID修改课程记录");
        try{
            courseService.updateById(course);
            log.info("updateById(id="+course.getId()+"): "+course);
        }catch (Exception e){
            log.info("updateById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",course);
    }

    /**
     * 查询所有课程信息
     * @return
     */
    @GetMapping("/selectList")
    public ResultDTO selectList(){
        log.info("表现层-查询所有课程记录");
        List<Course> courses=courseService.selectList();
        int i=0;
        for(Course course:courses){
            log.info("Course"+(++i)+": {}",course);//日志要用{}传对象
        }
        if(courses==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",courses);
    }

    /**
     * 查询所有课程及选修该课程的所有学生
     * @return
     */
    @GetMapping("/selectSelection")
    public ResultDTO selectSelection(){
        log.info("表现层-查询所有课程及选修该课程的所有学生");
        List<Course> courses=courseService.selectSelection();
        int i=0;
        for(Course course:courses){
            log.info("Course"+(++i)+": {}",course);//日志要用{}传对象
        }
        if(courses==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",courses);
    }

    /**
     * 查询所有课程及教授该课程的所有教师
     * @return
     */
    @GetMapping("/selectInstruction")
    public ResultDTO selectInstruction() {
        log.info("表现层-查询所有课程及教授该课程的所有教师");
        List<Course> courses=courseService.selectInstruction();
        int i=0;
        for(Course course:courses){
            log.info("Course"+(++i)+": {}",course);//日志要用{}传对象
        }
        if(courses==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",courses);
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
            courseService.importExcel(file);
        } catch (Exception e) {
            log.info("importExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("importExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

    /** 导出Excel
     * 从DB导出课程表
     * @param fileName
     * @param response
     * @return
     */
    @GetMapping("/exportExcel/{fileName}")
    public ResultDTO exportExcel(@PathVariable("fileName") String fileName, HttpServletResponse response){
        log.info("exportExcel-------------------------------------------");
        log.info("导出数据对应实体类: course, 导出excel文件名:{}",fileName);
        try{
            courseService.exportExcel(fileName, response);
        } catch(Exception e){
            log.info("exportExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("exportExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

}
