package com.service.impl;

import com.dao.ISelectionDao;
import com.domain.Selection;
import com.domain.vo.SelectionVO;
import com.service.ISelectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Service("selectionService")
public class SelectionServiceImpl implements ISelectionService {

    @Autowired
    private ISelectionDao selectionDao;

    @Override
    public int insert(Selection selection) {
        log.info("业务层-插入一条选修课记录");
        return selectionDao.insert(selection);
    }

    @Override
    public int deleteOne(Selection selection) {
        log.info("业务层-根据学生ID和课程ID删除选修课记录");
        return selectionDao.deleteOne(selection);
    }

    @Override
    public int update(Selection selection) {
        log.info("业务层-根据学生ID和课程ID修改成绩");
        return selectionDao.update(selection);
    }

    @Override
    public Selection selectOne(Selection selection) {
        log.info("业务层-根据学生ID和课程ID查找选课记录");
        return selectionDao.selectOne(selection);
    }

    @Override
    public Integer selectCount() {
        log.info("业务层-查询选课总记录数");
        return selectionDao.selectCount();
    }

    @Override
    public List<Selection> selectList() {
        log.info("业务层-查询所有选课记录");
        return selectionDao.selectList();
    }

    @Override
    public List<SelectionVO> selectSelection() {
        log.info("业务层-查询学生及其选修课记录 详细");
        return selectionDao.selectSelection();
    }


//    @Override
//    public void importExcel(MultipartFile file) throws Exception {
//
//    }
//
//    @Override
//    public void exportExcel(String fileName, HttpServletResponse response) throws Exception {
//
//    }
}
