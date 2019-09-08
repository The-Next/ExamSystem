package com.linln.modules.system.repository;

import com.linln.modules.system.entity.TestPaper;
import com.linln.modules.system.entity.TestPaperExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午3:42
 * @Descript: RepositoryJpa
 * @Version 1.0
 */
public interface TestPaperRepository extends BaseJPA<TestPaper> {

    @Query("select new com.linln.modules.system.entity.TestPaperExtension(id,testname,startTime,endTime,duration,createDate,updateDate,manualOperation,publisher) from TestPaper")
    public List<TestPaperExtension> findTestPaperList();
}
