package com.linln.modules.system.service;

import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.entity.MultipleOptions;
import com.linln.modules.system.entity.SubjectiveItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午11:17
 * @Descript: 主观题服务接口
 * @Version 1.0
 */
public interface SubjectiveItemService {
    public Page<SubjectiveItem> getPageList();//查询所有题目,分页
    public void delete(Integer id);//根据id删除题目
    public void save(SubjectiveItem subjectiveItem);//保存、添加数据
    public Optional<SubjectiveItem> getById(Integer id);//根据id获取数据
}
