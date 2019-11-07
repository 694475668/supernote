package com.ratta.gateway.exception;
/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/

/**
 * ******Title:程序的奥秘 ******
 * ******Description:<p> 自定义异常处理</p>******
 * ******Company: <p> 雷塔智能科技有限公司 </p> ******
 *
 * @version 1.0:
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-07-09 17:35
 */

import java.util.*;

import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

    /**
     * Create a new {@code DefaultErrorWebExceptionHandler} instance.
     *
     * @param errorAttributes    the error attributes
     * @param resourceProperties the resources configuration properties
     * @param errorProperties    the error configuration properties
     * @param applicationContext the current application context
     */
    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    /**
     * 获取异常属性
     */

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = 500;
        Throwable error = super.getError(request);
        if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
            code = 404;
        }
        log.error(response(code, this.buildMessage(request, error)).toString());

        return response(code, this.buildMessage(request, error));
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes
     */

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {

        /**
         * json显示错误信息
         */
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);

        /**
         * HTML示错误信息
         */
        // return RouterFunctions.route(acceptsTextHtml(),
        // this::renderErrorView).andRoute(RequestPredicates.all(),
        // this::renderErrorResponse);
    }

    /**
     * 根据code获取对应的HttpStatus 原始的方法是通过status来获取对应的HttpStatus的，
     * 如果我们定义的格式中没有status字段的话，这么就会报错，找不到对应的响应码，
     * 要么返回数据格式中增加status子段，要么重写，我这边返回的是code，所以要重写
     *
     * @param errorAttributes
     */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get("errorCode");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 构建异常信息
     *
     * @param request
     * @param ex
     * @return
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 构建返回的JSON数据格式
     *
     * @param status       状态码
     * @param errorMessage 异常信息
     * @return
     */
    public static Map<String, Object> response(int errorCode, String errorMessage) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("errorCode", errorCode);
        map.put("errorMsg", "客官，您访问的页面已经飞走了！");
        return map;
    }
}
