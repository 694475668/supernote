package com.ratta.gateway.filter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * **打印请求和响应日志
 * page
 * 
 */
@Component
@Slf4j
public class MessageFilter implements GlobalFilter, Ordered {
	@Override
	public int getOrder() {
		return -1;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		 //获取ServerHttpRequest对象
        ServerHttpRequest request = exchange.getRequest();
        Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
        String url = request.getURI().toString();
        //从header头信息中Content-Type信息
		//获取response的 返回数据
		ServerHttpResponse originalResponse = exchange.getResponse();
		DataBufferFactory bufferFactory = originalResponse.bufferFactory();
		ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
			@Override
			public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
				if(url.indexOf("/pic/code") == -1 && url.indexOf("/export") == -1 && url.indexOf("/xlsx") == -1) {
					if (getStatusCode().equals(HttpStatus.OK) && body instanceof Flux) {
						Flux<? extends DataBuffer> fluxBody = Flux.from(body);
						
						return super.writeWith(fluxBody.buffer().map(dataBuffers -> {//解决返回体分段传输
							List<byte[]> list = new ArrayList<byte[]>();
	                        dataBuffers.forEach(dataBuffer -> {
	                            byte[] content = new byte[dataBuffer.readableByteCount()];
	                            dataBuffer.read(content);
	                            DataBufferUtils.release(dataBuffer);
								list.add(content);
	                        });
	                        byte[] both = null;
	                        Iterator<byte[]> iterator = list.iterator();
	                        while(iterator.hasNext()){
	                        	both = (byte[]) ArrayUtils.addAll(both, iterator.next());
	                        } 
	                        String responseData = new String(both, Charset.forName("UTF-8"));
	                        if(url.indexOf("/import") == -1 && url.indexOf("/upload/check") == -1 && url.indexOf("/picture/upload") == -1) {
	                        	log.info("\n requestHeaders=[{}],\n requestBody=[{}], \n requestUrl=[{}], \n responseBody:[{}] \n", request.getHeaders(), requestBody, url,responseData);
	                        }else {
	                        	log.info("\n requestHeaders=[{}],\n requestBody=[{}], \n requestUrl=[{}], \n responseBody:[{}] \n", request.getHeaders(), "文件流", url,responseData);
	                        }
	                        byte[] uppedContent = new String(responseData.getBytes(), Charset.forName("UTF-8")).getBytes();
	                        originalResponse.getHeaders().setContentLength(uppedContent.length);
	                        return bufferFactory.wrap(uppedContent);
						}));
					} else {
						log.error("\n 响应code异常:{} \n", getStatusCode());
					}
				}else {
					log.info("\n requestHeaders=[{}],\n requestBody=[{}], \n requestUrl=[{}], \n responseBody:[{}] \n", request.getHeaders(), requestBody, url,"文件流");
					
				}
				return super.writeWith(body);
			}
		};
		return chain.filter(exchange.mutate().response(decoratedResponse).build());
	}
}