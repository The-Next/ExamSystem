package com.linln.modules.system.repository;

import com.linln.modules.system.entity.AchievementAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:27
 * @Descript: AchievementAnwserJpa
 * @Version 1.0
 */
public interface AchievementAnwserRepository extends BaseJPA<AchievementAnswer> {
    /**
     * @Author: 程佩
     * @Date: 2019/9/4 上午8:26
     * @Descript: 根据Achievement的id获取信息
     * @Version 1.0
     */
    public List<AchievementAnswer> findAchievementAnswersByAchievementId(Integer integer);
}
