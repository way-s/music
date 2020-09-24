package cn.huanhu.controller;

import cn.huanhu.entity.Consumer;
import cn.huanhu.service.ConsumerService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author m
 * @className ConsumerController
 * @description 消费者API
 * @date 2020/9/10
 */
@Controller
@RequestMapping("user/")
public class ConsumerController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加用户 v-1
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addUserV1(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phone_num").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avator = request.getParameter("avator").trim();

        if ("".equals(username)) {
            json.put("code", 0);
            json.put("msg", "用户名或密码错误");
            return json;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.parseByte(sex));
        if ("".equals(phoneNum)) {
            consumer.setPhoneNum(null);
        } else {
            consumer.setPhoneNum(phoneNum);
        }

        if ("".equals(email)) {
            consumer.setEmail(null);
        } else {
            consumer.setEmail(email);
        }

        consumer.setLocation(location);
        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setAvator(avator);
        consumer.setPhoneNum(password);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        log.info(consumer.toString());
        // 插入数据
        try {
            boolean result = consumerService.insert(consumer);
            if (result) {
                json.put("code", 1);
                json.put("msg", "注册成功");
            } else {
                json.put("code", 0);
                json.put("msg", "注册失败");
            }
        } catch (Exception e) {
            json.put("code", 0);
            json.put("msg", "已经注册过了不可以重复注册！");
        }
        return json;
    }


    /**
     * 判断是否登录成功
     *
     * @param request request
     * @param session session
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session) {

        JSONObject json = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean res = consumerService.verityPassword(username, password);

        if (res) {
            json.put("code", 1);
            json.put("msg", "登录成功");
            json.put("userMsg", consumerService.queryByName(username));
            session.setAttribute("username", username);
        } else {
            json.put("code", 0);
            json.put("msg", "用户名或密码错误");
        }
        return json;
    }

    /**
     * 返回所有用户
     */
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object allUser() {
        return consumerService.allUser();
    }

    /**
     * 返回指定ID的用户
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Object userOfId(HttpServletRequest request) {
        String id = request.getParameter("id");
        return consumerService.queryById(Integer.parseInt(id));
    }

    /**
     * 删除用户
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object deleteUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        log.info("user/delete?id=" + id);
        return consumerService.deleteById(Integer.parseInt(id));
    }

    /**
     * 更新用户信息
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object updateUserMsg(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phone_num").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avator = request.getParameter("avator").trim();

        if ("".equals(username)) {
            json.put("code", 0);
            json.put("msg", "用户名或密码错误");
            return json;
        }
        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.valueOf(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        consumer.setUpdateTime(new Date());

        //更新用户信息
        boolean res = consumerService.update(consumer);
        if (res) {
            json.put("code", 1);
            json.put("msg", "修改成功");
        } else {
            json.put("code", 0);
            json.put("msg", "修改失败");
        }
        return json;
    }

    /**
     * 更新用户头像
     *
     * @param upFile 上传的文件
     * @param id     id
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile upFile, @RequestParam("id") Integer id) {
        JSONObject json = new JSONObject();

        if (upFile.isEmpty()) {
            json.put("code", 0);
            json.put("msg", "文件上传失败！");
            return json;
        }
        // 文件名= 当前时间（精确到毫秒）+原来的文件名 避免上传的文件（图片）重复 如果上传了两个文件 将原来的文件覆盖
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
        // 获取文件路径
        String filePath = "E:/music/img/CHP/" + id + "/";
        // 如果文件路径不存在，添加文件路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        // 实际的文件地址
        File dest = new File(filePath + fileName);
        // 存储到数据库中的相对文件地址
        String storeUrlPath = "/CHP/" + id+"/"+ fileName;

        try {
            upFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeUrlPath);
            // 查询旧图片地址
            String fileAddr = "E:/music/img/";
            String oldPic = consumerService.queryOldPic(id);
            log.info("song/img/update->" + "\n" + "filename->" + fileName + "\n" + "filePath->" + filePath + "\n" + "dest->"
                    + dest + "\n" + "storeUrlPath->" + storeUrlPath + "\n" + "oldPic->" + oldPic + "\t" + fileAddr + oldPic);
            File oldFile = new File(fileAddr + oldPic);
            //删除旧图片
            if (oldFile.exists()) {
                oldFile.delete();
            }

            // 更新用户头像
            boolean result = consumerService.update(consumer);
            if (result) {
                json.put("code", 1);
                json.put("avator", storeUrlPath);
                json.put("msg", "上传成功");
            } else {
                json.put("code", 0);
                json.put("msg", "上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put("code", 0);
            json.put("msg", "上传失败" + e.getMessage());
            return json;
        }
    }
}
