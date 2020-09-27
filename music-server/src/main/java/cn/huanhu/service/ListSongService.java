package cn.huanhu.service;

import cn.huanhu.dao.ListSongDao;
import cn.huanhu.entity.ListSong;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author m
 * @className ListSongServiceImpl
 * @description 歌单包含的歌曲列表(ListSong)表服务实现类
 * @date 2020/09/09
 */
@Service("listSongService")
public class ListSongService {
    @Resource
    private ListSongDao listSongDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ListSong queryById(Integer id) {
        return this.listSongDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    public List<ListSong> allListSong() {
        return this.listSongDao.queryAllListSong();
    }

    /**
     * 新增数据
     *
     * @param listSong 实例对象
     * @return 实例对象
     */
    public ListSong insert(ListSong listSong) {
        this.listSongDao.insert(listSong);
        return listSong;
    }

    /**
     * 修改数据
     *
     * @param listSong 实例对象
     * @return 实例对象
     */
    public ListSong update(ListSong listSong) {
        this.listSongDao.update(listSong);
        return this.queryById(listSong.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param songId 歌曲Id
     * @return 是否成功
     */
    public boolean deleteById(Integer songId) {
        return this.listSongDao.deleteById(songId) > 0;
    }

    /**
     *  添加列表歌曲
     * @param listSong listSong
     * @return 1 0
     */
    public boolean addListSong(ListSong listSong){
        return this.listSongDao.addListSong(listSong)>0;
    }

    /**
     * 返回歌单里指定歌单Id的歌曲
     * @param songListId songListId
     * @return ListSong实体对象
     */
    public List<ListSong> queryListSongById(Integer songListId){
        return listSongDao.queryListSongById(songListId);
    }

    /**
     * 返回歌单里指定歌单ID的歌曲
     * @param songListId 歌单id
     * @return listSong 实体
     */
    public List<ListSong> listSongOfSongId(Integer songListId)
    {
        return listSongDao.listSongOfSongId(songListId);
    }
}