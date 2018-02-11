package com.zzw.configuration;

import com.zzw.vo.UserVo;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zzw
 * @see
 * @since 2018/2/11
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //手动注入 Forwards 类的实例
        BeanDefinitionBuilder beanDef_forwards = BeanDefinitionBuilder.rootBeanDefinition(UserVo.class);
        registry.registerBeanDefinition("forwards", beanDef_forwards.getBeanDefinition());
    }
}
