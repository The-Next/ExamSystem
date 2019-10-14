package com.linln.modules.system.service;

import com.linln.modules.system.entity.SingleChoice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:49
 * @Descript: 单选题服务接口
 * @Version 1.0
 */
public interface SingleChoiceService {
    public void add(SingleChoice singleChoice);//添加题目
    public void delete(Integer id);//删除题目
    @Deprecated
    public void update(SingleChoice singleChoice);//修改题目，不再使用，功能和update重合
    public Page<SingleChoice> getPageList();//查询所有题目,分页
    public List<SingleChoice> getList();//查询所有题目
    public Optional<SingleChoice> getById(Integer id);//根据id获取题目
}
