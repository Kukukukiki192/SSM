package com.controller;

import com.domain.ResultDTO;
import com.domain.Teacher;
import com.service.ITeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    /**
     * Bean对象传参
     * 保存/提交-Post
     * 注：1.须指定http头为content-type:application/json 2.使用body传输数据
     */

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    @PostMapping("/insert")
    public ResultDTO insert(@RequestBody Teacher teacher){
        log.info("表现层-插入一条教师记录");
        try{
            teacherService.insert(teacher);//要验证是否保存成功
            log.info("insert: "+teacher);
        }catch (Exception e){
            log.info("insert error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",teacher);
    }

    /**
     * 根据ID删除教师
     * @param id
     * @return
     */
    @DeleteMapping("/deleteById/{s_id}")
    public ResultDTO deleteById(@PathVariable("s_id") String id){
        log.info("表现层-根据ID删除教师记录");
        Teacher teacher=teacherService.selectById(id);
        try{
            teacherService.deleteById(id);
            log.info("deleteById(id="+id+"): "+teacher);
        }catch (Exception e){
            log.info("deleteById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",teacher);
    }

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    @PutMapping("/updateById")
    public ResultDTO updateById(@RequestBody Teacher teacher){
        log.info("表现层-根据ID修改教师记录");
        try{
            teacherService.updateById(teacher);
            log.info("updateById(id="+teacher.getId()+"): "+teacher);
        }catch (Exception e){
            log.info("updateById error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",teacher);
    }

    /**
     * 查询所有教师信息
     * @return
     */
    @GetMapping("/selectList")
    public ResultDTO selectList(){
        log.info("表现层-查询所有教师记录");
        List<Teacher> teachers=teacherService.selectList();
        int i=0;
        for(Teacher teacher:teachers){
            log.info("Teacher"+(++i)+": {}",teacher);//日志要用{}传对象
        }
        if(teachers==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",teachers);
    }

    /**
     * 教师任课查询
     * @return
     */
    @GetMapping("/selectInstruction")
    public ResultDTO selectInstruction(){
        log.info("表现层-教师任课查询");
        List<Teacher> teachers=teacherService.selectInstruction();
        int i=0;
        for(Teacher teacher:teachers){
            log.info("Teacher"+(++i)+": {}",teacher);//日志要用{}传对象
        }
        if(teachers==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",teachers);
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
            teacherService.importExcel(file);
        } catch (Exception e) {
            log.info("importExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("importExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

    /** 导出Excel
     * 从DB导出教师表
     * @param fileName
     * @param response
     * @return
     */
    @GetMapping("/exportExcel/{fileName}")
    public ResultDTO exportExcel(@PathVariable("fileName") String fileName, HttpServletResponse response){
        log.info("exportExcel-------------------------------------------");
        log.info("导出数据对应实体类: teacher, 导出excel文件名:{}",fileName);
        try{
            teacherService.exportExcel(fileName, response);
        } catch(Exception e){
            log.info("exportExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("exportExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }
}
