
package com.zzw.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
@Configuration
@ComponentScan(basePackages={"com.zzw"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@EnableAsync
public class SpringConfig {
}
