package com.example.demo.config;

import com.example.demo.domain.CommonDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 컨트롤러가 메모리에 뜨기전에 무언가 설정해야 한다면, @Configuration 어노테이션 사용,
// 컨트롤러가 이미 메모리 떠있고 무언가 설정해야 한다면, @Component 사용
@Component
@Aspect
public class BindingAdvice {

    private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

    // @Before : 함수의 앞을 제어
    @Before("execution(* com.example.demo.controller..*ApiController.*(..))")
    void beforeCheck() {
        // request 값 처리 어떻게?
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        System.out.println("전처리 로그 남기기");
    }

    // @After : 함수의 뒤를 제어
    @After("execution(* com.example.demo.controller..*ApiController.*(..))")
    void afterrCheck() {
//        System.out.println("후처리 로그 남기기");
    }

    // @Around : 함수의 앞, 뒤 모두 제어
    @Around("execution(* com.example.demo.controller..*ApiController.*(..))") // 해당 패키지에의 ApiController로 끝나는 모든 함수의 앞, 뒤에 실행
    public Object validationCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 리플렉션을 통해 메서드의 타입, 이름, 인자 정보를 가져올 수 있음
        String typeName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        for (Object arg : args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult)arg;

                if(bindingResult.hasErrors()) {
                    System.out.println("bindingResult has errors");
                    Map<String, String> errorMap = new HashMap<>();

                    for(FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        // log level : error > warn > info > debug
                        log.warn(typeName + "." + name + "() => 필드 : " + error.getField() + ", 메세지 : " + error.getDefaultMessage());
                    }

                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed(); // 함수의 스택을 실행 ( 필터 체인의 doFilter() 비슷)
    }
}
