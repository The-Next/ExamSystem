package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.*;
import com.linln.modules.system.repository.*;
import com.linln.modules.system.service.MultipleChoiceSevice;
import com.linln.modules.system.service.TestPaperService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/8/16 下午3:49
 * @Descript: 试卷组成服务接口
 * @Version 1.0
 */
@Service
public class TestPaperServiceImpl implements TestPaperService {

    @Resource
    private TestPaperRepository testPaperRepository;

    @Resource
    private SingleChioceRepository singleChioceRepository;

    @Resource
    private MultipleChoiceRepository multipleChoiceRepository;

    @Resource
    private JudgeRepository judgeRepository;

    @Resource
    private SubjectiveItemRepository subjectiveItemRepository;

    @Resource
    private MultipleChoiceSevice multipleChoiceSevice;

    @Override
    public Page<TestPaper> getPageList() {
        PageRequest page = PageSort.pageRequest();
        return testPaperRepository.findAll(page);
    }

    @Override
    public void save(Integer[] single
            ,Integer[] multiple
            ,Integer[] judge
            ,Integer[] subjective
            ,TestPaper testPaper) {
        if (single!=null){
            List<SingleChoice> singleChoices = singleChioceRepository.findAllById(Arrays.asList(single));
            System.out.println(singleChoices);
            singleChoices.forEach(s -> s.getTestPapers().add(testPaper));
            singleChioceRepository.saveAll(singleChoices);
            testPaper.setSingleChoiceArrayList(singleChoices);
        }
        if (multiple!=null){
            List<MultipleChoice> multipleChoices = multipleChoiceRepository.findAllById(Arrays.asList(multiple));
            multipleChoices.forEach(m -> m.getTestPapers().add(testPaper));
            multipleChoiceRepository.saveAll(multipleChoices);
            testPaper.setMultipleChoiceArrayList(multipleChoices);
        }
        if (judge!=null){
            List<Judge> judges = judgeRepository.findAllById(Arrays.asList(judge));
            judges.forEach(j -> j.getTestPapers().add(testPaper));
            judgeRepository.saveAll(judges);
            testPaper.setJudgeArrayList(judges);
        }
        if (subjective!=null){
            List<SubjectiveItem> subjectiveItems = subjectiveItemRepository.findAllById(Arrays.asList(subjective));
            subjectiveItems.forEach(su -> su.getTestPapers().add(testPaper));
            subjectiveItemRepository.saveAll(subjectiveItems);
            testPaper.setSubjectiveItems(subjectiveItems);
        }

        testPaperRepository.save(testPaper);
    }

    @Override
    public List<TestPaperExtension> getTestpaperList() {
        return testPaperRepository.findTestPaperList();
    }

    @Override
    public TestPaper getTestById(Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        List<MultipleChoice> multipleChoices = testPaper.getMultipleChoiceArrayList();
        multipleChoices
                .forEach(s -> s.setMultipleOptions(multipleChoiceSevice.getOptionsById(s.getId())));
        return testPaper;
    }
}
