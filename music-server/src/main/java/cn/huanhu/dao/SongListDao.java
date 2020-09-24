package cn.huanhu.dao;

import cn.huanhu.entity.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className SongListDao
 * @description 歌单(SongList)表数据库访问层
 * @date 2020/09/09
 */
@Mapper
public interface SongListDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SongList queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SongList> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param songList 实例对象
     * @return 对象列表
     */
    List<SongList> queryAll(SongList songList);

    /**
     * 新增数据
     *
     * @param songList 实例对象
     * @return 影响行数
     */
    int insert(SongList songList);

    /**
     * 修改数据
     *
     * @param songList 实例对象
     * @return 影响行数
     */
    int update(SongList songList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有歌单
     * @return 所有歌曲
     */
    List<SongList> allSongList();

    /**
     * 查询旧歌曲列表图片
     * @param id 主键
     * @return picUrl
     */
    public String queryOldSongListPic(Integer id);

    /**
     * 更新歌单图片
     * @param songList songList
     * @return 1 0
     */
    public int updateSongListImg(SongList songList);

    /**
     * 查询旧图片地址
     * @param id id
     * @return string
     */
    public String queryOldPic(Integer id);
}