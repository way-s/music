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
     * @param username username
     * @return 对象列表
     */
    public List<Consumer> queryByName(String username) {
        return this.consumerDao.queryByName(username);
    }

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    public boolean insert(Consumer consumer) {
        return this.consumerDao.insert(consumer)>0;
    }

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 实例对象
     */
    public Boolean update(Consumer consumer) {
        return this.consumerDao.update(consumer)>0;
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

    /**
     * 查询所有用户
     * @return consumer
     */
    public List<Consumer> allUser(){
        return consumerDao.allUser();
    }

    /**
     * 登录验证
     * @param username username
     * @param password password
     * @return 1 0
     */
    public boolean verityPassword(String username,String password){
        return consumerDao.verifyPassword(username,password)>0;
    }

    /**
     * 查询旧图片地址
     * @param id id
     * @return string
     */
    public String queryOldPic(Integer id){
        return consumerDao.queryOldPic(id);
    }
}