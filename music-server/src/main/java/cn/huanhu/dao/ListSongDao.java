package cn.huanhu.dao;

import cn.huanhu.entity.ListSong;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author m
 * @className ListSongDao
 * @description 歌单包含的歌曲列表(ListSong)表数据库访问层
 * @date 2020/09/09
 */
@Mapper
public interface ListSongDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ListSong queryById(Integer id);

    /**
     * 查询歌单包含的所有歌曲
     *
     * @return 对象列表
     */
    List<ListSong> queryAllListSong();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param listSong 实例对象
     * @return 对象列表
     */
    List<ListSong> queryAll(ListSong listSong);

    /**
     * 新增数据
     *
     * @param listSong 实例对象
     * @return 影响行数
     */
    int insert(ListSong listSong);

    /**
     * 修改数据
     *
     * @param listSong 实例对象
     * @return 影响行数
     */
    int update(ListSong listSong);

    /**
     * 通过主键删除数据
     *
     * @param songId 歌曲id
     * @return 影响行数
     */
    int deleteById(Integer songId);

    /**
     * 添加列表歌曲
     * @param listSong listSong
     * @return 1 0
     */
    int addListSong(ListSong listSong);

    /**
     * 返回歌单里指定歌单Id的歌曲
     *
     * @param songListId 歌单id的歌曲
     * @return 实例对象
     */
    List<ListSong> queryListSongById(Integer songListId);
}