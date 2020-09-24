package cn.huanhu.controller;

import cn.huanhu.entity.Song;
import cn.huanhu.service.SongService;
import cn.huanhu.utils.Constant;
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
import java.util.Date;


/**
 * @author m
 * @className SongController
 * @description 歌曲API
 * @date 2020/9/8
 */
@Controller
@RequestMapping("song/")
public class SongController {

    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     *
     * @param request request
     * @param upFile  上传的文件
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file") MultipartFile upFile) {
        JSONObject json = new JSONObject();
        // 歌手id
        String singerId = request.getParameter("singerId").trim();
        // 歌名
        String name = request.getParameter("name").trim();
        // 专辑
        String introduction = request.getParameter("introduction").trim();
        String pic = "img/t.jpg";
        // 歌词
        String lyric = request.getParameter("lyric").trim();

        if (upFile.isEmpty()) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "歌曲上传失败");
            return json;
        }
        // 文件名= 当前时间（精确到毫秒）+原来的文件名 避免上传的文件（图片）重复 如果上传了两个文件 将原来的文件覆盖
        String fileName = System.currentTimeMillis() + upFile.getOriginalFilename();
        // 获取文件路径
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        String filePath = "E:\\music\\img\\songAd\\" + singerId +"\\";
        // 如果文件路径不存在，添加文件路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        // 实际的文件地址
//        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        File dest = new File(filePath + fileName);
        // 存储到数据库中的相对文件地址
        String storeUrlPath = "/songAd/" + singerId +"/"+ fileName;
//        log.info("song/add->id=" + singerId + "\n" + "filename->" + fileName + "\n" + "filePath->" + filePath + "\n" + "dest->"
//                + dest + "\n" + "storeUrlPath->" + storeUrlPath + "\n" + "storeUrlPath : " + storeUrlPath);
        try {
            upFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());

            log.info("song/add->id=" + singerId + "\n" + "filename->" + fileName + "\n" + "filePath->" + filePath + "\n" + "dest->"
                    + dest + "\n" + "storeUrlPath->" + storeUrlPath + "\n" + "storeUrlPath : " + storeUrlPath);
            // 添加
            boolean result = songService.insert(song);
//            boolean result = false;
            if (result) {
                json.put(Constant.CODE, 1);
                json.put(Constant.MSG, "保存成功");
                // 返回的路径不一定使用到
                json.put("avator", storeUrlPath);
            } else {
                json.put(Constant.CODE, 0);
                json.put(Constant.MSG, "保存失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "保存失败" + e.getMessage());
        }
        return json;
    }

    /**
     * 返回所有歌曲
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public Object allSong() {
        return songService.allSong();
    }

    /**
     * 返回指歌曲id的歌曲
     * 返回指定用户id收藏列表 --
     *
     * @param request req
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        log.info("song/detail?id->" + id);
        return songService.songOfId(Integer.parseInt(id));
    }

    /**
     * 根据歌手id返回歌手的歌曲
     *
     * @param singerId singerId
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "singer/detail", method = RequestMethod.GET)
    public Object songOfSingerId(@RequestParam(value = "singerId") Integer singerId) {
        log.info("song/singer/detail?singerId---->" + singerId);
        return songService.songOfSingerId(singerId);
    }

    /**
     * 返回指定歌手名的歌曲
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "singerName/detail", method = RequestMethod.GET)
    public Object songOfSingerName(HttpServletRequest request) {
        String name = request.getParameter("name");
        log.info("song/singerNAme/detail->name"+name);
        return songService.songListOfName('%' + name + '%');
    }

    /**
     * 返回指定歌曲名的歌曲
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "name/detail", method = RequestMethod.GET)
    public Object songOfName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        Song name1 = new Song();
        name1.setName(name);
        return songService.songOfSingerName(name1);
    }

    /**
     * 删除歌曲 v-1
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Object deleteSong(HttpServletRequest request) {
        try {
//            String id = request.getParameter("id").trim();
            String singerId = request.getParameter("singerId");
            String name = request.getParameter("singerName").trim();
            log.info("song/delete?singerId->" + singerId + "\t" + "name->" + name);
        } catch (Exception e) {
            log.info("song/delete->" + e.getMessage());
            return null;
        }
//        return songService.deleteById(Integer.parseInt(id),Integer.parseInt(singerId));
        return null;
    }

    /**
     * 删除歌曲 v-2
     *
     * @param singerId singerId
     * @param id       songId
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "delete/{singerId}/{idx}", method = RequestMethod.POST)
    public Object deleteSong(@PathVariable("singerId") Integer singerId, @PathVariable("idx") Integer id) {
        log.info("v2-song/delete?singerId->" + singerId + "\t" + "id->" + id);
        // 查询旧歌曲地址
        String fileAddr = "E:/music/img";
        String oldPic = songService.queryOldUrl(id);
        File oldFile = new File(fileAddr + oldPic);
        //删除旧歌曲文件
        if (oldFile.exists()) {
            oldFile.delete();
        }
        return songService.deleteById(id, singerId);
    }

    /**
     * 更新歌曲信息
     *
     * @param request request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request) {
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
        log.info("song/update/->" + song.toString());
        boolean result = songService.update(song);
        if (result) {
            json.put(Constant.CODE, 1);
            json.put(Constant.MSG, "修改成功");
        } else {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "修改失败");
        }
        return json;
    }

    /**
     * 更新歌曲图片
     *
     * @param urlFile 歌曲图片路径
     * @param id      song表主键
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") Integer id,
                                @RequestParam("singerId") Integer singerId)
    {
        JSONObject json = new JSONObject();
        if (urlFile.isEmpty()) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败");
            return json;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        // 文件路径
        String filePath = "E:\\music\\img\\songPic\\" + singerId + "\\";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 目标地址
        File dest = new File(filePath + fileName);
        String storeUrlPath = "/songPic/" + singerId + "/" + fileName;

        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);

            // 查询旧图片地址
            String fileAddr = "E:/music/img/";
            String oldPic = songService.queryOldPic(id);
            log.info("song/img/update->" + "\n" + "filename->" + fileName + "\n" + "filePath->" + filePath + "\n" + "dest->"
                    + dest + "\n" + "storeUrlPath->" + storeUrlPath + "\n" + "oldPic->" + oldPic + "\t" + fileAddr + oldPic);
            File oldFile = new File(fileAddr + oldPic);
            //删除旧图片
            if (oldFile.exists()) {
                oldFile.delete();
            }

            boolean result = songService.updateSongPic(song);
            if (result) {
                json.put(Constant.CODE, 1);
                json.put(Constant.MSG, "上传成功");
                json.put("pic", storeUrlPath);
            } else {
                json.put(Constant.CODE, 0);
                json.put(Constant.MSG, "上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败" + e.getMessage());
            return json;
        }
    }

    /**
     * v-1
     * 更新歌曲url
     *
     * @param urlFile 歌曲url
     * @param id      主键
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") Integer id,
                                @RequestParam("singerId") Integer singerId)
    {
        JSONObject json = new JSONObject();
        if (urlFile.isEmpty()) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败");
            return json;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song" +
//                System.getProperty("file.separator") + "songPic";
        // 文件路径
        String filePath = "E:\\music\\img\\songAd\\" + singerId + "\\";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 目标地址
        File dest = new File(filePath + fileName);
        String storeUrlPath = "/songAd/" + singerId + "/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean result = songService.update(song);
            if (result) {
                json.put(Constant.CODE, 1);
                json.put(Constant.MSG, "上传成功");
                json.put("url", storeUrlPath);
            } else {
                json.put(Constant.CODE, 0);
                json.put(Constant.MSG, "上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败" + e.getMessage());
            return json;
        }
    }

    /**
     * v-2
     * 更新歌曲url
     * @param urlFile 歌曲url
     * @param id 主键
     * @param singerId singerId
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "url/update/{id}/{singerId}", method = RequestMethod.POST)
    public Object updateSongAd(@PathVariable("id") Integer id , @PathVariable("singerId") Integer singerId,
                               @RequestParam("file") MultipartFile urlFile)
    {
        JSONObject json = new JSONObject();
        if (urlFile.isEmpty()) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败");
            return json;
        }
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song" +
//                System.getProperty("file.separator") + "songPic";
        // 文件路径
        String filePath = "E:\\music\\img\\songAd\\" + singerId + "\\";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        // 目标地址
        File dest = new File(filePath + fileName);
        String storeUrlPath = "/songAd/" + singerId + "/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);

            // 查询旧歌曲地址
            String fileAddr = "E:/music/img";
            String oldPic = songService.queryOldUrl(id);
            log.info("song/url/update->" + "\n" + "filename->" + fileName + "\n" + "filePath->" + filePath + "\n" + "dest->"
                    + dest + "\n" + "storeUrlPath->" + storeUrlPath + "\n" + "oldPic->" + oldPic + "\t" + fileAddr + oldPic);
            File oldFile = new File(fileAddr + oldPic);
            //删除旧歌曲文件
            if (oldFile.exists()) {
                oldFile.delete();
            }

            boolean result = songService.update(song);
            if (result) {
                json.put(Constant.CODE, 1);
                json.put(Constant.MSG, "上传成功");
                json.put("url", storeUrlPath);
            } else {
                json.put(Constant.CODE, 0);
                json.put(Constant.MSG, "上传失败");
            }
            return json;
        } catch (IOException e) {
            json.put(Constant.CODE, 0);
            json.put(Constant.MSG, "上传失败" + e.getMessage());
            return json;
        }
    }
}
