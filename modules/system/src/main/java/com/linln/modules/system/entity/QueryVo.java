package com.linln.modules.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/8/2 下午6:19
 * @Descript: 封装类
 * @Version 1.0
 */
@Data
public class QueryVo implements Serializable {
    private MultipleChoice multipleChoice;
    private List<MultipleOptions> multipleOptions;
    private List<AchievementAnswer> achievementAnswer;
}
