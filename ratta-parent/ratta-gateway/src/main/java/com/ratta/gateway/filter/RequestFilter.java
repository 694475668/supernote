package com.ratta.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RequestFilter implements GatewayFilter, Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
		log.info("request body is:{}", requestBody);
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.info("RequestFilter post filter");
		}));
	}

	@Override
	public int getOrder() {
		return -200;
	}

}