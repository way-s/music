package cn.huanhu.service;

import cn.huanhu.dao.CollectDao;
import cn.huanhu.entity.Collect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author m
 * @className CollectServiceImpl
 * @description 收藏(Collect)表服务实现类
 * @date 2020/09/09
 */
@Service("collectService")
public class CollectService {
    @Resource
    private CollectDao collectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Collect queryById(Integer id) {
        return this.collectDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Collect> queryAllByLimit(int offset, int limit) {
        return this.collectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    public Collect insert(Collect collect) {
        this.collectDao.insert(collect);
        return collect;
    }

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    public Collect update(Collect collect) {
        this.collectDao.update(collect);
        return this.queryById(collect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.collectDao.deleteById(id) > 0;
    }
}