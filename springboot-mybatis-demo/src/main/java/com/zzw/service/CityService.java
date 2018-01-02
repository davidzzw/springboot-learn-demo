package com.zzw.service;

import com.zzw.model.City;

/**
 * @author zzw
 * @see
 * @since 2018/1/2
 */
public interface CityService {

    City findByName(String cityName);
}
