package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.entity.MultipleOptions;
import com.linln.modules.system.repository.MultipleChoiceRepository;
import com.linln.modules.system.repository.MultipleOptionsRepository;
import com.linln.modules.system.service.MultipleChoiceSevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/2 下午6:26
 * @Descript: 多选题服务接口实现
 * @Version 1.0
 */
@Service
public class MultipleChoiceSeviceImpl implements MultipleChoiceSevice {

    @Resource
    private MultipleChoiceRepository multipleChoiceRepository;
    @Resource
    private MultipleOptionsRepository multipleOptionsRepository;

    @Override
    public Page<MultipleChoice> getPageList() {
        PageRequest page = PageSort.pageRequest();
        return multipleChoiceRepository.findAll(page);
    }

    @Override
    public void delete(Integer id) {
        multipleChoiceRepository.deleteById(id);
        multipleOptionsRepository.deleteByM_id(id);
    }

    @Override
    public void save(MultipleChoice multipleChoice, List<MultipleOptions> multipleOptionsList) {
        multipleChoiceRepository.save(multipleChoice);
        multipleOptionsList.forEach(s -> {
            s.setM_id(multipleChoice.getId());
            multipleOptionsRepository.save(s);
        });
    }

    @Override
    public Optional<MultipleChoice> getById(Integer id) {
        Optional<MultipleChoice> choice = multipleChoiceRepository.findById(id);
        List<MultipleOptions> multipleOptions = multipleOptionsRepository.findMultipleOptionsByM_id(id);
        choice.get().setMultipleOptions(multipleOptions);
        return choice;
    }

    @Override
    public void deleteOptionById(Integer id) {
        multipleOptionsRepository.deleteById(id);
    }

    @Override
    public List<MultipleOptions> getOptionsById(Integer id) {
        return multipleOptionsRepository.findMultipleOptionsByM_id(id);
    }


}
