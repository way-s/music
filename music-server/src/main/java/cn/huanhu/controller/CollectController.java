package cn.huanhu.controller;

import cn.huanhu.service.CollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author m
 * @className CollectController
 * @description 收藏列表api
 * @date 2020/9/13
 */
@Controller
@RequestMapping("collection/")
public class CollectController {

    private static final Logger log = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private CollectService collectService;

//    /**
//     * 返回的指定用户ID收藏列表
//     * @param request request
//     * @return json
//     */
//    @ResponseBody
//    @RequestMapping(value = "detail", method = RequestMethod.GET)
//    public Object collectionOfUser(HttpServletRequest request){
//        String userId = request.getParameter("userId");
//        return collectService.collectionOfUser(Integer.parseInt(userId));
//    }

    /**
     * 返回的指定用户ID收藏列表
     * @param userId userId
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Object collectionOfUser(@RequestParam("userId")Integer userId){
        return collectService.collectionOfUser(userId);
    }

    /**
     * 根据用户ID和歌曲id删除收藏列表
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();
        String songId = request.getParameter("songId").trim();
        return collectService.deleteCollect(Integer.parseInt(userId),Integer.parseInt(songId));
    }

    /**
     * 返回所有用户收藏列表
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object allCollection(){
        return collectService.queryAllCollect();
    }

}
