package book.config.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut(value = "execution(* book.service.Impl.BookServiceImpl.find*(..))")
    public void allGetMethods() {}

    @Pointcut(value = "execution(* book.service.Impl.BookServiceImpl.add*(..))")
    public void allAddMethods() {}

    @Pointcut("execution(* book.service.Impl.BookServiceImpl.update*(..))")
    public void allUpdateMethods() {}
}
