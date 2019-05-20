package com.example.graphql;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@Component
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

    public Flux<String> testSubscription(String arg) {
        return Flux.interval(Duration.ofSeconds(2)).map(i -> arg + Instant.now().toString());
    }
}
