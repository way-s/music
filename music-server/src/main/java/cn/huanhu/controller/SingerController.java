package cn.huanhu.controller;

import cn.huanhu.entity.Singer;
import cn.huanhu.service.SingerService;
import cn.huanhu.utils.Constant;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    }

    /**
     *  修改歌手
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String id = request.getParameter("id").trim();
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
        singer.setId(Long.parseLong(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
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
     *  删除歌手
     * @param request  request
     * @return object
     */
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public Object deleteSinger(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return singerService.deleteById(Integer.parseInt(id));
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
}
