package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.Judge;
import com.linln.modules.system.repository.JudgeRepository;
import com.linln.modules.system.service.JudgeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午9:51
 * @Descript: 判断题服务接口实现
 * @Version 1.0
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private JudgeRepository judgeRepository;

    @Override
    public Page<Judge> getPageList() {
        PageRequest page = PageSort.pageRequest();
        return judgeRepository.findAll(page);
    }

    @Override
    public List<Judge> getList() {
        return judgeRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        judgeRepository.deleteById(id);
    }

    @Override
    public void save(Judge judge) {
        judgeRepository.save(judge);
    }

    @Override
    public Optional<Judge> getById(Integer id) {
        return judgeRepository.findById(id);
    }
}
