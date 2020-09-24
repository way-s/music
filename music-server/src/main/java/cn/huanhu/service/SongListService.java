package cn.huanhu.service;

import cn.huanhu.dao.SongListDao;
import cn.huanhu.entity.SongList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author m
 * @className SongListServiceImpl
 * @description 歌单(SongList)表服务实现类
 * @date 2020/09/09
 */
@Service("songListService")
public class SongListService {
    @Resource
    private SongListDao songListDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SongList queryById(Integer id) {
        return this.songListDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<SongList> queryAllByLimit(int offset, int limit) {
        return this.songListDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param songList 实例对象
     * @return 实例对象
     */
    public boolean insert(SongList songList) {
        return this.songListDao.insert(songList)>0;
    }

    /**
     * 修改数据
     *
     * @param songList 实例对象
     * @return 实例对象
     */
    public Boolean update(SongList songList) {
        return this.songListDao.update(songList)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.songListDao.deleteById(id) > 0;
    }

    /**
     * 查询所有歌单
     * @return 所有歌单
     */
    public List<SongList> allSongList() {
        return this.songListDao.allSongList();
    }

    /**
     * 查询旧歌曲列表图片
     * @param id 主键
     * @return picUrl
     */
    public String queryOldSongListPic(Integer id){
        return this.songListDao.queryOldSongListPic(id);
    }

    /**
     * 更新歌单图片
     * @param songList songList
     * @return 1 0
     */
    public boolean updateSongListImg(SongList songList) {
        return this.songListDao.updateSongListImg(songList) > 0;
    }

    /**
     * 查询旧图片地址
     * @param id id
     * @return string
     */
    public String queryOldPic(Integer id){
        return this.songListDao.queryOldPic(id);
    }
}