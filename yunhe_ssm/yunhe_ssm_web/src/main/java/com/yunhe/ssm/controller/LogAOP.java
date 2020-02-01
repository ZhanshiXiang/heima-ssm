package com.yunhe.ssm.controller;

/**
 * @author James
 * @create 2019-08-13 14:49
 */

import com.yunhe.ssm.domain.SysLog;
import com.yunhe.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 日志切面类
 */


@Component
@Aspect
public class LogAOP {

    //访问开始时间
    private Date visitTime;

    //访问的类名
    private Class clazz;

    //访问的方法
    private Method method;

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 前置通知
     *
     * 作用：获取访问类的时间、类名、方法名
     */
    @Before("execution(* com.yunhe.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //1、获取访问时间==当前系统时间
        visitTime = new Date();
        //访问的类
        clazz = jp.getTarget().getClass();


        //2、访问方法名称
        String methodName = jp.getSignature().getName();



        //3、获取方法名
        //获取访问方法参数
        Object[] args = jp.getArgs();
        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            //获取无参方法名
             method = clazz.getMethod(methodName);
        } else {
            //有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            //遍历空数组中每一个元素
            for (int i = 0; i < classArgs.length; i++) {
                //获取每一个参数，将其添加入数组中
                classArgs[i] = args[i].getClass();
            }
            //返回方法名
            clazz.getMethod(methodName, classArgs);
        }


    }


    /**
     * 后置通知
     *
     * @param jp
     */
    @After("execution(* com.yunhe.ssm.controller.*.*(..))")
    public void doAftere(JoinPoint jp) throws Exception {

        //获取执行时长
        long time = new Date().getTime() - visitTime.getTime();


        String url="";
        //获取url
        if (clazz != null && method != null && clazz != LogAOP.class) {
            // 1、获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                //获取类上/orders
                String[] classValue = classAnnotation.value();
                // 2、获取方法上的@RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    //获取方法上/findAll.do
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];



                    //获取ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    //1、通过SpringSecurity 获取 ,从上下文中获取对象
                    SecurityContext context = SecurityContextHolder.getContext();
                    //2、从session中获取
                    //Object spring_security_context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //将日志信息封装
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                        //调用service中save()方法保存日志实体
                        sysLogService.save(sysLog);


                }

            }

        }




    }


}
