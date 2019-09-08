package com.linln.modules.system.service;

import com.linln.modules.system.entity.Achievement;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/9/1 下午3:08
 * @Descript:
 * @Version 1.0
 */
public interface AchievementService{
    public void save(Achievement achievement);//保存答案信息
    public List<Achievement> getByTestId(Integer integer);//根据考试id获取信息
    public Achievement getById(Integer id);//根据id获取信息
}
