package com.ratta.gateway.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * **解决终端老版本同步时文件名包含特殊字符问题 page
 * 
 */
@Component
@Slf4j
public class SynchronousCharFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		// 获取请求路径
		String url = request.getURI().toString();
		ServerHttpRequestDecorator serverHttpRequestDecorator = null;
		// 从header头信息中Content-Type信息
		String contentType = request.getHeaders().getFirst("Content-Type");
		if (contentType != null && contentType != "") {
			log.info("请求的原始文本类型： " + contentType);
		}

		if (url.indexOf("/terminal") != -1) {
			if (!contentType.equals("application/json")) {
				// 定义新的消息头
				HttpHeaders headers = new HttpHeaders();
				headers.putAll(exchange.getRequest().getHeaders());
				if (url.indexOf("/terminal/synchronous/compare") != -1 || url.indexOf("/terminal/file/upload") != -1) {
					String responseData = (String) exchange.getAttribute("cachedRequestBodyObject");
					// 替换地址
					if (responseData.indexOf("%25") != -1) {
						responseData = responseData.replace("%25", "%");
					}
					if (responseData.indexOf("%2B") != -1) {
						responseData = responseData.replace("%2B", "+");
					}
					if (responseData.indexOf("%3D") != -1) {
						responseData = responseData.replace("%3D", "=");
					}
					if (responseData.indexOf("%26") != -1) {
						responseData = responseData.replace("%26", "&");
					}
					DataBuffer bodyDataBuffer = stringBuffer(responseData);
					Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

					headers.remove(HttpHeaders.CONTENT_LENGTH);
					headers.setContentLength(responseData.getBytes().length);
					// 利用ServerHttpRequestDecorator来解析request，并修改内容
					serverHttpRequestDecorator = new ServerHttpRequestDecorator(request) {
						@Override
						public HttpHeaders getHeaders() {
							long contentLength = headers.getContentLength();
							HttpHeaders httpHeaders = new HttpHeaders();
							httpHeaders.putAll(super.getHeaders());
							if (contentLength > 0) {
								httpHeaders.setContentLength(contentLength);
							} else {
								httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
							}
							return httpHeaders;
						}

						@Override
						public Flux<DataBuffer> getBody() {
							return bodyFlux;
						}
					};

				}
			}
		}
		return chain.filter(exchange.mutate().request(serverHttpRequestDecorator).build());
	}

	@Override
	public int getOrder() {
		// 数字越低优先级越高
		return -100;
	}

	/**
	 * 字符串转DataBuffer
	 * 
	 * @param value
	 * @return
	 */
	private DataBuffer stringBuffer(String value) {
		byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
		NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
		DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
		buffer.write(bytes);
		return buffer;
	}
}