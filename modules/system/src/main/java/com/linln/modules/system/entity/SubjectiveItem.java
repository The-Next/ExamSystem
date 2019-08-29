package com.linln.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
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
 * @Date: 2019/7/30 下午3:56
 * @Descript: 主观题
 * @Version 1.0
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subjectiveitem")
public class SubjectiveItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//题目编号
    @Column(name = "subject")
    private String subject;//题目
    @Column(name = "mark")
    private Integer mark;//分值
    @Transient
    private String anwser;//填写答案
    @JsonIgnoreProperties(value = "subjectiveItems")
    @ManyToMany(mappedBy = "subjectiveItems")
    private List<TestPaper> testPapers;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
