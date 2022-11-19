package book.config.aop;

import book.model.Book;
import book.util.CustomResponse;
import book.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class BookAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Book book = null;

        if (methodSignature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Book) {
                    book = (Book) arg;
                    log.info("Попытка добавить книгу с названием {}", book.getTitle());
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        assert book != null;
        log.info("Книга с названием {} добавлена", book.getTitle());
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String author = null;
        Long id = 0L;

        if (methodSignature.getName().equals("findAllBooks")) {
            log.info("Попытка получить все книги");
        } else if (methodSignature.getName().equals("findByAuthorName")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof String) {
                    author = (String) arg;
                    log.info("Пытаемся получить автора {}", author);
                }
            }
        }

        if (methodSignature.getName().equals("findAllBooks")) {
            log.info("Попытка получить все книги");
        } else if (methodSignature.getName().equals("findOne")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Long) {
                    id = (Long) arg;
                    log.info("Пытаемся получить книгу с id {}", id);
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        if (methodSignature.getName().equals("findAllBooks")) {
            log.info("Все книги получены");
        } else if (methodSignature.getName().equals("findByAuthorName")) {
            log.info("Книга автора {} получена", author);
        }else if (methodSignature.getName().equals("findOne")) {
            log.info("Книга c id {} получена", id);
        }

        return result;
    }

    @Around("Pointcuts.allUpdateMethods()")
    public Object aroundUpdateAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Book book = null;

        if (methodSignature.getName().equals("update")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object arg : arguments) {
                if (arg instanceof Book) {
                    book = (Book) arg;
                    log.info("Попытка обновить книгу с названием {}", book.getTitle());
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        assert book != null;
        log.info("Книга с названием {} обновлена", book.getTitle());
        return result;
    }

}
