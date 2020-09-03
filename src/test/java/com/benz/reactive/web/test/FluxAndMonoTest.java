package com.benz.reactive.web.test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

    @Test
    public void fluxTest()
    {
       Flux<String> stringFlux= Flux.just("Spring","Spring Boot","Reactive Spring")
           .concatWith(Flux.error(new RuntimeException("Benze error is occurred")))
               .concatWith(Flux.just("Kelly Brook"))
       .log();

       stringFlux.subscribe(System.out::println
       ,ex->System.err.println("Exception is "+ex),
               ()->System.out.println("Completed"));
    }
}
