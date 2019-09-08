package com.linln.modules.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author: 程佩
 * @Date: 2019/9/4 下午6:00
 * @Descript:
 * @Version 1.0
 */
@NoRepositoryBean
public interface BaseJPA<T> extends JpaRepository<T,Integer>,JpaSpecificationExecutor<T>,QuerydslPredicateExecutor<T> {
}
