package cn.huanhu.dao;

import cn.huanhu.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className CollectDao
 * @description 收藏(Collect)表数据库访问层
 * @date 2020/09/09
 */
@Mapper
public interface CollectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collect queryById(Integer id);

    /**
     * 返回的指定用户ID收藏列表
     *
     * @param userId 用户id
     * @return 对象列表
     */
    List<Collect> queryByUserId(@Param("userId") Integer userId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param collect 实例对象
     * @return 对象列表
     */
    List<Collect> queryAll(Collect collect);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int insert(Collect collect);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int update(Collect collect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据用户id 和 歌曲id 删除歌单
     * @param userId 用户id
     * @param songId 歌曲id
     * @return 1 0
     */
    int deleteCollect(@Param("userId")Integer userId,@Param("songId")Integer songId);

    /**
     * 返回所有的实体作
     *
     * @return 对象列表
     */
    List<Collect> queryAllCollect();

    /**
     * 歌曲是否已经收藏
     * @param userId 用户id
     * @param songId 歌曲id
     * @return 1 0
     */
    int existSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);

    /**
     * 添加收藏
     * @param collect collect
     * @return 1 0
     */
    int addCollection(Collect collect);
}