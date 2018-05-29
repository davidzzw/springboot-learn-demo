/*
 * FileName: PersonRouter.java
 * Author:   zzw
 * Date:     2018年05月28日
 * Description:
 */
package com.zzw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> route(PersonHandler personHandler) {
        return RouterFunctions.route(GET("/people/{id}").and(accept(APPLICATION_JSON)), personHandler::get).andRoute(GET("/people").and(accept(APPLICATION_JSON)), personHandler
                                                        ::all).andRoute(POST("/people").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::post).
                andRoute(PUT("/people/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), personHandler::put)
                        .andRoute(DELETE("/people/{id}"), personHandler::delete)
                        .andRoute(GET("/people/country/{country}").and(accept(APPLICATION_JSON)), personHandler::getByCountry);
    }
}
