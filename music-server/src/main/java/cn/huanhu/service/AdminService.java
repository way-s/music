package cn.huanhu.service;


import cn.huanhu.dao.AdminDao;
import cn.huanhu.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author m
 * @className AdminService
 * @description (Admin)表服务接口
 * @date 2020/09/03
 */
@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private AdminDao adminDao;

    public Admin queryById(Integer id){
        return adminDao.queryById(id);
    }

    /**
     * 密码效验
     * @param userName 昵称
     * @param passWord 密码
     * @return 1 0
     */
    public boolean checkPassWord(String userName,String passWord){
        return adminDao.checkPassWord(userName, passWord);
    }

    /**
     * 查询是否存在
     *
     * @param name 昵称
     * @return 1 0
     */
    public boolean queryByName(String name){
        return adminDao.queryByName(name);
    }

    /**
     * 注册
     *
     * @param name 昵称
     * @param password 密码
     * @return 1 0
     */
    public boolean insert(String name,String password){
        return adminDao.insert(name,password) > 0;
    }

}