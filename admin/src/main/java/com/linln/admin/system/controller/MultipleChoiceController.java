package com.linln.admin.system.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.annotation.EntityParam;
import com.linln.modules.system.entity.MultipleChoice;
import com.linln.modules.system.repository.MultipleChoiceRepository;
import com.linln.modules.system.service.MultipleChoiceSevice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/3 下午3:00
 * @Descript: 多选题控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/multiplechoice")
public class MultipleChoiceController {

    @Resource
    private MultipleChoiceSevice multipleChoiceSevice;

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 上午10:12
     * @Descript: 获取所有多选题信息
     * @Version 1.0
     */
    @GetMapping("/getAll")
    @RequiresPermissions("system:multiplechoice:getAll")
    public String selectAll(HttpServletRequest request){
        Page<MultipleChoice> page = multipleChoiceSevice.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/multiplechoice/index";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/4 下午2:22
     * @Descript: 转到添加界面
     * @Version 1.0
     */
    @GetMapping("/add")
    @RequiresPermissions("/system/multiplechoice/add")
    public String add(){
        return "/system/multiplechoice/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/4 下午2:23
     * @Descript: 保存信息
     * @Version 1.0
     */
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions({"/system/multiplechoice/add","system:multiplechoice:edit"})
    public ResultVo save(@EntityParam MultipleChoice multipleChoice){
        try {
            multipleChoiceSevice.save(multipleChoice,multipleChoice.getMultipleOptions());
            return ResultVoUtil.success("保存成功");
        }catch (Exception e){
            return ResultVoUtil.success("保存失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/4 下午5:45
     * @Descript: 去修改数据
     * @Version 1.0
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:multiplechoice:edit")
    public String edit(HttpServletRequest request,@PathVariable("id") Integer id){
        Optional<MultipleChoice> multipleChoice = multipleChoiceSevice.getById(id);
        request.setAttribute("item",multipleChoice.get());
        return "/system/multiplechoice/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/4 下午8:33
     * @Descript: 删除多选题
     * @Version 1.0
     */
    @RequestMapping("/delete")
    @RequiresPermissions("system:multiplechoice:delete")
    @ResponseBody
    public ResultVo delete(@RequestParam(value = "ids", required = false) Integer id){
        try {
            multipleChoiceSevice.delete(id);
            return ResultVoUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/4 下午9:50
     * @Descript:
     * @Version 1.0
     */
    @RequestMapping("/deleteoption")
    @RequiresPermissions("system:multiplechoice:deleteoption")
    @ResponseBody
    public ResultVo deletOprions(@RequestParam(value = "ids", required = false) Integer id){
        try {
            System.out.println(id);
            multipleChoiceSevice.deleteOptionById(id);
            return ResultVoUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return ResultVoUtil.success("删除失败");
        }
    }
}

