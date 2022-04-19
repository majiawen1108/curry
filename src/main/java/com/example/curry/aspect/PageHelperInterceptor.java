package com.example.curry.aspect;

import com.example.curry.model.PageListResult;
import com.example.curry.utils.RequestParms;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jw.ma
 * @title: PageHelperInterceptor
 * @description: TODO
 * @date 2022/4/18 11:11
 */
@Component
@Aspect
public class PageHelperInterceptor {
    private static final Logger log = LoggerFactory.getLogger(PageHelperInterceptor.class);

    /**
     * 使用线程本地变量
     */
    private static final ThreadLocal<RequestParms> PAGEBEANCONTEXT = new ThreadLocal<>();


    /**
     * 以WithPage结尾的Controller方法都是需要分页的方法
     * @param joinPoint
     * @throws Exception
     */
    @Before(value = "execution(public * com.example.curry.web.*.*WithPage(..))")
    public void controllerAop(JoinPoint joinPoint) throws Exception {
        log.info("Controller正在执行PageHelperAop");
        RequestParms requestParms =null;

        Object[] args = joinPoint.getArgs();
        //获取类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //通过反射获取参数列表
        Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName,args);

        requestParms = (RequestParms) nameAndArgs.get("requestParms");
        if(requestParms == null){
            requestParms = new RequestParms();
            requestParms.setPageNum((Integer) nameAndArgs.get("pageNum"));
            requestParms.setPageSize((Integer) nameAndArgs.get("pageSize"));
        }
        //将分页参数放置线程变量中
        PAGEBEANCONTEXT.set(requestParms);
    }

    @Before(value = "execution(public * com.example.curry.service.impl.*.*WithPage(..))")
    public void serviceImplAop(){
        log.info("Impl正在执行PageHelperAop");
        RequestParms requestParms = PAGEBEANCONTEXT.get();
        PageHelper.startPage(requestParms.getPageNum(), requestParms.getPageSize());
    }

    @AfterReturning(value = "execution(* com.example.curry.mapper.*.*WithPage(..))")
    public void mapperAop(){
        log.info("mapper正在执行PageHelperAop");
        PAGEBEANCONTEXT.remove();
    }

    /**
     * 通过反射获取参数列表
     * @throws Exception
     */
    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > map=new HashMap<String,Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put( attr.variableName(i + pos),args[i]);
        }
        return map;
    }

}
