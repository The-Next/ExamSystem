package com.linln.modules.system.service.impl;

import com.linln.modules.system.entity.Achievement;
import com.linln.modules.system.entity.AchievementAnswer;
import com.linln.modules.system.entity.QAchievementAnswer;
import com.linln.modules.system.repository.AchievementAnwserRepository;
import com.linln.modules.system.service.AchievementAnwserService;
import com.linln.modules.system.service.AchievementService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/9/1 下午3:46
 * @Descript: 主观题答案服务接口实现
 * @Version 1.0
 */
@Service
public class AchievementAnwserServiceImpl implements AchievementAnwserService {

    @Resource
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;
    @PostConstruct
    public void initFactory(){
        queryFactory=new JPAQueryFactory(entityManager);
    }

    @Resource
    private AchievementAnwserRepository achievementAnwserRepository;


    @Override
    public void save(AchievementAnswer achievementAnswer) {
        achievementAnwserRepository.save(achievementAnswer);
    }

    @Override
    public List<AchievementAnswer> getlistByAchievementId(Integer id) {
        return achievementAnwserRepository.findAchievementAnswersByAchievementId(id);
    }

    @Transactional
    @Override
    public void update(AchievementAnswer achievementAnswer) {
        QAchievementAnswer a = QAchievementAnswer.achievementAnswer;
        queryFactory
                .update(a)
                .set(a.giveMark,achievementAnswer.getGiveMark())
                .where(a.id.eq(achievementAnswer.getId()))
                .execute();
    }
}
