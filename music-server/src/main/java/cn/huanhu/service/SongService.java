package cn.huanhu.service;

import cn.huanhu.dao.SongDao;
import cn.huanhu.entity.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author m
 * @className SongService
 * @description SongService
 * @date 2020/9/8
 */
@Service
public class SongService {

    private static final Logger log = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private SongDao songDao;

    /**
     * 增加
     * @param song song
     * @return true or false
     */
    public boolean insert(Song song){
        log.info(song.toString());
        return songDao.insert(song)>0;
    }

    /**
     * 修改
     * @param song song
     * @return true or false
     */
    public boolean update(Song song){
        return songDao.update(song)>0;
    }

    /**
     * 删除
     * @param id primaryKey id
     * @return true or false
     */
    public boolean deleteById(Integer id){
        return songDao.deleteById(id)>0;
    }

    /**
     * 根据主键查询整个对象
     * @param id id
     * @return song
     */
    public Song queryByPrimaryKey(Integer id){
        return songDao.queryByPrimaryKey(id);
    }

    /**
     * 查询所有歌曲
     * @return list
     */
    public List<Song> allSong(){
        return songDao.allSong();
    }

    /**
     * 根据歌曲名字模糊查询列表
     * @param name name
     * @return list song
     */
    public List<Song> songListOfName(String name){
        return songDao.songListOfName(name);
    }

    /**
     * 根据歌手id查询
     * @param singerId singerId
     * @return list song
     */
    public List<Song> songOfSingerId(Integer singerId){
        return songDao.queryBySingerId(singerId);
    }

    /**
     * 根据歌曲id查询
     * @param id songId
     * @return list song
     */
    public List<Song> songOfId(Integer id){
        return songDao.songOfId(id);
    }

    /**
     * 根据歌手名字查询
     * @param song Name
     * @return list song
     */
    public List<Song> songOfSingerName(Song song){
        return songDao.queryAllSong(song);
    }
}
