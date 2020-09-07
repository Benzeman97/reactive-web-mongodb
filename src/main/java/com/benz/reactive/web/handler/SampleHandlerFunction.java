package com.benz.reactive.web.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SampleHandlerFunction {

    public Mono<ServerResponse> flux(ServerRequest request)
    {
         return ServerResponse.ok()
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(
                          Flux.just(5,4,3,2,1).log(),
                          Integer.class
                  );
    }

    public Mono<ServerResponse> mono(ServerRequest request)
    {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(4).log(),
                        Integer.class
                );
    }
}
