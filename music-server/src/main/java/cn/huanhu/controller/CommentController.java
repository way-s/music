package cn.huanhu.controller;

import cn.huanhu.entity.Comment;
import cn.huanhu.service.CommentService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author m
 * @className CommentController
 * @description 评论API
 * @date 2020/9/13
 */
@Controller
@RequestMapping("comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 更新评论
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String userId = request.getParameter("userId").trim();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        String content = request.getParameter("content").trim();
        String type = request.getParameter("type").trim();
        String up = request.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        if (songId.isEmpty()){
            comment.setUserId(null);
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }

        if (songListId.isEmpty()){
            comment.setSongListId(null);
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        comment.setType(new Byte(type));
        comment.setUp(Integer.parseInt(up));

        boolean result = commentService.update(comment);
        if (result){
            json.put("code", 1);
            json.put("msg", "修改成功");
        }else {
            json.put("code", 0);
            json.put("msg", "修改失败");
        }
        return json;
    }

    /**
     * 删除评论
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Object deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return commentService.deleteById(Integer.parseInt(id));
    }

    /**
     * 获得指定歌单ID的评论列表
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "songList/detail", method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    /**
     * 获得指定歌曲ID的评论列表
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "song/detail", method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    /**
     * 提交评论
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songListId=request.getParameter("songListId");
        String songId=request.getParameter("songId");
        String content = request.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(songId));
        } else if (new Byte(type) == 1) {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

    /**
     * 点赞
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "like", method = RequestMethod.POST)
    public Object commentOfLike(HttpServletRequest request){

        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String up = request.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean res = commentService.update(comment);
        if (res){
            json.put("code", 1);
            json.put("msg", "点赞成功");
        }else {
            json.put("code", 0);
            json.put("msg", "点赞失败");
        }
        return json;
    }

}
