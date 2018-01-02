package com.zzw.dao;

import com.zzw.model.City;
import org.apache.ibatis.annotations.Param;

public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
