package com.zzw.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzw
 * @see
 * @since 2018/2/22
 */
@Configuration
@ConfigurationProperties(prefix = "zzw.database")
public class DataBase {

    private String url;
    //......
}
