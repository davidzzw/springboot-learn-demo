package com.zzw.service;

import com.zzw.domain.User;

/**
 * @author zzw
 * @see
 * @since 2018/1/2
 */
public interface UserService {
    User findByName(String userName);
}
