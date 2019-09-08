package com.linln.examapi.Controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.jwt.annotation.IgnorePermissions;
import com.linln.component.jwt.annotation.JwtPermissions;
import com.linln.component.jwt.utlis.JwtUtil;
import com.linln.modules.system.domain.User;
import com.linln.modules.system.entity.*;
import com.linln.modules.system.repository.MultipleOptionsRepository;
import com.linln.modules.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    private AchievementService achievementService;
    @Resource
    private AchievementAnwserService achievementAnwserService;

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

    /**
     * @Author: 程佩
     * @Date: 2019/8/30 下午8:55
     * @Descript: 批改试卷
     * @Version 1.0
     */
    @JwtPermissions
    @ApiOperation("批改试卷")
    @PostMapping("/paperCorrection")
    public ResultVo paperCorrection(@RequestBody TestPaper testPaper){
        try {
            //获取考试人员信息
            String username = JwtUtil.getSubject();//学号
            User user = userService.getByName(username);

            //生成答案信息
            Achievement answer = new Achievement();
            answer.setTestId(testPaper.getId());
            answer.setUserId((int) user.getId().longValue());
            answer.setUserNickName(user.getNickname());

            //计算客观题答案
            int single = 0;
            int multipleChoice = 0;
            int judge = 0;

            //用来判断多选题正确
            boolean flag = true;

            //选择题答案结算
            for (SingleChoice s : testPaper.getSingleChoiceArrayList()){
                if (s.getChoice()!=null) {
                    if (s.getChoice().equals(s.getAnswer())) {
                        single += s.getMark();
                    }
                }
            }


            //多选题答案结算
            for (MultipleChoice m : testPaper.getMultipleChoiceArrayList()){
                for (MultipleOptions mu : m.getMultipleOptions()){
                    if (mu.getIsChoose()==null){
                        flag = false;
                    }else if (mu.getIsChoose().equals(mu.getIsTrue())){
                        flag = true;
                    }else {
                        flag = false;
                    }
                }
                if (flag == true){
                    multipleChoice += m.getMark();
                }
            }
            //判断题答案结算
            for (Judge j : testPaper.getJudgeArrayList()){
                if (j.getUserChoice() != null){
                    if (j.getUserChoice().equals(j.getIsTrue())){
                        judge += j.getMark();
                    }
                }
            }


            answer.setSingleChoicePoint(single);
            answer.setMultipleChoicePoint(multipleChoice);
            answer.setJudgePoint(judge);
            answer.setTotlePoint(single+multipleChoice+judge);
            //保存信息
            achievementService.save(answer);


            //保存主观题信息
            for (SubjectiveItem sub : testPaper.getSubjectiveItems()){
                AchievementAnswer achievementAnswer = new AchievementAnswer();
                achievementAnswer.setTestId(testPaper.getId());
                achievementAnswer.setAchievementId(answer.getId());
                achievementAnswer.setMark(sub.getMark());
                achievementAnswer.setAnwser(sub.getAnwser());
                achievementAnswer.setSubject(sub.getSubject());
                achievementAnswer.setUserId(user.getId().intValue());
                achievementAnwserService.save(achievementAnswer);
            }
            return ResultVoUtil.success("提交成功!");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVoUtil.error("提交异常");
        }
    }
}
