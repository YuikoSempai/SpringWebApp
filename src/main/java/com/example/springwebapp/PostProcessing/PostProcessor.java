package com.example.springwebapp.PostProcessing;

import com.example.springwebapp.models.AuditedMethod;
import com.example.springwebapp.repository.AuditedMethodRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PostProcessor implements BeanPostProcessor {

    private final Map<String, String> annotationMap = new HashMap<>();
    @Autowired
    private AuditedMethodRepository auditedMethodRepository;

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (hasAnyMeasuredMethod(bean)) {
            getAuditedMethodNames(bean, beanName);
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        String annotation = annotationMap.getOrDefault(method.getName(), null);
                        if (annotation != null) {
                            auditedMethodRepository.save(new AuditedMethod(LocalDateTime.now().toString(), method.getName()));
                        }
                        return method.invoke(bean, args);
                    });
        }
        return bean;
    }

    private void getAuditedMethodNames(Object bean, String beanName) {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            Audited annotation = method.getAnnotation(Audited.class);
            if (annotation != null) {
                annotationMap.put(method.getName(), method.getName());
            }
        }
    }

    private boolean hasAnyMeasuredMethod(Object bean) {
        return Stream
                .of(ReflectionUtils.getAllDeclaredMethods(bean.getClass()))
                .anyMatch(method -> AnnotationUtils.getAnnotation(method, Audited.class) != null);
    }

}
