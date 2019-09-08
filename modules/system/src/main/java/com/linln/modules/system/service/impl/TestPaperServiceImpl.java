package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.*;
import com.linln.modules.system.repository.*;
import com.linln.modules.system.service.MultipleChoiceSevice;
import com.linln.modules.system.service.TestPaperService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Resource
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    @PostConstruct
    public void initFactory(){
        queryFactory=new JPAQueryFactory(entityManager);
    }


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
//            singleChoices.forEach(s -> s.getTestPapers().add(testPaper));
//            singleChioceRepository.saveAll(singleChoices);
            testPaper.setSingleChoiceArrayList(singleChoices);
        }
        if (multiple!=null){
            List<MultipleChoice> multipleChoices = multipleChoiceRepository.findAllById(Arrays.asList(multiple));
//            multipleChoices.forEach(m -> m.getTestPapers().add(testPaper));
//            multipleChoiceRepository.saveAll(multipleChoices);
            testPaper.setMultipleChoiceArrayList(multipleChoices);
        }
        if (judge!=null){
            List<Judge> judges = judgeRepository.findAllById(Arrays.asList(judge));
//            judges.forEach(j -> j.getTestPapers().add(testPaper));
//            judgeRepository.saveAll(judges);
            testPaper.setJudgeArrayList(judges);
        }
        if (subjective!=null){
            List<SubjectiveItem> subjectiveItems = subjectiveItemRepository.findAllById(Arrays.asList(subjective));
//            subjectiveItems.forEach(su -> su.getTestPapers().add(testPaper));
//            subjectiveItemRepository.saveAll(subjectiveItems);
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

    @Override
    public void saveSingle(Integer[] single,Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        ArrayList<SingleChoice> list = (ArrayList<SingleChoice>) singleChioceRepository.findAllById(Arrays.asList(single));
        testPaper.getSingleChoiceArrayList().addAll(list);
        testPaperRepository.save(testPaper);
    }

    @Override
    public void deleteSingle(Integer single, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        Iterator<SingleChoice> iterator = testPaper.getSingleChoiceArrayList().iterator();
        while (iterator.hasNext()){
            SingleChoice item = iterator.next();
            if (item.getId().equals(single)){
                iterator.remove();
            }
        }
        testPaperRepository.save(testPaper);
    }

    @Override
    public void saveMultiple(Integer[] multiple, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        ArrayList<MultipleChoice> list = (ArrayList<MultipleChoice>) multipleChoiceRepository.findAllById(Arrays.asList(multiple));
        testPaper.getMultipleChoiceArrayList().addAll(list);
        testPaperRepository.save(testPaper);
    }

    @Override
    public void deleteMultiple(Integer multiple, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        Iterator<MultipleChoice> iterator = testPaper.getMultipleChoiceArrayList().iterator();
        while (iterator.hasNext()){
            MultipleChoice item = iterator.next();
            if (item.getId().equals(multiple)){
                iterator.remove();
            }
        }
        testPaperRepository.save(testPaper);
    }

    @Override
    public void saveJudge(Integer[] judge, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        ArrayList<Judge> arrayList = (ArrayList<Judge>) judgeRepository.findAllById(Arrays.asList(judge));
        testPaper.getJudgeArrayList().addAll(arrayList);
        testPaperRepository.save(testPaper);
    }

    @Override
    public void deleteJudge(Integer judge, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        Iterator<Judge> iterator = testPaper.getJudgeArrayList().iterator();
        while (iterator.hasNext()){
            Judge item = iterator.next();
            if (item.getId().equals(judge)){
                iterator.remove();
            }
        }
        testPaperRepository.save(testPaper);
    }

    @Override
    public void saveSubjective(Integer[] subjective, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        ArrayList<SubjectiveItem> arrayList = (ArrayList<SubjectiveItem>)subjectiveItemRepository.findAllById(Arrays.asList(subjective));
        testPaper.getSubjectiveItems().addAll(arrayList);
        testPaperRepository.save(testPaper);
    }

    @Override
    public void deleteSubjective(Integer subjective, Integer id) {
        TestPaper testPaper = testPaperRepository.findById(id).get();
        Iterator<SubjectiveItem> iterator = testPaper.getSubjectiveItems().iterator();
        while (iterator.hasNext()){
            SubjectiveItem item = iterator.next();
            if (item.getId().equals(subjective)){
                iterator.remove();
            }
        }
        testPaperRepository.save(testPaper);
    }

    @Transactional
    @Override
    public void updataTestPaper(TestPaper testPaper) {
        QTestPaper t = QTestPaper.testPaper;
        queryFactory
                .update(t)
                .set(t.startTime,testPaper.getStartTime())
                .set(t.duration,testPaper.getDuration())
                .set(t.testname,testPaper.getTestname())
                .set(t.endTime,testPaper.getEndTime())
                .where(t.id.eq(testPaper.getId()))
                .execute();
    }
}
