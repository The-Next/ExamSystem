package com.linln.modules.system.service;

import com.linln.modules.system.entity.AchievementAnswer;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/9/1 下午3:45
 * @Descript:
 * @Version 1.0
 */
public interface AchievementAnwserService {
    public void save(AchievementAnswer achievementAnswer);//保存主观题答案
    public List<AchievementAnswer> getlistByAchievementId(Integer id);//根据试卷信息获取主观题
    public void update(AchievementAnswer achievementAnswer);//修改主观题答案
}
