package cn.huanhu.controller;

import cn.huanhu.service.AdminService;
import cn.huanhu.utils.Constant;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author m
 * @className AdminController
 * @description 管理员API
 * @date 2020/9/5
 */
@Controller
@RequestMapping("login/")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 登录
     * @param userName name
     * @param passWord password
     * @param session session
     * @return json
     */
//    @PostMapping("login/{userName}/{passWord}")
//    public @ResponseBody
//    Object login(
//            @PathVariable("userName") String userName, @PathVariable("passWord") String passWord,
//            HttpSession session) {
//        JSONObject json = new JSONObject();
//        log.info("userName + passWord = " + userName + "\t" + passWord);
//        adminService.checkPassWord(userName, passWord);
//        if (adminService.checkPassWord(userName, passWord)) {
//            json.put(Constant.CODE, 1);
//            json.put(Constant.MSG, "登录成功");
//            session.setAttribute(Constant.NAME, userName);
//        } else {
//            json.put(Constant.CODE, 0);
//            json.put(Constant.MSG, "用户名或密码错误");
//        }
//        return json;
//    }

    /**
     * 登录 2
     * @param userName name
     * @param passWord password
     * @param session session
     * @return json
     */
    @PostMapping("login")
    public @ResponseBody
    Object login(
            @RequestParam("userName") String userName, @RequestParam("passWord") String passWord,
            HttpSession session) {
        JSONObject json = new JSONObject();
        log.info("userName + passWord = " + userName + "\t" + passWord);
        adminService.checkPassWord(userName, passWord);
        if (adminService.checkPassWord(userName, passWord)) {
            json.put(Constant.CODE, 1);
            json.put(Constant.MSG, "登录成功");
            session.setAttribute(Constant.NAME, userName);
        } else {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "用户名或密码错误");
        }
        return json;
    }
}
