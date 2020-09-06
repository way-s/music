package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 歌手(Singer)实体类
 *
 * @author m
 * @since 2020-09-03 15:41:39
 */

@Data
@Entity
@Table(name = "singer", schema = "music")
public class Singer implements Serializable {
    private static final long serialVersionUID = -89962339624556973L;

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 歌手姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 歌手性别（1男0女）
     */
    @Column(name = "sex")
    private Byte sex;

    /**
     * 歌手图片
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 生日
     */
    @Column(name = "birth")
    private Date birth;

    /**
     * 所属地区
     */
    @Column(name = "location")
    private String location;

    /**
     * 简介
     */
    @Column(name = "introduction")
    private String introduction;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}