package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.SubjectiveItem;
import com.linln.modules.system.repository.SubjectiveItemRepository;
import com.linln.modules.system.service.SubjectiveItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午11:19
 * @Descript: 主观题服务接口实现
 * @Version 1.0
 */
@Service
public class SubjectiveItemServiceimpl implements SubjectiveItemService {

    @Resource
    private SubjectiveItemRepository subjectiveItemRepository;

    @Override
    public Page<SubjectiveItem> getPageList() {
        PageRequest page = PageSort.pageRequest();
        return subjectiveItemRepository.findAll(page);
    }

    @Override
    public List<SubjectiveItem> getList() {
        return subjectiveItemRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        subjectiveItemRepository.deleteById(id);
    }

    @Override
    public void save(SubjectiveItem subjectiveItem) {
        subjectiveItemRepository.save(subjectiveItem);
    }

    @Override
    public Optional<SubjectiveItem> getById(Integer id) {
        return subjectiveItemRepository.findById(id);
    }
}
