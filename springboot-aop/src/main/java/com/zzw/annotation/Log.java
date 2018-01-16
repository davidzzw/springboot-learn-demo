package com.zzw.annotation;

import java.lang.annotation.*;

/**
 * @author zzw
 * @see
 * @since 2018/1/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
}
