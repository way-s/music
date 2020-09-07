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

    public boolean checkPassWord(String userName,String passWord){
//        return adminDao.checkPassWord(name,passWord)>0?true:false;
//        log.info("UserName + passWord ="+userName+"\t"+passWord);
        return adminDao.checkPassWord(userName, passWord);
    }


}