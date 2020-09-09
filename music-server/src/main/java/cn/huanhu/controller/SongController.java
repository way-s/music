package cn.huanhu.controller;

import cn.huanhu.entity.Song;
import cn.huanhu.service.SongService;
import cn.huanhu.utils.Constant;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * @author m
 * @className SongController
 * @description SongController
 * @date 2020/9/8
 */
@RestController
@RequestMapping("song/")
public class SongController {

    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param request request
     * @param upFile 上传的文件
     * @return json
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile upFile){
        JSONObject json = new JSONObject();
        // 歌手id 歌名
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = request.getParameter("pic").trim();
        String lyric = request.getParameter("lyric").trim();
        String url = request.getParameter("url").trim();

        if(upFile.isEmpty()){
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"歌曲上传失败");
            return json;
        }
        // 文件名= 当前时间（精确到毫秒）+原来的文件名 避免上传的文件（图片）重复 如果上传了两个文件 将原来的文件覆盖
        String fileName = System.currentTimeMillis()+upFile.getOriginalFilename();
        // 获取文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        // 如果文件路径不存在，添加文件路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        // 存储到数据库中的相对文件地址
        String storeUrlPath = "/img/song/" + fileName;
        try {
            upFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            log.info("storeUrlPath : "+storeUrlPath);
            // 更新
            boolean result = songService.insert(song);
            if(result){
                json.put(Constant.CODE,1);
                json.put(Constant.MSG,"保存成功");
                // 返回的路径不一定使用到
                json.put("avator",storeUrlPath);
            }else {
                json.put(Constant.CODE,0);
                json.put(Constant.MSG,"保存失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"保存失败"+ e.getMessage());
        }
        return json;
    }

    /**
     * 返回所有歌曲
     * @return json
     */
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public Object allSong(){
        return songService.allSong();
    }

    /**
     *  返回指歌曲id的歌曲
     * @param request req
     * @return json
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return songService.songOfId(Integer.parseInt(id));
    }

    /**
     *  返回指定歌手ID的歌曲
     * @param request request
     * @return json
     */
    @RequestMapping(value = "singer/detail", method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest request){
        String singerId = request.getParameter("singerId");
        return songService.songOfSingerId(Integer.parseInt(singerId));
    }

    /**
     * 返回指定歌手名的歌曲
     * @param request request
     * @return json
     */
    @RequestMapping(value = "singerName/detail", method = RequestMethod.GET)
    public Object songOfSingerName(HttpServletRequest request){
        String name = request.getParameter("name");
        return songService.songListOfName('%'+ name + '%');
    }

    /**
     * 返回指定歌曲名的歌曲
     * @param request request
     * @return json
     */
    @RequestMapping(value = "name/detail", method = RequestMethod.GET)
    public Object songOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        Song name1 = new Song();
        name1.setName(name);
        return songService.songOfSingerName(name1);
    }

    /**
     * 删除歌曲
     * @param request request
     * @return json
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request){
        String id = request.getParameter("id");
        return songService.deleteById(Integer.parseInt(id));
    }

    /**
     * 更新歌曲信息
     * @param request request
     * @return json
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singerId));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());
        song.setLyric(lyric);
        boolean result = songService.update(song);
        if(result){
            json.put(Constant.CODE,1);
            json.put(Constant.MSG,"修改成功");
        }else {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"修改失败");
        }
        return json;
    }

    /**
     * 更新歌曲图片
     * @param urlFile 歌曲图片路径
     * @param id 主键
     * @return json
     */
    @RequestMapping(value = "img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")Integer id){
        JSONObject json = new JSONObject();

        if (urlFile.isEmpty()){
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"上传失败");
            return json;
        }
        String fileName = System.currentTimeMillis()+urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" +
                System.getProperty("file.separator") + "songPic";
        File file = new File(filePath);
        if (!file.exists()){
            // 创建此抽象路径名指定的目录
            file.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/"+fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean result = songService.update(song);
            if (result){
                json.put(Constant.CODE,1);
                json.put(Constant.MSG,"上传成功");
                json.put("pic",storeUrlPath);
            }else {
                json.put(Constant.CODE,0);
                json.put(Constant.MSG,"上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"上传失败"+ e.getMessage());
            return json;
        }
    }

    /**
     * 更新歌曲url
     * @param urlFile 歌曲url
     * @param id 主键
     * @return json
     */
    @RequestMapping(value = "url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")Integer id){
        JSONObject json = new JSONObject();
        if (urlFile.isEmpty()){
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"上传失败");
            return json;
        }
        String fileName = System.currentTimeMillis()+urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song" +
                System.getProperty("file.separator") + "songPic";
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/"+fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean result = songService.update(song);
            if (result){
                json.put(Constant.CODE,1);
                json.put(Constant.MSG,"上传成功");
                json.put("url",storeUrlPath);
            }else {
                json.put(Constant.CODE,0);
                json.put(Constant.MSG,"上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"上传失败"+ e.getMessage());
            return json;
        }
    }
}
