package com.linln.modules.system.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 程佩
 * @Date: 2019/7/30 下午5:19
 * @Descript: 主观题答案
 * @Version 1.0
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "achievementanswer")
public class AchievementAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//编号
    @Column(name = "testId")
    private Integer testId;//考试编号
    @Column(name = "userId")
    private Integer userId;//用户编号
    @Column(name = "achievementId")
    private Integer achievementId;//个人成绩编号
    @Column(name = "subject")
    private String subject;//题目
    @Column(name = "anwser")
    private String anwser;//答案
    @Column(name = "mark")
    private Integer mark;//分值
    @Column(name = "isreadover",columnDefinition = "integer default 0")
    private Integer isReadOver;//是否批阅
    @Column(name = "giveMark")
    private Integer giveMark;//批改分数
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
