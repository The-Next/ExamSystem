package com.linln.modules.system.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: 程佩
 * @Date: 2019/8/28 下午8:19
 * @Descript: 部分TestPaper信息
 * @Version 1.0
 */
@Data
public class TestPaperExtension {
    private Integer id;//考试编号
    private String testname;//考试名称
    private String startTime;//开始时间
    private String endTime;//结束时间
    private Integer duration;//持续时间(单位：分钟)
    private Date createDate;
    private Date updateDate;
    private Integer manualOperation;//是否手动组卷
    private String publisher;//出卷人

    public TestPaperExtension(Integer id, String testname, String startTime, String endTime, Integer duration, Date createDate, Date updateDate, Integer manualOperation, String publisher) {
        this.id = id;
        this.testname = testname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.manualOperation = manualOperation;
        this.publisher = publisher;
    }
}
