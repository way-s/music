package cn.huanhu.service;

import cn.huanhu.dao.ConsumerDao;
import cn.huanhu.entity.Consumer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author m
 * @className ConsumerServiceImpl
 * @description 前端用户表(Consumer)表服务实现类
 * @date 2020/09/09
 */
@Service
public class ConsumerService{
    @Resource
    private ConsumerDao consumerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Consumer queryById(Integer id) {
        return this.consumerDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Consumer> queryAllByLimit(int offset, int limit) {
        return this.consumerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    public Consumer insert(Consumer consumer) {
        this.consumerDao.insert(consumer);
        return consumer;
    }

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    public Consumer update(Consumer consumer) {
        this.consumerDao.update(consumer);
        return this.queryById(consumer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.consumerDao.deleteById(id) > 0;
    }
}