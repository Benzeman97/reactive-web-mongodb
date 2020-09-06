package com.benz.reactive.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoController{

    @Autowired
    WebTestClient webTestClient;

    @Test
   public void flux_approach1()
   {
     Flux<Integer> integerFlux= webTestClient.get().uri("/reactive/flux")
               .accept(MediaType.APPLICATION_JSON)
               .exchange()
               .expectStatus().isOk()
               .returnResult(Integer.class)
               .getResponseBody();

       StepVerifier.create(integerFlux)
               .expectSubscription()
               .expectNext(4)
               .expectNext(3)
               .expectNext(2,1)
               .verifyComplete();
   }
}
