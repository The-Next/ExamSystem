package com.linln.admin.system.controller;

import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.annotation.EntityParam;
import com.linln.modules.system.entity.SubjectiveItem;
import com.linln.modules.system.service.SubjectiveItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 程佩
 * @Date: 2019/8/5 上午11:27
 * @Descript: 主观题控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/subjectiveitem")
public class SubjectiveItemController {

    @Resource
    private SubjectiveItemService subjectiveItemService;

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 下午3:26
     * @Descript: 获取所有主观题信息
     * @Version 1.0
     */
    @GetMapping("/index")
    @RequiresPermissions("system:subjectiveitem:index")
    public String selectAll(HttpServletRequest request){
        Page<SubjectiveItem> page = subjectiveItemService.getPageList();
        request.setAttribute("list",page.getContent());
        request.setAttribute("page",page);
        return "/system/subjectiveitem/index";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 下午3:30
     * @Descript: 去添加页面
     * @Version 1.0
     */
    @GetMapping("/add")
    @RequiresPermissions("system:subjectiveitem:add")
    public String add(){
        return "/system/subjectiveitem/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 下午3:54
     * @Descript: 保存信息
     * @Version 1.0
     */
    @PostMapping("/save")
    @ResponseBody
    @RequiresPermissions({"system:subjectiveitem:add"})
    public ResultVo save(@EntityParam SubjectiveItem subjectiveItem){
        try {
            subjectiveItemService.save(subjectiveItem);
            return ResultVoUtil.success("保存成功");
        }catch (Exception e){
            return ResultVoUtil.success("保存失败");
        }
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 下午5:05
     * @Descript: 去编辑界面
     * @Version 1.0
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:subjectiveitem:edit")
    public String edit(HttpServletRequest request,@PathVariable("id") Integer id){
        Optional<SubjectiveItem> subjectiveItem = subjectiveItemService.getById(id);
        request.setAttribute("item",subjectiveItem.get());
        return "/system/subjectiveitem/add";
    }

    /**
     * @Author: 程佩
     * @Date: 2019/8/5 下午5:21
     * @Descript: 删除信息
     * @Version 1.0
     */
    @RequestMapping("/delete")
    @RequiresPermissions("system:multiplechoice:delete")
    @ResponseBody
    public ResultVo delete(@RequestParam(value = "ids", required = false) Integer id){
        try {
            subjectiveItemService.delete(id);
            return ResultVoUtil.success("删除成功");
        } catch (Exception e) {
            return ResultVoUtil.success("删除失败");
        }
    }
}