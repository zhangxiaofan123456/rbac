package com.baizhi.component;



import com.baizhi.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAop {
    @Pointcut("execution(public * com.baizhi.controller..*(..))")
    public void pointcut(){
    }

    @Around("pointcut()")//代表我这个Around是针对pointcut()这个切点
    public Response checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获取请求
        long startTime=System.currentTimeMillis();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//拿到请求的上下文
        ServletRequestAttributes requestAttributes1=(ServletRequestAttributes) requestAttributes;//真实能拿到请求的是这个实现类，所以要做一个强转。
        HttpServletRequest request = requestAttributes1.getRequest();//拿到这个请求
        //2.解析请求中的url和参数列表
        //参数替换掉{}这个占位符
        log.info("请求URL：{}",request.getRequestURL().toString());
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String paramKey : parameterMap.keySet()) {
            log.info("请求参数：key={}，value={}",paramKey,parameterMap.get(paramKey));
        }
        //3.正常执行方法
        Response result= (Response)joinPoint.proceed();
        //4.解析执行前端
        log.info("响应结果：{}",result);
        log.info("本次调用接口共计时间： {}ms",System.currentTimeMillis()-startTime);
        log.info("============");
        return result;

    }




    }



