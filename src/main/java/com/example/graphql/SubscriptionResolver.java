package com.example.graphql;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    public Flux<String> testSubscription(String arg) {
        Flux<String> stringFlux = Flux.create(emitter -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(sendValues(emitter), 0, 2, TimeUnit.SECONDS);

        });
        return stringFlux;
    }


    private Runnable sendValues(FluxSink<String> emitter) {
        return () -> {
            emitter.next("test" + Instant.now().toString());
        };
    }

}
