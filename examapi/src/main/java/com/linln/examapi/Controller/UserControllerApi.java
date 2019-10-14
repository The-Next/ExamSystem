package com.linln.examapi.Controller;

import com.linln.common.data.URL;
import com.linln.common.enums.ResultEnum;
import com.linln.common.enums.StatusEnum;
import com.linln.common.exception.ResultException;
import com.linln.common.utils.EncryptUtil;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.action.UserAction;
import com.linln.component.actionLog.annotation.ActionLog;
import com.linln.component.jwt.annotation.IgnorePermissions;
import com.linln.component.jwt.annotation.JwtPermissions;
import com.linln.component.jwt.config.properties.JwtProjectProperties;
import com.linln.component.jwt.enums.JwtResultEnums;
import com.linln.component.jwt.utlis.JwtUtil;
import com.linln.component.shiro.ShiroUtil;
import com.linln.modules.system.domain.User;
import com.linln.modules.system.repository.UserRepository;
import com.linln.modules.system.service.RoleService;
import com.linln.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: 程佩
 * @Date: 2019/8/26 下午5:29
 * @Descript: 前端用户登录api
 * @Version 1.0
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/api/user")
public class UserControllerApi { //后面加一个Api的原因是因为UserController在admin模块中出现，无法再次注入

    @Resource
    private RoleService roleService;

    @Resource
    private JwtProjectProperties properties;

    @Resource
    private UserService userService;
    /**
     * @Author: 程佩
     * @Date: 2019/8/28 下午3:23
     * @Descript: 登录接口，权限验证
     * @Version 1.0
     */
    @IgnorePermissions
    @CrossOrigin(allowCredentials = "true")
    @RequestMapping("/login")
    public ResultVo login(@RequestBody User u){
        // 根据用户名获取系统用户数据
        User user = userService.getByName(u.getUsername());
        if (user == null) {
            throw new ResultException(JwtResultEnums.AUTH_REQUEST_ERROR);
        } else if (user.getStatus().equals(StatusEnum.FREEZED.getCode())){
            throw new ResultException(JwtResultEnums.AUTH_REQUEST_LOCKED);
        } else {
            // 对明文密码加密处理
            String encrypt = EncryptUtil.encrypt(u.getPassword(), user.getSalt());
            // 判断密码是否正确
            if (encrypt.equals(user.getPassword())) {
                String token = JwtUtil.getToken(u.getUsername(), properties.getSecret(), properties.getExpired());
                return ResultVoUtil.success("登录成功", token);
            } else {
                throw new ResultException(JwtResultEnums.AUTH_REQUEST_ERROR);
            }
        }
    }


    /**
     * @Author: 程佩
     * @Date: 2019/8/28 下午3:28
     * @Descript: 根据id获取用户信息
     * @Version 1.0
     */
    @JwtPermissions
    @CrossOrigin(allowCredentials = "true")
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/getById")
    public User getUser(){
        String name = JwtUtil.getSubject();
        User user = userService.getByName(name);
        user.setPassword("");
        user.setSalt("");
        return user;
    }
}
