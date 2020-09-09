package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 前端用户表(Consumer)实体类
 *
 * @author m
 * @since 2020-09-03 15:37:50
 */

@Data
@Entity
@Table(name = "consumer", schema = "music")
public class Consumer implements Serializable {
    private static final long serialVersionUID = 297652205870561678L;

    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 性别（1男0女）
     */
    @Column(name = "sex")
    private Boolean sex;

    /**
     * 电话
     */
    @Column(name = "phone_num")
    private String phoneNum;

    /**
     * 电子邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 生日
     */
    @Column(name = "birth")
    private Date birth;

    /**
     * 签名
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 地区
     */
    @Column(name = "location")
    private String location;

    /**
     * 头像
     */
    @Column(name = "avator")
    private String avator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
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

}