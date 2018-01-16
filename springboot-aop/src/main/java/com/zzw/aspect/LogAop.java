package com.zzw.aspect;

import com.zzw.util.ClassUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author zzw
 * @see
 * @since 2018/1/16
 */
@Component  //声明组件
@Aspect //  声明切面
@ComponentScan  //组件自动扫描
@EnableAspectJAutoProxy //spring自动切换JDK动态代理和CGLIB
public class LogAop {

    /**
     *自定义日志
     */
    private Logger logger = LoggerFactory.getLogger(LogAop.class);

    /**
     * 打印类method的名称以及参数
     * @param point 切面
     */
    @Pointcut("@annotation(com.zzw.annotation.Log)))")
    public void controllerMethodPointcut(){}

    /**
     * <li>Before       : 在方法执行前进行切面</li>
     * <li>execution    : 定义切面表达式</li>
     * <p>public * com.eparty.ccp.*.impl..*.*(..)
     *      <li>public :匹配所有目标类的public方法，不写则匹配所有访问权限</li>
     *      <li>第一个* :方法返回值类型，*代表所有类型 </li>
     *      <li>第二个* :包路径的通配符</li>
     *      <li>第三个..* :表示impl这个目录下所有的类，包括子目录的类</li>
     *      <li>第四个*(..) : *表示所有任意方法名,..表示任意参数</li>
     * </p>
     * @param point 切面
     */
   /* @Before("controllerMethodPointcut()")
    public void before(JoinPoint point) {
        this.printMethodParams(point);
    }*/

    @Around("controllerMethodPointcut()")
    public void advice(ProceedingJoinPoint joinPoint){
        logger.info("环绕通知之开始");
        try {
            ClassUtil.printMethodParams(joinPoint);
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        logger.info("环绕通知之结束");
    }
}
