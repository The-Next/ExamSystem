package com.linln.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/30 上午11:01
 * @Descript: 单选题
 * @Version 1.0
 */
//如果使用@Data就会造成java.lang.StackOverflowError: null异常
//因为会实现带关联表属性的hashCode和equals等方法，另一个办法就是不使用懒加载
//要么添加@EqualsAndHashCode注解
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "singlechoice")
public class SingleChoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//题目编号
    @Column(name = "subject")
    private String subject;//题目
    @Column(name = "choiceA")
    private String choiceA;//选项A
    @Column(name = "choiceB")
    private String choiceB;//选项B
    @Column(name = "choiceC")
    private String choiceC;//选项C
    @Column(name = "choiceD")
    private String choiceD;//选项D
    @Column(name = "answer")
    private String answer;//答案
    @Transient
    private String choice;//用户选择
    @Column(name = "mark")
    private Integer mark;//分值
    @JsonIgnoreProperties(value = "singleChoiceArrayList")
    @ManyToMany(mappedBy = "singleChoiceArrayList")
    private List<TestPaper> testPapers;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
