package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.SingleChoice;
import com.linln.modules.system.repository.SingleChioceRepository;
import com.linln.modules.system.service.SingleChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:55
 * @Descript: 单选题服务接口
 * @Version 1.0
 */
@Service
public class SingleChoiceServiceImpl implements SingleChoiceService {

    @Resource
    private SingleChioceRepository singleChioceRepository;

    @Override
    public void add(SingleChoice singleChoice) {
        singleChioceRepository.save(singleChoice);
    }

    @Override
    public void delete(Integer id) {
        singleChioceRepository.deleteById(id);
    }

    @Override
    public void update(SingleChoice singleChoice) {
        singleChioceRepository.save(singleChoice);
    }

    @Override
    public Page<SingleChoice> getPageList() {
        PageRequest page = PageSort.pageRequest();
        return singleChioceRepository.findAll(page);
    }

    @Override
    public List<SingleChoice> getList() {
        return singleChioceRepository.findAll();
    }

    @Override
    public Optional<SingleChoice> getById(Integer id) {
        return singleChioceRepository.findById(id);
    }
}
