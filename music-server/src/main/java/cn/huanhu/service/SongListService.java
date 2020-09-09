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
    public SongList insert(SongList songList) {
        this.songListDao.insert(songList);
        return songList;
    }

    /**
     * 修改数据
     *
     * @param songList 实例对象
     * @return 实例对象
     */
    public SongList update(SongList songList) {
        this.songListDao.update(songList);
        return this.queryById(songList.getId());
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
}