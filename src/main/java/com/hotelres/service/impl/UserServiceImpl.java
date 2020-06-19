package com.hotelres.service.impl;

import com.hotelres.bean.User;
import com.hotelres.dao.IUserDao;
import com.hotelres.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author scqzy
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao iUserDao;

    @Override
    public List<User> findAll(String username) {
        return iUserDao.findAll(username);
    }

    @Override
    public void saveUser(User user) {
        iUserDao.saveUser(user);
    }

    @Override
    public User login(String username,String password) {
        return iUserDao.login(username,password);
    }

    @Override
    public User findByIdcard(String idcard) {

        return this.iUserDao.findByIdcard(idcard);

    }

    @Override
    public User findByUsername(String username) {
        return this.iUserDao.findByUsername(username);
    }

    @Override
    public User findByIdcardAndRealname(String idcard, String realname) {
        return this.iUserDao.findByIdcardAndRealname(idcard,realname);
    }

    @Override
    public void updatePassword(String username, String password) {
        this.iUserDao.updatePassword(username,password);
    }

    @Override
    public void updateInfo(double jiudianweizhi, double weishengqingjie, double shebeisheshi, double fuwuzhiliang, String username) {
        this.iUserDao.updateInfo(jiudianweizhi,weishengqingjie,shebeisheshi,fuwuzhiliang,username);
    }

    @Override
    public void updateUser(User user) {
        iUserDao.updateUser(user);
    }

    @Override
    public void removeUser(int id) {
        iUserDao.removeUser(id);
    }


}
