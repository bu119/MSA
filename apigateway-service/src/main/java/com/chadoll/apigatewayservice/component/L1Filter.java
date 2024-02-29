package com.chadoll.apigatewayservice.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

// 지역 필터 (특정한 마이크로서비스- 라우팅에 대해서만 적용: yml에서 헤당 필터를 특정 라우팅에 등록해야한다.)
@Component
public class L1Filter extends AbstractGatewayFilterFactory<L1Filter.Config> {

    // 생성자
    public L1Filter() {

        super(Config.class);
    }

    // 지역 필터 내용 작성
    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // 마이크로서비스 거치기 전 로직 작성
            if (config.isPre()) {
                System.out.println("pre local filter 1");
            }

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // 마이크로 서비스 거친 후 로직 작성
                        if (config.isPost()) {

                            System.out.println("post local filter 1");
                        }
                    }));
        };
    }

    // 특정 변수 값을 가질 수 있다.
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Config {
        // 받을 변수 선언
        private boolean pre;
        private boolean post;
    }
}