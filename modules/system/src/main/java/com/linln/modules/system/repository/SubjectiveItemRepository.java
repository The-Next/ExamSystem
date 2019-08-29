package com.linln.modules.system.repository;

import com.linln.modules.system.entity.SubjectiveItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:38
 * @Descript: SubjectiveItemJpa
 * @Version 1.0
 */
public interface SubjectiveItemRepository extends JpaRepository<SubjectiveItem,Integer> {
}
