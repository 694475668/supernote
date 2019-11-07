package com.ratta.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("errorCode", "500");
        map.put("errorMsg", "客官，您访问的页面已经飞走了！");
        log.error("ERROR:", e);
        return map;
    }
}
