package cn.huanhu.dao;

import cn.huanhu.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author m
 * @className AdminDao
 * @description (Admin)表数据库访问层
 * @date 2020/09/03
 */
@Mapper
public interface AdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Admin queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param admin 实例对象
     * @return 对象列表
     */
    List<Admin> queryAll(Admin admin);

    /**
     * 新增数据
     *
     * @param name name
     * @param password password
     * @return 影响行数
     */
    int insert(@Param("name")String name , @Param("password")String password);


    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 密码登录查询
     *
     * @param userName name
     * @param passWord password
     * @return true false
     */
    boolean checkPassWord(@Param("userName") String userName,@Param("passWord") String passWord);

    /**
     * 查询是否存在
     *
     * @param name 昵称
     * @return 对象列表
     */
    boolean queryByName(String name);

}