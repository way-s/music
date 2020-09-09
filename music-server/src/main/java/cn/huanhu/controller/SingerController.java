package cn.huanhu.controller;

import cn.huanhu.entity.Singer;
import cn.huanhu.service.SingerService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author m
 * @className SingerController
 * @description SingerController
 * @date 2020/9/6
 */
@RestController
@RequestMapping("singer/")
public class SingerController {

    private static final Logger log = LoggerFactory.getLogger(SingerController.class);
    @Autowired
    private SingerService singerService;

    /**
     *  添加歌手
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        log.info(singer.toString());
        //保存
        boolean result = singerService.insert(singer);
        //成功
        if (result){
            json.put(Constant.CODE,1);
            json.put(Constant.MSG,"添加成功");
        }
        //失败
        else {
           json.put(Constant.CODE,0);
           json.put(Constant.MSG,"添加失败");
        }
        return json;
//        return null;
    }

    /**
     *  更新歌手信息
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        //保存
        boolean result = singerService.update(singer);
        //成功
        if (result){
            json.put(Constant.CODE,1);
            json.put(Constant.MSG,"修改成功");
        }
        //失败
        else {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"修改失败");
        }
        return json;
    }

    /**
     * 删除歌手
     * @param request request
     * @param id id
     * @return object
     */
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public Object deleteSinger(HttpServletRequest request, @RequestParam("id") Integer id){
//        String id = request.getParameter("id").trim();
        return singerService.deleteById(id);
    }

    /**
     *  根据主键查询整个对象
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "q/key",method = RequestMethod.POST)
    public Object queryByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return singerService.queryByPrimaryKey(Integer.parseInt(id));
    }

    /**
     *  查询所有歌手
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "q/all",method = RequestMethod.POST)
    public Object allSinger(HttpServletRequest request){
        return singerService.allSinger();
    }

    /**
     *  根据歌手名字模糊查询
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "q/name",method = RequestMethod.POST)
    public Object singerListOfName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return singerService.singerListOfName("%"+name+"%");
    }

    /**
     *  根据性别查询
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "q/sex",method = RequestMethod.POST)
    public Object queryBySex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();
        return singerService.queryBySex(Integer.parseInt(sex));
    }

    /**
     *  更新歌手图片
     * @param upPicFile 上传的歌手图片
     * @param id 歌手id
     * @return json
     */
    @RequestMapping(value = "avatar/update/",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile upPicFile,@RequestParam("id") Integer id){
        log.info("go in");
        log.info("id："+id+"\t"+"urlFile："+upPicFile);
        JSONObject json = new JSONObject();
        if(upPicFile.isEmpty()){
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"文件上传失败");
            return json;
        }
        // 文件名= 当前时间（精确到毫秒）+原来的文件名 避免上传的文件（图片）重复 如果上传了两个文件 将原来的文件覆盖
        String fileName = System.currentTimeMillis()+upPicFile.getOriginalFilename();
        // 获取文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") +
                "img" + System.getProperty("file.separator") + "singerPic" ;
        // 如果文件路径不存在，添加文件路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        // 存储到数据库中的相对文件地址
        String storeRelativelyPath = "/img/singerPic/" + fileName;
        try {
            upPicFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeRelativelyPath);
            log.info("storeRelativelyPath : "+storeRelativelyPath);
            // 更新
            boolean result = singerService.update(singer);
            if(result){
                json.put(Constant.CODE,1);
                json.put(Constant.MSG,"上传成功");
                json.put("pic",storeRelativelyPath);
            }else {
                json.put(Constant.CODE,0);
                json.put(Constant.MSG,"上传失败");
            }
            return json;

        } catch (IOException e) {
            json.put(Constant.CODE,0);
            json.put(Constant.MSG,"上传失败"+ e.getMessage());
        }
        return json;
    }
}
