package ru.janeryshouse.orderservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* ru.janeryshouse.orderservice.service.OrderService.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // Логирование перед вызовом метода (аналог @Before)
        log.info("Вызов метода: {} с аргументами: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());

        try {
            // Вызов целевого метода
            Object result = joinPoint.proceed();

            // Логирование после успешного завершения метода (аналог @AfterReturning)
            log.info("Метод: {} успешно завершен с результатом: {}", joinPoint.getSignature().toShortString(), result);

            // Возвращаем результат выполнения метода
            return result;
        } catch (Exception e) {
            // Логирование исключения (аналог @AfterThrowing)
            log.error("Метод: {} завершился с ошибкой: {}", joinPoint.getSignature().toShortString(), e.getMessage());

            // Пробрасываем исключение дальше
            throw e;
        }
    }
}
