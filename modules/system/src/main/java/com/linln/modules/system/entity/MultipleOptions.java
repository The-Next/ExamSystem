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
 * @Date: 2019/7/30 下午2:40
 * @Descript: 多选题答案
 * @Version 1.0
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "multipleoptions")
public class MultipleOptions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//编号
    @Column(name = "m_id")
    private Integer m_id;//多选题id
    @Column(name = "m_option")
    private String m_option;//选项
    @Transient
    private Integer isChoose;//该选项是否被选择
    @Column(name = "isTrue")
    private Integer isTrue;//该选项是否是正确选项
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
