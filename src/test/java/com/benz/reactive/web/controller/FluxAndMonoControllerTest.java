package com.benz.reactive.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {
    

    @Autowired
    WebTestClient webTestClient;

    @Test
   public void flux_approach1()
   {



     Flux<Integer> integerFlux= webTestClient.get().uri("/reactive/flux")
               .accept(MediaType.APPLICATION_STREAM_JSON)
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

   @Test
   public void flux_approach2()
   {
        webTestClient.get().uri("/reactive/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(4);
   }

   @Test
   public void flux_approach3()
   {
       List<Integer> expectedExchangeResult=new ArrayList<>(Arrays.asList(4,3,2,1));

      EntityExchangeResult<List<Integer>> entityExchangeResult= webTestClient.get().uri("/reactive/flux")
               .accept(MediaType.APPLICATION_JSON)
               .exchange()
               .expectStatus().isOk()
               .expectBodyList(Integer.class)
               .returnResult();

       Assert.assertEquals(expectedExchangeResult,entityExchangeResult.getResponseBody());
   }

   @Test
   public void flux_approach4()
   {
       List<Integer> expectedExchangeResult=new ArrayList<>(Arrays.asList(4,3,2,1));

       webTestClient.get().uri("/reactive/flux")
               .accept(MediaType.APPLICATION_JSON)
               .exchange()
               .expectStatus().isOk()
               .expectBodyList(Integer.class)
               .consumeWith(response->{
                   Assert.assertEquals(expectedExchangeResult,response.getResponseBody());
               });
   }
}
