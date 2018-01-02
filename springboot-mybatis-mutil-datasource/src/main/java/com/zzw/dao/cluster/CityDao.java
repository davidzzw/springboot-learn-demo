package com.zzw.dao.cluster;

import com.zzw.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityDao {

    City findByName(@Param("cityName") String cityName);
}
