package com.controller;

import com.domain.ResultDTO;
import com.service.*;
import com.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICourseService courseService;

    /**
     * 从Excel批量导入数据到DB
     * @param file
     * @param entity
     * @return
     * @throws Exception
     */
    @PostMapping("/import")
    public ResultDTO importExcel(@RequestParam("excel") MultipartFile file, @RequestParam("entity") String entity) throws Exception {
        log.info("importExcel-------------------------------------------");
        try {
            switch (entity){
                case "student":
                    studentService.importExcel(file);
                    break;
                case "teacher":
                    teacherService.importExcel(file);
                    break;
                case "course":
                    courseService.importExcel(file);
                    break;
            }
        } catch (Exception e) {
            log.info("importExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("importExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

    /**  从DB导出记录到Excel
     * poi导出电子表格问题：
     * 代码已经设置了文件名，并且支持中文，但是在用postman测试的时候，文件名永远是response.xsl
     * 解决办法：
     * 不用postman测试，改为浏览器，并且用 GET 请求方式，即可解决
     * @param entity
     * @param fileName
     * @param response
     * @return
     */
    @GetMapping("/export/{entity}/{fileName}")
    public ResultDTO exportExcel(@PathVariable("entity") String entity, @PathVariable("fileName") String fileName, HttpServletResponse response){ // 在浏览器中可以读到文件名 Apifox中-响应提交后无法调用sendError(),弹框读取不到文件名,要右键打开方式
        /**
         * poi导出电子表格问题：
         * 代码已经设置了文件名，并且支持中文，但是在用postman测试的时候，文件名永远是response.xsl
         * 解决办法：不用postman测试，改为浏览器，并且用 GET 请求方式，即可解决
         */
        log.info("exportExcel-------------------------------------------");
        log.info("导出数据对应实体类:{}, 导出excel文件名:{}",entity,fileName);
        try{
            switch (entity){
                case "student":
                    studentService.exportExcel(fileName, response);
                    break;
                case "teacher":
                    teacherService.exportExcel(fileName, response);
                    break;
                case "course":
                    courseService.exportExcel(fileName, response);
                    break;
            }
        } catch(Exception e){
            log.info("exportExcel error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        log.info("exportExcel successfully! ----------------------------");
        return new ResultDTO(0,"success");
    }

}
