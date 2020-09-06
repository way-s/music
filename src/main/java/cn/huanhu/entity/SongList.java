package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 歌单(SongList)实体类
 *
 * @author m
 * @since 2020-09-03 15:42:44
 */

@Data
@Entity
@Table(name = "song_list", schema = "music")
public class SongList implements Serializable {
    private static final long serialVersionUID = -68608621606031103L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 歌单图片
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 简介
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 风格
     */
    @Column(name = "style")
    private String style;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}