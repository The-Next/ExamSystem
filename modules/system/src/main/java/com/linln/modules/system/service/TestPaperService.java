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
    public void saveSingle(Integer[] single,Integer id);//向试卷里添加单选题
    public void deleteSingle(Integer single,Integer id);//向试卷里删除单选题
    public void saveMultiple(Integer[] multiple,Integer id);//向试卷里添加多选题
    public void deleteMultiple(Integer multiple,Integer id);//向试卷里删除多选题
    public void saveJudge(Integer[] judge,Integer id);//向试卷里添加判断题
    public void deleteJudge(Integer judge,Integer id);//向试卷里删除判断题
    public void saveSubjective(Integer[] subjective,Integer id);//向试卷里添加主观题
    public void deleteSubjective(Integer subjective,Integer id);//向试卷里删除主观题
    public void updataTestPaper(TestPaper testPaper);//修改试卷信息
}
