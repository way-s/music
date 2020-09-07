package cn.huanhu.controller;

import cn.huanhu.entity.Admin;
import cn.huanhu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author m
 * @className WebController
 * @description web
 * @date 2020/9/3
 */
@Controller
public class WebController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "q")
    public @ResponseBody Admin queryById() {
        return adminService.queryById(1);
    }
}
