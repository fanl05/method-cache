package com.ryland.method.cache.aspect;

import com.ryland.method.cache.anno.Cache;
import com.ryland.method.cache.cache.CacheManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
@Slf4j
public class MethodCacheAspect {

    @Around(value = "@annotation(com.ryland.method.cache.anno.Cache)")
    public Object methodCache(ProceedingJoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        } catch (NoSuchMethodException ex) {
            log.error("{}", ex.toString());
        }

        if (Objects.isNull(method)
                || Objects.equals("void", method.getReturnType().getName())) {
            return proceed(joinPoint);
        }

        Cache cacheAnno = method.getAnnotation(Cache.class);
        String cacheName = "".equals(cacheAnno.name()) ? method.getName() : cacheAnno.name();

        Object[] parameters = 0 == method.getParameterTypes().length ? CacheManager.EMPTY_PARAMETER : joinPoint.getArgs();

        Object cacheValue = CacheManager.get(cacheName, parameters);
        if (Objects.nonNull(cacheValue)) {
            return cacheValue;
        }

        Object returnValue = proceed(joinPoint);
        if (Objects.nonNull(returnValue)) {
            CacheManager.put(cacheName, parameters, returnValue);
        }
        return returnValue;
    }

    private Object proceed(ProceedingJoinPoint joinPoint) {
        Object ret = null;
        try {
            ret = joinPoint.proceed();
        } catch (Throwable t) {
            log.error("{}", t.toString());
        }
        return ret;
    }

}
