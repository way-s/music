package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 歌曲(Song)实体类
 *
 * @author m
 * @since 2020-09-03 15:42:10
 */

@Data
@Entity
@Table(name = "song", schema = "music")
public class Song implements Serializable {
    private static final long serialVersionUID = -45049763295276656L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 歌手id
     */
    @Column(name = "singer_id")
    private Long singerId;

    /**
     * 歌名
     */
    @Column(name = "name")
    private String name;

    /**
     * 简介
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 发行时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 歌曲图片
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 歌词
     */
    @Column(name = "lyric")
    private String lyric;

    /**
     * 歌曲地址
     */
    @Column(name = "url")
    private String url;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}