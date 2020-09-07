package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 评价（对歌单的评价）(Rank)实体类
 *
 * @author m
 * @since 2020-09-03 15:40:37
 */

@Data
@Entity
@Table(name = "rank", schema = "music")
public class Rank implements Serializable {
    private static final long serialVersionUID = -52164887248755498L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 歌单id
     */
    @Column(name = "song_list_id")
    private Long songListId;

    /**
     * 用户id
     */
    @Column(name = "consumer_id")
    private Long consumerId;

    /**
     * 评分
     */
    @Column(name = "score")
    private Long score;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSongListId() {
        return songListId;
    }

    public void setSongListId(Long songListId) {
        this.songListId = songListId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}