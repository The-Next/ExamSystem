package com.linln.examapi.Controller;

import com.linln.component.jwt.annotation.IgnorePermissions;
import com.linln.component.jwt.annotation.JwtPermissions;
import com.linln.modules.system.entity.MultipleOptions;
import com.linln.modules.system.entity.TestPaper;
import com.linln.modules.system.entity.TestPaperExtension;
import com.linln.modules.system.repository.MultipleOptionsRepository;
import com.linln.modules.system.service.MultipleChoiceSevice;
import com.linln.modules.system.service.TestPaperService;
import com.linln.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 程佩
 * @Date: 2019/8/28 下午6:50
 * @Descript: 考试管理
 * @Version 1.0
 */
@Api("考试管理")
@RestController
@RequestMapping("/api/test")
public class TestPaperControllerApi {
    @Resource
    private TestPaperService testPaperService;
    @Resource
    private UserService userService;

    @Resource
    private MultipleChoiceSevice multipleChoiceSevice;

    /**
     * @Author: 程佩
     * @Date: 2019/8/28 下午6:57
     * @Descript: 获取试卷列表
     * @Version 1.0
     */
    @JwtPermissions
    @ApiOperation("获取试卷列表")
    @GetMapping("/getTestList")
    public List<TestPaperExtension> getTestList(){
        List<TestPaperExtension> testPaperList = testPaperService.getTestpaperList();
        return testPaperList;
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/29 下午5:46
     * @Descript: 根据id获取试卷
     * @Version 1.0
     */
    @IgnorePermissions
    @ApiOperation("根据id获取试卷")
    @GetMapping("/getTeatById")
    public TestPaper getTeatById(Integer id){
        return testPaperService.getTestById(id);
    }
}
