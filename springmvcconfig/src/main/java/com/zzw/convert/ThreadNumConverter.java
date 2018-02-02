package com.zzw.convert;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
public class ThreadNumConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return String.valueOf(Thread.currentThread().getId());
    }
}
