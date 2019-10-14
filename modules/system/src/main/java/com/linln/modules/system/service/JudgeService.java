package com.linln.modules.system.service;

import com.linln.modules.system.entity.Judge;
import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.entity.MultipleOptions;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午9:48
 * @Descript: 判断题服务接口
 * @Version 1.0
 */
public interface JudgeService {
    public Page<Judge> getPageList();//查询所有题目,分页
    public List<Judge> getList();//查询所有题目
    public void delete(Integer id);//根据id删除题目
    public void save(Judge judge);//保存、添加数据
    public Optional<Judge> getById(Integer id);//根据id获取数据
}
