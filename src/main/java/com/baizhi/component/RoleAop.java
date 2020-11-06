package com.baizhi.component;

import com.baizhi.annotation.NeedRole;
import com.baizhi.entity.Permission;
import com.baizhi.entity.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class RoleAop {

    @Pointcut("execution(public * com.baizhi.controller..*ADMIN(..))")
    public void pointcut(){


    }
    @Around("pointcut()")
    public Response checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        NeedRole annotation = method.getAnnotation(NeedRole.class);
        int value = annotation.value();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes1=(ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = requestAttributes1.getRequest();
        HttpSession session = request.getSession();
        List<Permission>list= (List<Permission>) session.getAttribute("userPermission");
        if (list!=null&&list.size()>0){
            for (Permission permission : list) {
                if (permission.getId()==value){
                    return (Response) joinPoint.proceed();
                }
            }
            Response response = new Response(400, "你的权限不足", null);
            return response;
        }else {
            Response response = new Response(400, "你没有登录", null);
            return response;
        }

    }




}
