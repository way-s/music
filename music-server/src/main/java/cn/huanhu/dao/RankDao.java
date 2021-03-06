package cn.huanhu.dao;

import cn.huanhu.entity.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className RankDao
 * @description 评价（对歌单的评价）(Rank)表数据库访问层
 * @date 2020/09/09
 */
@Mapper
public interface RankDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Rank queryById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param rank 实例对象
     * @return 对象列表
     */
    List<Rank> queryAll(Rank rank);

    /**
     * 新增数据
     *
     * @param rank 实例对象
     * @return 影响行数
     */
    int insert(Rank rank);

    /**
     * 修改数据
     *
     * @param rank 实例对象
     * @return 影响行数
     */
    int update(Rank rank);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 提交评分
     * @param rank rank
     * @return json
     */
     int addRank(Rank rank);

    /**
     * 总分
     * @param songListId 歌单id
     * @return 1 0
     */
    int selectScoreSum(@Param("songListId")Long songListId);

    /**
     * 查总评分人数
     * @param songListId 歌单id
     * @return 1 0
     */
    int selectRankNum(@Param("songListId") Long songListId);
}