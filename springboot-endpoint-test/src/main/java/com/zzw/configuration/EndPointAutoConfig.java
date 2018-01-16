package com.zzw.configuration;

import com.zzw.endpoint.MyEndPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zzw
 * @see
 * @since 2018/1/16
 */
@Configuration
public class EndPointAutoConfig {

    @Bean
    public MyEndPoint myEndPoint() {
        return new MyEndPoint();
    }
}
