package com.benz.reactive.web.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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


    @Test
    public void fluxTest_WithoutError()
    {
        Flux<String> stringFlux=Flux.just("Spring","Spring Boot","Reactive Programming")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Programming")
               .verifyComplete();

    }

    @Test
    public void fluxTest_WithError()
    {
        Flux<String> stringFlux=Flux.just("Spring","Spring Boot","Reactive Programming")
                .concatWith(Flux.error(()->new RuntimeException("Runtime Exception is occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring","Spring Boot","Reactive Programming")
               //.expectError(RuntimeException.class)
                .expectErrorMessage("Runtime Exception is occurred")
                .verify();

    }

    @Test
    public void monoTest()
    {
        Mono<String> stringMono=Mono.just("Spring Boot");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring Boot")
                .verifyComplete();
    }

    @Test
    public void monoTest_WithError()
    {


        StepVerifier.create(Mono.error(()->new RuntimeException("Exception is occurred")).log())
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Exception is occurred")
                .verify();
    }
}
