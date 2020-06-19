package com.hotelres.service;

import com.hotelres.bean.User;

import java.util.List;

public interface IUserService {
    public List<User> findAll(String username);

    public void saveUser(User user);

    public User login(String username,String password);

    public User findByIdcard(String idcard);

    public User findByUsername(String username);

    public User findByIdcardAndRealname(String idcard,String realname);

    public void updatePassword(String username,String password);

    public void updateInfo(double jiudianweizhi,double weishengqingjie,double shebeisheshi,double fuwuzhiliang,String username);

    public void updateUser(User user);

    public void removeUser(int id);
}
