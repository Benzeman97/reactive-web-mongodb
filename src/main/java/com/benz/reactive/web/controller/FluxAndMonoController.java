package com.benz.reactive.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/reactive")
public class FluxAndMonoController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux()
    {
        return Flux.just(4,3,2,1).
                delayElements(Duration.ofSeconds(1L)).log();
    }

    @GetMapping(value="/fluxstream",produces = {MediaType.APPLICATION_STREAM_JSON_VALUE})
    public Flux<Integer> returnFluxStream()
    {
        return Flux.just(4,3,2,1).
                delayElements(Duration.ofSeconds(1L)).log();
    }
}
