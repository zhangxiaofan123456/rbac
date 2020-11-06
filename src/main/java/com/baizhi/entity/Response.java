package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;
    private String msg;
    private Object data;

    public  static Response success(String msg,Object data){
        return new Response(200,msg,data);
    }
    public  static Response operationResultIsEmpty(String msg,Object data){
        return new Response(200,msg,data);
    }
    public  static Response failure(String msg,Object data){
        return new Response(400,msg,data);
    }



}
