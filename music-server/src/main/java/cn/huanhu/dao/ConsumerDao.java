package cn.huanhu.dao;

import cn.huanhu.entity.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className ConsumerDao
 * @description 前端用户表(Consumer)表数据库访问层
 * @date 2020/09/09
 */
@Mapper
public interface ConsumerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Consumer queryById(Integer id);

    /**
     * 根据名字查询
     *
     *
     * @param username@return 对象列表
     */
    List<Consumer> queryByName(@Param("username")String username );


    /**
     * 通过实体作为筛选条件查询
     *
     * @param consumer 实例对象
     * @return 对象列表
     */
    List<Consumer> queryAll(Consumer consumer);

    /**
     * 新增数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int insert(Consumer consumer);

    /**
     * 修改数据
     *
     * @param consumer 实例对象
     * @return 影响行数
     */
    int update(Consumer consumer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询所有用户
     * @return consumer
     */
    List<Consumer> allUser();

    /**
     *登录验证
     * @param username username
     * @param password password
     * @return 1 0
     */
    int verifyPassword(@Param("username")String username ,@Param("password")String password);

    /**
     * 查询旧图片地址
     * @param id id
     * @return string
     */
    public String queryOldPic(Integer id);

    /**
     * 手机密码验证
     * @param phoneNum 手机号
     * @param password 密码
     * @return consumer 实体
     */
    public List<Consumer> verityPhoneNum(@Param("phoneNum") Integer phoneNum,@Param("password") Integer password);
}