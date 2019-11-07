package com.ratta.gateway.authorize;

import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * **身份验证
 * page
 * 
 */
@Component
@Slf4j
@RefreshScope
public class AuthorizeGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizeGatewayFilterFactory.Config> {
    /**
     * 授权token
     */
    private static final String AUTHORIZE_TOKEN = "x-access-token";
    /**
     * 授权令牌token 用来再次获取的 如果二次获取的token不一样，需要跟redis中的token做校验
     */
    private static final String AUTHORIZE_PARAMS_TOKEN = "token";
    
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${origin.rediskey.allow}")
	private String manageOrigin;
	
	@Value("${preview.adrress}")
	private String previewAdrress;
	
	@Value("${production.adrress}")
	private String productionAdrress;
	
	@Value("${localhost.adrress}")
	private String localhostAdrress;
	

    public AuthorizeGatewayFilterFactory() {
        super(Config.class);
        log.info("Loaded GatewayFilterFactory [Authorize]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
        	 //获取ServerHttpRequest对象
            ServerHttpRequest request = exchange.getRequest();
            Object requestBody = exchange.getAttribute("cachedRequestBodyObject");
            String url = request.getURI().toString();
            String origin = request.getHeaders().getOrigin();
            log.info("222222获取到的域名:[{}]", request.getHeaders().toString());
    		log.info("22222222计算的域名:[{}]", previewAdrress);
    		log.info("23333333333计算的域名:[{}]", productionAdrress);
    		log.info("222222", request.getHeaders().get("Referer"));
            if (!config.isEnabled()) {
            	if(request.getHeaders().toString().indexOf(previewAdrress) != -1) {
            		ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.add("Line", "1");
                    }).build();
                	ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
            	}
            	
            	if(request.getHeaders().toString().indexOf(productionAdrress) != -1) {
            		ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.add("Line", "0");
                    }).build();
                	ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
            	}
            	
            	if(request.getHeaders().toString().indexOf(localhostAdrress) != -1) {
            		ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.add("Line", "0");
                    }).build();
                	ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
            	}
            	
            	return chain.filter(exchange);
            }
            
            User user = null;
            String rediskey = null;
            if(!StringUtils.isEmpty(request.getCookies()) && !StringUtils.isEmpty(request.getCookies().getFirst("redisKey"))) {
            	rediskey = request.getCookies().getFirst("redisKey").getValue();
            }
            
           
            //获取ServerHttpResponse对象
            ServerHttpResponse response = exchange.getResponse();

            // 获取HttpHeaders对象
            HttpHeaders headers = request.getHeaders();
            ServerWebExchange build = null;

            //从header头信息中token信息
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            if (token == null) {
                //如果token为null，则从请求参数中尝试再次获取
                token = getParamToken(requestBody);
            }
            
            log.info("请求的url: {}, 请求的rediskey: {}, 请求的token: {}",url,rediskey,token);
            
            if (token != null && !"".equals(token)) {
                try {
                    //调用token解析方法
                    user = getUser(origin, rediskey,url,token);
                    final String userId = user.getUserId();
                    //设置请求头
                    ServerHttpRequest host = exchange.getRequest().mutate().headers(httpHeaders -> {
                        httpHeaders.add("User-ID", userId);
                    }).build();
                    build = exchange.mutate().request(host).build();
                } catch (Exception exception) {
                    //token无效
                    log.error("token失效或者不存在:" + token);
                    //设置状态码
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }
            }else {
            	if(url.indexOf("/system/business/app/version/update") != -1) {
            		return chain.filter(exchange);
            	}
            	response.setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("token失效或者不存在:" + token);
                return response.setComplete();
            }
            //认证通过，将用户ID进行传递到下游服务器
            return chain.filter(build);
        };
    }

    private String getParamToken(Object requestBody) {
    	if(requestBody != null) {
    		String body = requestBody.toString().replace("\n","").replace(" ","").replace("\"","").replace("{","").replace("}","");
    		if(!StringUtils.isEmpty(body)) {
        		body = body.substring(1, body.length());
        		String[] arrBody = body.split(","); 
        		for (String string : arrBody) {
        			String[] arrParam = string.split(":");
        			if(arrParam[0].toLowerCase().equals(AUTHORIZE_PARAMS_TOKEN)) {
        				log.info("从请求body里面获取到的token为:[{}]", string.substring(string.indexOf(":")+1));
        				return string.substring(string.indexOf(":")+1);
        			}
    			}
    		}
    	}
		return null;
    }
    
    /**
     * /解析token
     *
     * @param token
     * @return
     */
	private User getUser(String origin, String rediskey, String url, String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("supernote");
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
		} catch (Exception e) {
			if(!StringUtils.isEmpty(origin)) {
				try {
					SecretKey key = generalKey();
					@SuppressWarnings("unused")
					Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
				} catch (Exception e1) {
					log.error("e1:[{}]", e1.getMessage());
					return new User();
				}
			}
		}
		DecodedJWT jwt = JWT.decode(token);
		String sub = jwt.getClaim("sub").asString();
		User user = JSONObject.parseObject(sub, User.class);
		String noOriginUrl = url.split("//")[1].split("/")[1];
		if (noOriginUrl.startsWith("terminal")) {
			return user;
		}
		log.info("获取到的域名:[{}]", origin);
		log.info("计算的域名:[{}]", url.split("//")[0] + "//" + url.split("//")[1].split("/")[0]);
		if(!StringUtils.isEmpty(origin) && manageOrigin.indexOf(origin) != -1) {
			return user;
		}else if (manageOrigin.indexOf(url.split("//")[0] + "//" + url.split("//")[1].split("/")[0]) != -1) {
			return user;
		}

		String token2 = (String) redisTemplate.opsForValue().get(rediskey + "_token");
		if (StringUtils.isEmpty(token2) || !token.equals(token2)) {
			return new User();
		}
		return user;
	}

    public static SecretKey generalKey(){
      String stringKey = "suponotes" + "7786df7fc3a34e26a61c034d5ec8245d";
      byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    } 

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config {
        /**
         * 控制是否开启认证
         */
        private boolean enabled;
    }
}
