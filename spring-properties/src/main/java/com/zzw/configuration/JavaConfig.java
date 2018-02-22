package com.zzw.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zzw
 * @see
 * @since 2018/2/22
 */
@PropertySource("classpath:jdbc.properties")
@Configuration
public class JavaConfig {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
