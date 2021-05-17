package com.controller;

import com.domain.ResultDTO;
import com.domain.Selection;
import com.domain.vo.SelectionVO;
import com.service.ISelectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/selection")
public class SelectionController {
    
    @Autowired
    private ISelectionService selectionService;

    /**
     * 插入一条选修课记录
     * @param selection
     * @return
     */
    @PostMapping("/insert")
    public ResultDTO insert(@RequestBody Selection selection){
        log.info("表现层-插入一条选修课记录");
        try{
            selectionService.insert(selection);//要验证是否保存成功
            log.info("insert: "+selection);
        }catch (Exception e){
            log.info("insert error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",selection);
    }

    /**
     * 根据学生ID和课程ID删除
     * @param selection
     * @return
     */
    @DeleteMapping("/deleteOne")
    public ResultDTO deleteOne(@RequestBody Selection selection){
        log.info("表现层-根据学生ID和课程ID删除选修课记录");
        try{
            selectionService.deleteOne(selection);
            log.info("deleteOne(sId="+selection.getSId()+",cId="+selection.getCId()+"): "+selection);
        }catch (Exception e){
            log.info("deleteOne error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",selection);
    }

    /**
     * 根据学生ID和课程ID修改选修课成绩
     * @param selection
     * @return
     */
    @PutMapping("/update")
    public ResultDTO update(@RequestBody Selection selection){
        log.info("表现层-根据学生ID和课程ID修改选修课成绩");
        try{
            selectionService.update(selection);
            log.info("update(sId="+selection.getSId()+",cId="+selection.getCId()+"): "+selection);
        }catch (Exception e){
            log.info("update error!");
            e.printStackTrace();
            return new ResultDTO(-1,"fail");
        }
        return new ResultDTO(0,"success",selection);
    }

    @GetMapping("/selectSelection")
    public ResultDTO selectSelection(){
        log.info("表现层-查询学生及其选修课记录 详细");
        int i=0;
        List<SelectionVO> selectionVOs=selectionService.selectSelection();
        for(SelectionVO selectionVO:selectionVOs){
            log.info("选修"+(++i)+": {}",selectionVO);
        }
        if(selectionVOs==null) return new ResultDTO(-1,"fail");
        return new ResultDTO(0,"success",selectionVOs);
    }

}
