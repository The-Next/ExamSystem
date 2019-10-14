package com.linln.modules.system.service;

import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.entity.MultipleOptions;
import com.linln.modules.system.entity.QueryVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/2 下午5:30
 * @Descript: 多选题服务接口
 * @Version 1.0
 */

public interface MultipleChoiceSevice {
    public Page<MultipleChoice> getPageList();//查询所有题目,分页
    public List<MultipleChoice> getList();//查询所有题目
    public void delete(Integer id);//根据id删除题目
    public void save(MultipleChoice multipleChoice, List<MultipleOptions> multipleOptionsList);//保存、添加数据
    public Optional<MultipleChoice> getById(Integer id);//根据id获取数据
    public void deleteOptionById(Integer id);//根据id删除选项
    public List<MultipleOptions> getOptionsById(Integer id);//根据id获得选项
}
