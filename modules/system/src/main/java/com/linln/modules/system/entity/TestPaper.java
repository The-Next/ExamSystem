package com.linln.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/30 下午4:37
 * @Descript: 试卷
 * @Version 1.0
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "testpaper")
public class TestPaper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//考试编号
    @Column(name = "testname")
    private String testname;//考试名称
    @Column(name = "startTime")
    private String startTime;//开始时间
    @Column(name = "endTime")
    private String endTime;//结束时间
    @Column(name = "duration")
    private Integer duration;//持续时间(单位：分钟)
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Column(name = "manualOperation")
    private Integer manualOperation;//是否手动组卷
    @Column(name = "publisher")
    private String publisher;//出卷人
    @JsonIgnoreProperties(value = "testPapers")
    @ManyToMany//多对多映射
    @JoinTable(name = "testPapers_singlechoice",joinColumns = @JoinColumn(name = "testPapers_id"),
            inverseJoinColumns = @JoinColumn(name = "singlechoice_id"))//外键关联
    private List<SingleChoice> singleChoiceArrayList;
    @JsonIgnoreProperties(value = "testPapers")
    @ManyToMany//多对多映射
    @JoinTable(name = "testPapers_multiplechoice",joinColumns = @JoinColumn(name = "testPapers_id"),
            inverseJoinColumns = @JoinColumn(name = "multiplechoice_id"))//外键关联
    private List<MultipleChoice> multipleChoiceArrayList;
    @JsonIgnoreProperties(value = "testPapers")
    @ManyToMany//多对多映射
    @JoinTable(name = "testPapers_judge",joinColumns = @JoinColumn(name = "testPapers_id"),
            inverseJoinColumns = @JoinColumn(name = "judge_id"))//外键关联
    private List<Judge> judgeArrayList;
    @JsonIgnoreProperties(value = "testPapers")
    @ManyToMany//多对多映射
    @JoinTable(name = "testPapers_subjective",joinColumns = @JoinColumn(name = "testPapers_id"),
            inverseJoinColumns = @JoinColumn(name = "subjective_id"))//外键关联
    private List<SubjectiveItem> subjectiveItems;
}
