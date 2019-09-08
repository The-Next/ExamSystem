package com.linln.admin.system.controller;

import com.linln.common.data.PageSort;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.modules.system.entity.*;
import com.linln.modules.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Resource
    private AchievementService achievementService;

    @Resource
    private AchievementAnwserService achievementAnwserService;

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

    /**
     * @Author: 程佩
     * @Date: 2019/9/5 下午3:47
     * @Descript: 去更新试卷
     * @Version 1.0
     */
    @GetMapping("/toupdate/{id}")
    public String toUpDate(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);
        request.setAttribute("testPaper",testPaper);
        return "/system/testpaper/update";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/5 下午5:40
     * @Descript: 去为试卷添加单选题
     * @Version 1.0
     */
    @GetMapping("/toaddSingle/{id}")
    public String toaddSingle(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);
        System.out.println(id);
        List<SingleChoice> singleChoices = singleChoiceService.getPageList().getContent();
        ArrayList<SingleChoice> arrayList = new ArrayList<>(singleChoices);
        arrayList.removeAll(testPaper.getSingleChoiceArrayList());
        request.setAttribute("id",id);
        request.setAttribute("list",arrayList);
        return "/system/testpaper/addsingle";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午2:21
     * @Descript: 去为试卷添加多选题
     * @Version 1.0
     */
    @GetMapping("/toaddMultiple/{id}")
    public String toaddMultiple(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);
        List<MultipleChoice> multipleChoices = multipleChoiceSevice.getPageList().getContent();
        ArrayList<MultipleChoice> arrayList = new ArrayList<>(multipleChoices);
        arrayList.removeAll(testPaper.getMultipleChoiceArrayList());
        request.setAttribute("id",id);
        request.setAttribute("list",arrayList);
        return "/system/testpaper/addmultiple";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午3:31
     * @Descript: 去为试卷添加判断题
     * @Version 1.0
     */
    @GetMapping("/toaddJudge/{id}")
    public String toaddJudge(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);
        List<Judge> judges = judgeService.getPageList().getContent();
        ArrayList<Judge> arrayList = new ArrayList<>(judges);
        arrayList.removeAll(testPaper.getJudgeArrayList());
        request.setAttribute("id",id);
        request.setAttribute("list",arrayList);
        return "/system/testpaper/addjudge";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午4:19
     * @Descript: 去为试卷添加主观题
     * @Version 1.0
     */
    @GetMapping("/toaddSubjective/{id}")
    public String toaddSubjective(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);
        List<SubjectiveItem> subjectiveItems = subjectiveItemService.getPageList().getContent();
        ArrayList<SubjectiveItem> arrayList = new ArrayList<>(subjectiveItems);
        arrayList.removeAll(testPaper.getSubjectiveItems());
        request.setAttribute("id",id);
        request.setAttribute("list",arrayList);
        return "/system/testpaper/addsubjectiveitem";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/8 下午12:53
     * @Descript: 修改试卷信息
     * @Version 1.0
     */
    @PostMapping("/updataTestPaper")
    public String updataTestPaper(HttpServletRequest request,TestPaper testPaper){
        testPaperService.updataTestPaper(testPaper);
        return "redirect:/system/testpaper/index";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/5 下午9:13
     * @Descript: 添加单选题
     * @Version 1.0
     */
    @ResponseBody
    @PostMapping("/addSingle")
    public ResultVo addSingle(HttpServletRequest request, Integer[] single, Integer id){
        try {
            testPaperService.saveSingle(single,id);
            return ResultVoUtil.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("添加失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/5 下午10:14
     * @Descript: 删除单选题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/deleteSingle")
    public ResultVo deleteSingle(HttpServletRequest request,@RequestParam(value = "ids", required = false) Integer single,@RequestParam(value = "id", required = false) Integer id){
        try {
            testPaperService.deleteSingle(single,id);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午2:14
     * @Descript: 添加多选题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/addMultiple")
    public  ResultVo addMultiple(HttpServletRequest request, Integer[] multiple, Integer id){
        try {
            testPaperService.saveMultiple(multiple,id);
            return ResultVoUtil.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("添加失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午2:18
     * @Descript: 删除多选题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("deleteMultiple")
    public ResultVo deleteMultiple(HttpServletRequest request,@RequestParam(value = "ids", required = false) Integer multiple,@RequestParam(value = "id", required = false) Integer id){
        try {
            testPaperService.deleteMultiple(multiple,id);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午3:34
     * @Descript: 添加判断题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/addJudge")
    public ResultVo addJudge(HttpServletRequest request, Integer[] judge, Integer id){
        try {
            testPaperService.saveJudge(judge,id);
            return ResultVoUtil.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("添加失败");
        }
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午3:37
     * @Descript: 删除判断题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/deleteJudge")
    public ResultVo deleteJudge(HttpServletRequest request,@RequestParam(value = "ids", required = false) Integer judge,@RequestParam(value = "id", required = false) Integer id){
        try {
            testPaperService.deleteJudge(judge,id);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午4:27
     * @Descript: 添加主观题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/addSubjectiveItem")
    public ResultVo addSubjectiveItem(HttpServletRequest request, Integer[] subjectiveItem, Integer id){
        try {
            testPaperService.saveSubjective(subjectiveItem,id);
            return ResultVoUtil.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("添加失败");
        }
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/6 下午4:33
     * @Descript: 删除主观题
     * @Version 1.0
     */
    @ResponseBody
    @RequestMapping("/deleteSubjectiveItem")
    public ResultVo deleteSubjectiveItem(HttpServletRequest request,@RequestParam(value = "ids", required = false) Integer subjectiveItem,@RequestParam(value = "id", required = false) Integer id){
        try {
            testPaperService.deleteSubjective(subjectiveItem,id);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }



    /**
     * @Author: 程佩
     * @Date: 2019/9/2 下午9:10
     * @Descript: 获取试卷（批改用）
     * @Version 1.0
     */
    @RequestMapping("/getTextPaper")
    @RequiresPermissions("system:testpaper:getTextPaper")
    public String getPaper(HttpServletRequest request){
        Page<TestPaper> page = testPaperService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/testpaper/correcttextlist";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/2 下午10:03
     * @Descript: 根据id获取该场考试人员信息
     * @Version 1.0
     */
    @GetMapping("/getCorrectListById/{id}")
    public String getCorrectListById(@PathVariable("id") Integer id,HttpServletRequest request){
        TestPaper testPaper = testPaperService.getTestById(id);

        List<Achievement> list = achievementService.getByTestId(id);
        request.setAttribute("list",list);
        request.setAttribute("testPaper",testPaper);
        return "/system/testpaper/correctpeoplelist";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/9/4 上午10:59
     * @Descript: 批改试卷
     * @Version 1.0
     */
    @GetMapping("/getPaperById/{id}")
    public String getPaperById(@PathVariable("id") Integer id,HttpServletRequest request){
        Achievement achievement = achievementService.getById(id);
        TestPaper testPaper = testPaperService.getTestById(achievement.getTestId());
        List<AchievementAnswer> list = achievementAnwserService.getlistByAchievementId(id);
        request.setAttribute("achievement",achievement);
        request.setAttribute("testPaper",testPaper.getTestname());
        request.setAttribute("list",list);
        return "/system/testpaper/correct";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/9/4 下午7:00
     * @Descript: 更新分数
     * @Version 1.0
     */
    @PostMapping("/update")
    public String update(QueryVo queryVo,Integer id,Integer achievementid){
        int point = 0;
        for (AchievementAnswer a:queryVo.getAchievementAnswer()){
            achievementAnwserService.update(a);
            point += a.getGiveMark();
        }

        Achievement achievement = achievementService.getById(achievementid);
        achievement.setTotlePoint(achievement.getJudgePoint()+achievement.getMultipleChoicePoint()+achievement.getSingleChoicePoint()+point);

        achievementService.save(achievement);

        return "redirect:/system/testpaper/getCorrectListById/"+id;
    }
}
