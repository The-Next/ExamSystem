package com.linln.modules.system.service;

import com.linln.modules.system.entity.TestPaper;
import com.linln.modules.system.entity.TestPaperExtension;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/8/16 下午3:46
 * @Descript: 组卷系统服务接口
 * @Version 1.0
 */
public interface TestPaperService {
    public Page<TestPaper> getPageList();
    public void save(Integer[] single
            ,Integer[] multiple
            ,Integer[] judge
            ,Integer[] subjective
            ,TestPaper testPaper);
    public List<TestPaperExtension> getTestpaperList();//不包含试题信息
    public TestPaper getTestById(Integer id);//根据id获取试卷
}
