package com.benz.reactive.web.router;

import com.benz.reactive.web.handler.SampleHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> router(SampleHandlerFunction handlerFunction)
    {

       return RouterFunctions.route(RequestPredicates.GET("/functional/flux").
                and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handlerFunction::flux)
               .andRoute(RequestPredicates.GET("/functional/mono").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handlerFunction::mono);
    }
}
