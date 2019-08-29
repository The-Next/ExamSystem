package com.linln.admin.system.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.annotation.EntityParam;
import com.linln.modules.system.entity.Judge;
import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.service.JudgeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午9:55
 * @Descript: 判断题控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/judge")
public class JudgeController {

    @Resource
    private JudgeService judgeService;

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 上午10:12
     * @Descript: 获取所有判断题信息
     * @Version 1.0
     */
    @GetMapping("/getAll")
    @RequiresPermissions("system:judge:getAll")
    public String selectAll(HttpServletRequest request){
        Page<Judge> page = judgeService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/judge/index";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 上午10:14
     * @Descript: 去添加界面
     * @Version 1.0
     */
    @GetMapping("/add")
    @RequiresPermissions("system:judge:add")
    public String add(){
        return "/system/judge/add";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/8/5 上午10:57
     * @Descript: 去编辑页面
     * @Version 1.0
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:judge:edit")
    public String edit(HttpServletRequest request,@PathVariable("id") Integer id){
        Optional<Judge> judge = judgeService.getById(id);
        request.setAttribute("item",judge.get());
        return "/system/judge/add";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/8/5 上午10:43
     * @Descript: 保存判断题信息
     * @Version 1.0
     */
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions({"system:judge:add","system:judge:edit"})
    public ResultVo save(@EntityParam Judge judge){
        try {
            judgeService.save(judge);
            return ResultVoUtil.success("保存成功");
        }catch (Exception e){
            return ResultVoUtil.success("保存失败");
        }
    }

    @RequestMapping("/delete")
    @RequiresPermissions("system:judge:delete")
    @ResponseBody
    public ResultVo delete(@RequestParam(value = "ids", required = false) Integer id){
        try {
            judgeService.delete(id);
            return ResultVoUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }
}
