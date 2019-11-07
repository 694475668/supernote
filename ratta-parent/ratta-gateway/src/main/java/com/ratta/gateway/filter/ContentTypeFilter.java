package com.ratta.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * **解决终端老版本Content-type不是json的问题
 * page
 * 
 */
@Component
public class ContentTypeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //获取请求路径
        String url = request.getURI().toString();
        //从header头信息中Content-Type信息
        String contentType = request.getHeaders().getFirst("Content-Type");
        if (url.indexOf("/terminal") != -1) {
            if (!contentType.equals("application/json")) {
            	//设置请求头
                ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
                    //设置请求头ContentType 为  application/json
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                }).build();
                //传到下游服务
                ServerWebExchange build = exchange.mutate().request(host).build();
                return chain.filter(build);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //数字越低优先级越高
        return -80;
    }
}