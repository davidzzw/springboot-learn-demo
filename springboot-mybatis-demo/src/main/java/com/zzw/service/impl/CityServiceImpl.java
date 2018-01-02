package com.zzw.service.impl;

import com.zzw.dao.CityDao;
import com.zzw.model.City;
import com.zzw.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzw
 * @see
 * @since 2018/1/2
 */
@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDao cityDao;

    @Override
    public City findByName(String cityName) {
        return cityDao.findByName(cityName);
    }
}
