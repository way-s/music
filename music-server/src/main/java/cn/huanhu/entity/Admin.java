package cn.huanhu.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author m
 * @since 2020-09-03 11:18:12
 */

@Data
@Entity
@Table(name = "admin", schema = "music")
public class Admin implements Serializable {

    private static final long serialVersionUID = -80807735258850286L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 账号
     */
    @Column(name = "name")
    private String name;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}