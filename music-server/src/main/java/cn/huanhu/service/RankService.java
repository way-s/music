package cn.huanhu.service;

import cn.huanhu.dao.RankDao;
import cn.huanhu.entity.Rank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author m
 * @className RankServiceImpl
 * @description 评价（对歌单的评价）(Rank)表服务实现类
 * @date 2020/09/09
 */
@Service
public class RankService{

    @Resource
    private RankDao rankDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Rank queryById(Integer id) {
        return this.rankDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param rank 实例对象
     * @return 实例对象
     */
    public Rank insert(Rank rank) {
        this.rankDao.insert(rank);
        return rank;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.rankDao.deleteById(id) > 0;
    }

    /**
     * 提交评分
     * @param rank rank
     * @return json
     */
    public boolean addRank(Rank rank) {
        return this.rankDao.addRank(rank) > 0;
    }

    /**
     * 获取指定歌单的评分
     * @param songListId 歌单id
     * @return 1 0
     */
    public int rankOfSongListId(Long songListId) {
        // 分数总和 / 评分人数
        return rankDao.selectScoreSum(songListId) / rankDao.selectRankNum(songListId);
    }
}