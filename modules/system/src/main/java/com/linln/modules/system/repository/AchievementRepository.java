package com.linln.modules.system.repository;

import com.linln.modules.system.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:24
 * @Descript: AchievementJpa
 * @Version 1.0
 */
public interface AchievementRepository extends BaseJPA<Achievement> {
    public List<Achievement> findAchievementByTestId(Integer id);
}
