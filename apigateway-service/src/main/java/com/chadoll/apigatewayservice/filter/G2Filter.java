package com.chadoll.apigatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// 하나의 글로벌 필터
@Component
public class G2Filter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // pre: 마이크로서비스에 도착하기 전에 거치는 로직
        System.out.println("pre global filter order -2");

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    // post: 마이크로서비스에 도달 후 거치는 로직
                    System.out.println("post global filter order -2");
                }));
    }

    // 필터에 순서 지정 (들어갈때 -2, -1, - 0, 1 / 나올 때 1, 0, -1, -2 순서로 동작)
    @Override
    public int getOrder() {

        return -2;
    }
}