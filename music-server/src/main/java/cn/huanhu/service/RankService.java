package cn.huanhu.service;

import cn.huanhu.dao.RankDao;
import cn.huanhu.entity.Rank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Rank> queryAllByLimit(int offset, int limit) {
        return this.rankDao.queryAllByLimit(offset, limit);
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
     * 修改数据
     *
     * @param rank 实例对象
     * @return 实例对象
     */
//    public Rank update(Rank rank) {
//        this.rankDao.update(rank);
//        return this.queryById((Integer) rank.getId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.rankDao.deleteById(id) > 0;
    }
}