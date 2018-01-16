package com.zzw.configuration;

import com.zzw.interceptor.TestInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzw
 * @see
 * @since 2018/1/15
 */
//@Configuration
public class AopConfigurtion extends DefaultPointcutAdvisor implements InitializingBean{

    private Pointcut pointcut;
    private Advice advice;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pointcut = new JdkRegexpMethodPointcut();
        JdkRegexpMethodPointcut pt = (JdkRegexpMethodPointcut) pointcut;
        pt.setPattern("com.zzw.service.*");
        advice = new TestInterceptor();
    }
}
