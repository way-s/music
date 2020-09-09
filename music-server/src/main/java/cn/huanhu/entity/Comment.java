package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论(Comment)实体类
 *
 * @author m
 * @since 2020-09-03 15:29:37
 */

@Data
@Entity
@Table(name = "comment", schema = "music")
public class Comment implements Serializable {
    private static final long serialVersionUID = 209880959655573489L;

    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 歌曲id
     */
    @Column(name = "song_id")
    private Long songId;

    /**
     * 歌曲列表id
     */
    @Column(name = "song_list_id")
    private Long songListId;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 类型
     */
    @Column(name = "type")
    private Boolean type;

    /**
     * 评论点赞数
     */
    @Column(name = "up")
    private Long up;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getSongListId() {
        return songListId;
    }

    public void setSongListId(Long songListId) {
        this.songListId = songListId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getUp() {
        return up;
    }

    public void setUp(Long up) {
        this.up = up;
    }

}