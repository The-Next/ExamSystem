package com.linln.admin.system.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.annotation.EntityParam;
import com.linln.modules.system.domain.ActionLog;
import com.linln.modules.system.entity.SingleChoice;
import com.linln.modules.system.service.SingleChoiceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/7/31 下午4:30
 * @Descript: 单选题控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/singlechoice")
public class SingleChoiceController {
    @Resource
    private SingleChoiceService singleChoiceService;

    /**
     * @Author: 程佩
     * @Date: 2019/7/31 下午4:33
     * @Descript: 添加、修改、保存单选
     * @Version 1.0
     */
    @PostMapping("/add")
    @ResponseBody
    @RequiresPermissions({"system:singlechoice:toadd","system:singlechoice:toEdit"})
    public ResultVo addItem(@EntityParam SingleChoice singleChoice){
        try {
            singleChoiceService.add(singleChoice);
            return ResultVoUtil.SAVE_SUCCESS;
        }catch (Exception e){
            return ResultVoUtil.success("操作失败");
        }


    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/1 下午4:42
     * @Descript: 去添加
     * @Version 1.0
     */
    @GetMapping("/toadd")
    @RequiresPermissions("system:singlechoice:toadd")
    public String toAdd(){
        return "/system/singlechoice/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/7/31 下午4:49
     * @Descript: 获取所有单选题
     * @Version 1.0
     */
    @GetMapping("/getAll")
    @RequiresPermissions("system:singlechoice:getAll")
    public String selectAll(HttpServletRequest request){
        //分页
        Page<SingleChoice> page = singleChoiceService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/singlechoice/index";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/7/31 下午4:55
     * @Descript: 去编辑界面，根据id选取单选题
     * @Version 1.0
     */
    @GetMapping("/toEdit/{id}")
    @RequiresPermissions("system:singlechoice:toEdit")
    public String selectById(HttpServletRequest request,@PathVariable("id") Integer id){
        Optional<SingleChoice> singleChoice = singleChoiceService.getById(id);
        request.setAttribute("item",singleChoice.get());
        return "/system/singlechoice/add";
    }


    /**
     * @Author: 程佩
     * @Date: 2019/8/2 下午5:47
     * @Descript: 根据id删除题目
     * @Version 1.0
     */
    @RequestMapping("/delete")
    @RequiresPermissions("system:singlechoice:delete")
    @ResponseBody
    public ResultVo deletById(@RequestParam(value = "ids", required = false) Integer id){
        try {
            singleChoiceService.delete(id);
            return ResultVoUtil.success("删除题目成功");
        }catch (Exception e){
            return ResultVoUtil.success("删除题目失败");
        }
    }
}
