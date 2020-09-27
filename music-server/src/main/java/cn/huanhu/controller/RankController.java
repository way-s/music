package cn.huanhu.controller;

import cn.huanhu.entity.Rank;
import cn.huanhu.service.RankService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author m
 * @className RankController
 * @description 评分API
 * @date 2020/9/26
 */
@Controller
@RequestMapping("rank")
public class RankController {

    private static final Logger log = LoggerFactory.getLogger(RankController.class);

    @Autowired
    private RankService rankService;

    /**
     * 提交评分
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String songListId = request.getParameter("songListId").trim();
        String consumerId = request.getParameter("consumerId").trim();
        String score = request.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setSongListId(Long.parseLong(songListId));
        rank.setConsumerId(Long.parseLong(consumerId));
        rank.setScore(Integer.parseInt(score));

        // 提交评分
        boolean res = rankService.addRank(rank);
        if (res){
            json.put("code", 1);
            json.put("msg", "评价成功");
        }else {
            json.put("code", 0);
            json.put("msg", "评价失败");
        }
        return json;
    }

    /**
     * 获取指定歌单的评分
     * @param request request
     * @return json
     */
    @RequestMapping(value = "q", method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        log.info("rank/q->songListId->"+songListId);
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }
}
