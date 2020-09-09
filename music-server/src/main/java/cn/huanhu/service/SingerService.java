package cn.huanhu.service;

import cn.huanhu.dao.SingerDao;
import cn.huanhu.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author m
 * @className SingerService
 * @description SingerService
 * @date 2020/9/6
 */
@Service
public class SingerService {

    private static final Logger log = LoggerFactory.getLogger(SingerService.class);

    @Autowired
    private SingerDao singerDao;

    /**
     * 增加
     * @param singer singer
     * @return true or false
     */
    public boolean insert(Singer singer){
        log.info(singer.toString());
        return singerDao.insert(singer)>0;
    }

    /**
     * 修改
     * @param singer singer
     * @return true or false
     */
    public boolean update(Singer singer){
        return singerDao.update(singer)>0;
    }

    /**
     * 删除
     * @param id primaryKey id
     * @return true or false
     */
    public boolean deleteById(Integer id){
        return singerDao.deleteById(id)>0;
    }

    /**
     * 根据主键查询整个对象
     * @param id id
     * @return singer
     */
    public Singer queryByPrimaryKey(Integer id){
        return singerDao.queryByPrimaryKey(id);
    }

    /**
     * 查询所有歌手
     * @return list
     */
    public List<Singer> allSinger(){
        return singerDao.allSinger();
    }

    /**
     * 根据歌手名字模糊查询列表
     * @param name name
     * @return list singer
     */
    public List<Singer> singerListOfName(String name){
        return singerDao.singerListOfName(name);
    }

    /**
     * 根据性别查询
     * @param sex sex
     * @return list singer
     */
    public List<Singer> queryBySex(Integer sex){
        return singerDao.queryBySex(sex);
    }
}
