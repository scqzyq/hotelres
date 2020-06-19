package com.hotelres.service.impl;

import com.hotelres.bean.Hadmin;
import com.hotelres.dao.IAdminDao;
import com.hotelres.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author scqzy
 */
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    @Resource
    IAdminDao iAdminDao;
    @Override
    public Hadmin login(String aname, String apassword) {
        return iAdminDao.login(aname,apassword);
    }
}
