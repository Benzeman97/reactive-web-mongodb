package com.benz.reactive.web.handler;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SampleHandlerFunctionTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void flux_approach()
    {
      Flux<Integer> integerFlux=   webTestClient.get().uri("/functional/flux")
                .exchange()
                .expectStatus().isOk()
                 .returnResult(Integer.class)
                 .getResponseBody();

        StepVerifier.create(integerFlux)
                .expectSubscription()
                .expectNext(5,4,3,2,1)
                .verifyComplete();
    }


    @Test
    public void mono_approach()
    {
         Integer expectedInteger=4;

        webTestClient.get().uri("/functional/mono")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith(response->{
                    Assert.assertEquals(expectedInteger,response.getResponseBody());
                });
    }

}
