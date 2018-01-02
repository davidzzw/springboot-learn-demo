package com.zzw.dao.master;

import com.zzw.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    User findByName(@Param("userName") String userName);
}
