package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 收藏(Collect)实体类
 *
 * @author m
 * @since 2020-09-03 15:26:25
 */

@Data
@Entity
@Table(name = "collect", schema = "music")
public class Collect implements Serializable {
    private static final long serialVersionUID = -23151247246032091L;

    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 收藏类型（0歌曲1歌单）
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 歌曲id
     */
    @Column(name = "song_id")
    private Long songId;

    /**
     * 歌单id
     */
    @Column(name = "song_list_id")
    private Long songListId;

    /**
     * 收藏时间
     */
    @Column(name = "create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}