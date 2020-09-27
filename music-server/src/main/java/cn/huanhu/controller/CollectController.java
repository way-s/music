package cn.huanhu.controller;

import cn.huanhu.entity.Collect;
import cn.huanhu.service.CollectService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    /**
     * 添加收藏的歌曲
     * type: 0 代表歌曲， 1 代表歌单
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId=request.getParameter("songId");
        String songListId=request.getParameter("songListId");

        log.info("userId->"+userId + "  " + "type->" + type + "  " +"songId->" + songId);
        // 判断歌曲是否存在歌单中
        boolean isExist = collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId));
        if (songId.isEmpty()){
            log.info("收藏歌曲为空");
            json.put("code", 0);
            json.put("msg", "收藏歌曲为空");
            return json;
        }
        else if (isExist) {
            collectService.deleteCollect(Integer.parseInt(userId),Integer.parseInt(songId));
            json.put("code", 2);
            json.put("msg", "取消收藏");
            return json;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(songId));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(songListId));
        }
        collect.setCreateTime(new Date());
        // 添加收藏
        boolean res = collectService.addCollection(collect);
        if (res){
            json.put("code", 1);
            json.put("msg", "收藏成功");
        }else {
            json.put("code", 0);
            json.put("msg", "收藏失败");
        }
        return json;
    }
}
