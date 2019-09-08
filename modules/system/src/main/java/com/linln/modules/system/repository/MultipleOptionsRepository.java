package com.linln.modules.system.repository;

import com.linln.modules.system.entity.MultipleOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:33
 * @Descript: MultipleOptionsJpa
 * @Version 1.0
 */
public interface MultipleOptionsRepository extends BaseJPA<MultipleOptions> {
    @Query("delete FROM MultipleOptions m where m.m_id = ?1")
    @Modifying
    @Transactional
    public void deleteByM_id(Integer mId);

    @Query("select m from MultipleOptions m where m.m_id = ?1")
    public List<MultipleOptions> findMultipleOptionsByM_id(Integer id);
}
