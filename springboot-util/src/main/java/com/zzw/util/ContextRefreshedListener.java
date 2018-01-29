package com.zzw.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * @author zzw
 * @see
 * @since 2018/1/27
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null){
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Controller.class);
            for(Object bean:beans.values()){
                logger.info(bean == null ? "null":bean.getClass().getName());
            }
            logger.info("=====ContextRefreshedEvent=====" + event.getSource().getClass().getName());
        }
    }
}
