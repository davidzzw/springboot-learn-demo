package com.zzw.util;

import com.zzw.vo.BeanDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/1/17
 */
public class ApplicationContextUtil {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    public static List<BeanDetails> getBeanDetails(ApplicationContext context) {

        List<BeanDetails> list = new ArrayList<>();
        AnnotationConfigEmbeddedWebApplicationContext configEmbeddedWebApplicationContext = AnnotationConfigEmbeddedWebApplicationContext.class.cast(context);
        ConfigurableListableBeanFactory beanFactory = configEmbeddedWebApplicationContext.getBeanFactory();

        String[] beanNames = context.getBeanDefinitionNames();
        int allBeansCount = context.getBeanDefinitionCount();

        BeanDetails beanDetails = null;
        for (String beanName : beanNames) {
            beanDetails = new BeanDetails();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            //Class<?> beanType = context.getType(beanName);
            //Package beanPackage = beanType.getPackage();

            beanDetails.setBean(beanName);
            beanDetails.setScope(beanDefinition.getScope());
            beanDetails.setType( beanDefinition.getBeanClassName());
            beanDetails.setResource(beanDefinition.getResourceDescription());
            if(beanDefinition.getDependsOn() != null){
                beanDetails.setDependencies(Arrays.asList(beanDefinition.getDependsOn()));
            }
            list.add(beanDetails);
           /* Object bean = context.getBean(beanName);
            logger.info("BeanName:" + beanName);
            logger.info("Bean的类型：" + beanType);
            logger.info("Bean所在的包：" + beanPackage);*/
        }

        /*while (context.getParent() != null) {
            context = context.getParent();
            list.add(beanDetails);

        }*/
        return list;
    }
}
