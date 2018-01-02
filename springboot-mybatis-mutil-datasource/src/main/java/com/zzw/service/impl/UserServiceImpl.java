package com.zzw.service.impl;

import com.zzw.dao.cluster.CityDao;
import com.zzw.dao.master.UserDao;
import com.zzw.domain.City;
import com.zzw.domain.User;
import com.zzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzw
 * @see
 * @since 2018/1/2
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private CityDao cityDao;
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("温岭市");
        user.setCity(city);
        return user;
    }
}
