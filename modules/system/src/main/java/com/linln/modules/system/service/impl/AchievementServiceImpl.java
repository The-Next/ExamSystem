package com.linln.modules.system.service.impl;

import com.linln.modules.system.entity.Achievement;
import com.linln.modules.system.repository.AchievementRepository;
import com.linln.modules.system.service.AchievementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/9/1 下午3:08
 * @Descript:
 * @Version 1.0
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    @Resource
    private AchievementRepository achievementRepository;

    @Override
    public void save(Achievement achievement) {
        achievementRepository.save(achievement);
    }

    @Override
    public List<Achievement> getByTestId(Integer integer) {
        return achievementRepository.findAchievementByTestId(integer);
    }

    @Override
    public Achievement getById(Integer id) {
        return achievementRepository.findById(id).get();
    }
}
