package cn.huanhu.controller;

import cn.huanhu.entity.ListSong;
import cn.huanhu.service.ListSongService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author m
 * @className ListSongController
 * @description 歌单API
 * @date 2020/9/11
 */
@Controller
@RequestMapping("listSong/")
public class ListSongController {

    private static final Logger log = LoggerFactory.getLogger(ListSongController.class);

    @Autowired
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(songId));
        listsong.setSongListId(Integer.parseInt(songListId));

        boolean res = listSongService.addListSong(listsong);
        if (res) {
            json.put("code", 1);
            json.put("msg", "添加成功");
        } else {
            json.put("code", 0);
            json.put("msg", "添加失败");
        }
        return json;
    }

    /**
     * 返回歌单里包含的所有歌曲
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object allListSong() {
        return listSongService.allListSong();
    }

    /**
     * 返回歌单里指定歌单ID的歌曲
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        return listSongService.queryListSongById(Integer.parseInt(songListId));
    }

    /**
     * 删除歌单里的歌曲
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object deleteListSong(HttpServletRequest request) {
        String songId = request.getParameter("songId");
        String id = request.getParameter("id");
        log.info("listSong/delete->" + songId + "id->" + id);
//        return listSongService.deleteById(Integer.parseInt(songId));
        return null;
    }

    /**
     * 返回歌单里指定歌单ID的歌曲
     * @param request    request
     * @param songListId 歌单id
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "detail/{songListId}", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest request, @PathVariable("songListId") Integer songListId) {
        log.info("listSong/detail/songListId->" + songListId);
        return listSongService.listSongOfSongId(songListId);
    }
}
