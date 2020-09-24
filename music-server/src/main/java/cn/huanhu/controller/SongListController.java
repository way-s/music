package cn.huanhu.controller;

import cn.huanhu.entity.SongList;
import cn.huanhu.service.SongListService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author m
 * @className SongListController
 * @description 歌单API
 * @date 2020/9/11
 */
@Controller
@RequestMapping("songList/")
public class SongListController {

    private static final Logger log = LoggerFactory.getLogger(SongListController.class);

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping("add")
    public Object addSongList(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        log.info("songList/add->"+songList.toString());
        // 插入数据
        boolean result = songListService.insert(songList);
        if (result) {
            json.put("code", 1);
            json.put("msg", "添加成功");
        } else {
            json.put("code", 0);
            json.put("msg", "添加失败");
        }
        return json;
    }

    /**
     * 返回所有歌单
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object allSongList() {
        return songListService.allSongList();
    }

    /**
     * 更新歌单信息
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object updateSongListMsg(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        log.info(songList.toString());
        boolean res = songListService.update(songList);
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
     * 删除歌单
     *
     * @param id 主键
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public Object deleteSongList(@PathVariable("id") Integer id) {
        // 查询旧歌曲地址
        String fileAddr = "E:/music/img";
        String oldPic = songListService.queryOldSongListPic(id);
        File oldFile = new File(fileAddr + oldPic);
        //删除旧歌曲文件
        if (oldFile.exists()) {
            oldFile.delete();
        }
        return songListService.deleteById(id);
    }

    /**
     * 更新歌单图片
     * @param upFile 上传的图片
     * @param id 主键
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/img/update", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile upFile, @RequestParam("id") Integer id){
        JSONObject json = new JSONObject();

        if (upFile.isEmpty()) {
            json.put("code", 0);
            json.put("msg", "文件上传失败！");
            return json;
        }
        String fileName = System.currentTimeMillis()+ upFile.getOriginalFilename();
        // 文件路径
        String filePath = "E:\\music\\img\\songListPic\\";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + fileName);
        String storeAvatorPath = "/songListPic/"+fileName;
        try {
            upFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);

            // 查询旧图片地址
            String fileAddr = "E:/music/img/";
            String oldPic = songListService.queryOldPic(id);
            File oldFile = new File(fileAddr + oldPic);
            //删除旧图片
            if (oldFile.exists()) {
                oldFile.delete();
            }

            boolean res = songListService.updateSongListImg(songList);
            if (res){
                json.put("code", 1);
                json.put("avator", storeAvatorPath);
                json.put("msg", "上传成功");
            }else {
                json.put("code", 0);
                json.put("msg", "上传失败");
            }
            return json;
        }catch (IOException e){
            json.put("code", 0);
            json.put("msg", "上传失败" + e.getMessage());
            return json;
        }
    }
}
