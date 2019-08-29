package com.linln.admin.system.controller;

import com.linln.common.data.PageSort;
import com.linln.modules.system.entity.*;
import com.linln.modules.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: 程佩
 * @Date: 2019/8/16 下午3:52
 * @Descript: 试卷组成控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/testpaper")
public class TestPaperController {

    @Resource
    private TestPaperService testPaperService;

    @Resource
    private JudgeService judgeService;

    @Resource
    private SingleChoiceService singleChoiceService;

    @Resource
    private MultipleChoiceSevice multipleChoiceSevice;

    @Resource
    private SubjectiveItemService subjectiveItemService;

    /**
     * @Author: 程佩
     * @Date: 2019/8/16 下午3:57
     * @Descript: 获取试卷列表
     * @Version 1.0
     */
    @RequestMapping("index")
    @RequiresPermissions("system:testpaper:index")
    public String selectAll(HttpServletRequest request){
        Page<TestPaper> page = testPaperService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/testpaper/index";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/8/16 下午4:04
     * @Descript: 添加试卷
     * @Version 1.0
     */
    @GetMapping("/add")
    @RequiresPermissions("system:testpaper:add")
    public String add(HttpServletRequest request){
        Page<SingleChoice> singleChoicePage = singleChoiceService.getPageList();
        Page<MultipleChoice> multipleChoicePage = multipleChoiceSevice.getPageList();
        Page<Judge> judgePage = judgeService.getPageList();
        Page<SubjectiveItem> subjectiveItemPage = subjectiveItemService.getPageList();
        request.setAttribute("singlelist",singleChoicePage.getContent());
        request.setAttribute("multiplelist",multipleChoicePage.getContent());
        request.setAttribute("judgelist",judgePage.getContent());
        request.setAttribute("subjectivelist",subjectiveItemPage.getContent());
        return "/system/testpaper/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/24 下午11:43
     * @Descript: 保存试卷
     * @Version 1.0
     */
    @PostMapping("/save")
    @RequiresPermissions("system:testpaper:add")
    public String save(HttpServletRequest request
            ,Integer[] single
            ,Integer[] multiple
            ,Integer[] judge
            ,Integer[] subjective
            ,TestPaper testPaper){
//        Arrays.asList(single).forEach(System.out::println);
//        Arrays.asList(multiple).forEach(System.out::println);
//        Arrays.asList(judge).forEach(System.out::println);
//        Arrays.asList(subjective).forEach(System.out::println);
        testPaperService.save(single,multiple,judge,subjective,testPaper);
        Page<TestPaper> page = testPaperService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/testpaper/index";
    }
}
