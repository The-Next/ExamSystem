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
 * @Date: 2019/7/30 下午4:50
 * @Descript: 个人成绩
 * @Version 1.0
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "achievement")
public class Achievement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//考试成绩编号
    @Column(name = "testId")
    private Integer testId;//考试编号
    @Column(name = "userId")
    private Integer userId;//用户编号
    @Column(name = "userName")
    private String userName;//用户姓名
    @Column(name = "singleChoicePoint")
    private Integer singleChoicePoint;//单选成绩
    @Column(name = "multipleChoicePoint")
    private Integer multipleChoicePoint;//多选成绩
    @Column(name = "judgePoint")
    private Integer judgePoint;//判断题成绩
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
