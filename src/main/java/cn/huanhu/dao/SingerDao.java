package cn.huanhu.dao;

import cn.huanhu.entity.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SingerDao
 * @Description: SingerDao
 * @author: m
 * @Date: 2020/9/6
 */
@Mapper
public interface SingerDao {

    /**
     * 增加
     * @param singer singer
     * @return int
     */
    public int insert(Singer singer);

    /**
     * 修改
     * @param singer singer
     * @return int
     */
    public int update(Singer singer);

    /**
     * 删除
     * @param id primaryKey id
     * @return int
     */
    public int deleteById(Integer id);

    /**
     * 根据主键查询整个对象
     * @param id id
     * @return singer
     */
    public Singer queryByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     * @return list
     */
    public List<Singer> allSinger();


    /**
     * 根据歌手名字模糊查询列表
     * @param name name
     * @return list singer
     */
    public List<Singer> singerListOfName(String name);

    /**
     * 根据性别查询
     * @param sex sex
     * @return list singer
     */
    public List<Singer> queryBySex(Integer sex);
}
