package com.zzw.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zzw
 * @see
 * @since 2018/1/15
 */
public class TestInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("*************");
        Object proceed = invocation.proceed();
        logger.info("#############");
        return proceed;
    }
}
