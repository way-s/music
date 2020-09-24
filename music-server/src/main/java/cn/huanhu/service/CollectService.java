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
     * @param userId 用户id
     * @return 对象列表
     */
    public List<Collect> collectionOfUser(Integer userId) {
        return this.collectDao.queryByUserId(userId);
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

    /**
     * 根据用户ID和歌曲id删除收藏列表
     *
     * @param userId 用户id
     * @param songId 歌曲id
     * @return 是否成功
     */
    public boolean deleteCollect(Integer userId,Integer songId) {
        return this.collectDao.deleteCollect(userId,songId) > 0;
    }

    /**
     * 返回所有用户收藏列表
     * @return json
     */
    public List<Collect> queryAllCollect(){
        return this.collectDao.queryAllCollect();
    }
}