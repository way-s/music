package cn.huanhu.dao;

import cn.huanhu.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className SongDao
 * @description 歌曲(Song)表数据库访问层
 * @date 2020/09/08
 */
@Mapper
public interface SongDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Song> songOfId(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param song 实例对象
     * @return 对象列表
     */
    List<Song> queryAllSong(Song song);

    /**
     * 新增歌曲数据
     *
     * @param song 实例对象
     * @return 影响行数
     */
    int insert(Song song);

    /**
     * 修改数据
     *
     * @param song 实例对象
     * @return 影响行数
     */
    int update(Song song);

    /**
     * 通过主键删除数据
     * 根据主键删除歌曲
     *
     * @param id 主键
     * @param singerId singerId
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id, @Param("singerId") Integer singerId);

    /**
     * 根据id 更新歌曲图片
     * @param song song
     * @return 1 0
     */
    public int updateSongPic(Song song);

    /**
     * 查询所有歌曲
     * @return list
     */
    public List<Song> allSong();


    /**
     * 根据歌曲名字模糊查询列表
     * @param name name
     * @return list singer
     */
    public List<Song> songListOfName(String name);

    /**
     * 根据歌手id查询
     * @param singerId 歌手id
     * @return list singer
     */
    public List<Song> queryBySingerId(Integer singerId);

    /**
     * 查询旧图片地址
     * @param id id
     * @return string
     */
    public String queryOldPic(Integer id);

    /**
     * 查询旧歌曲地址
     * @param id id
     * @return string
     */
    public String queryOldUrl(Integer id);
}