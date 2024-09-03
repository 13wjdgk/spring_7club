package com.mycom.myapp.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycom.myapp.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final HttpSession session;

    // 로그인 메소드에 대한 포인트컷
    @Pointcut("execution(* com.mycom.myapp.controller.UserController.login(..))")
    private void loginPointcut() {}
    
    // advise
    @Before("loginPointcut()")
    public void logMethodCall(JoinPoint joinPoint) {
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto == null) return;
        
        String methodName = joinPoint.getSignature().getName();
        
        logger.info("{} 가 {} 를 호출했습니다.", userDto.getEmail(), methodName);
    }
}
