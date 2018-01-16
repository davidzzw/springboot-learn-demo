package com.zzw.service.impl;

import com.zzw.annotation.Log;
import com.zzw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author zzw
 * @see
 * @since 2018/1/15
 */
@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Log
    public void test() {
        logger.info("test");
    }
}
