package cn.huanhu.dao;

import cn.huanhu.entity.Song;
import org.apache.ibatis.annotations.Mapper;

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
//    List<Song> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


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
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据主键查询整个对象
     * @param id id
     * @return singer
     */
    public Song queryByPrimaryKey(Integer id);

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

}