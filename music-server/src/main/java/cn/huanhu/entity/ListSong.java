package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 歌单包含的歌曲列表(ListSong)实体类
 *
 * @author m
 * @since 2020-09-03 15:39:47
 */

@Data
@Entity
@Table(name = "list_song", schema = "music")
public class ListSong implements Serializable {
    private static final long serialVersionUID = -17314864367509369L;

    @Id
    @Column(name = "id")
    private Long id;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}