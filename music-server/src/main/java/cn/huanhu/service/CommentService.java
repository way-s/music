package cn.huanhu.service;

import cn.huanhu.dao.CommentDao;
import cn.huanhu.entity.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author m
 * @className CommentServiceImpl
 * @description 评论(Comment)表服务实现类
 * @date 2020/09/09
 */
@Service
public class CommentService {
    @Resource
    private CommentDao commentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Comment queryById(Integer id) {
        return this.commentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    public Comment insert(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 1 0
     */
    public boolean update(Comment comment) {
        return this.commentDao.update(comment) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.commentDao.deleteById(id) > 0;
    }

    /**
     * 获得指定歌单ID的评论列表
     *
     * @param songListId 歌单列表id
     * @return comment实体
     */
    public List<Comment> commentOfSongListId(Integer songListId) {
        return this.commentDao.queryBySongListId(songListId);
    }

    /**
     * 获得指定歌曲ID的评论列表
     *
     * @param songId 歌曲列表id
     * @return comment实体
     */
    public List<Comment> commentOfSongId(Integer songId) {
        return this.commentDao.queryBySongId(songId);
    }

    /**
     * 添加评论
     * @param comment comment
     * @return 1 0
     */
    public boolean addComment(Comment comment) {
        return commentDao.insert(comment) > 0;
    }
}