
package com.baizhi.component;

import com.baizhi.entity.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception e){
        e.printStackTrace();
        return new Response(500,"系统开小差了",null);

    }


}
