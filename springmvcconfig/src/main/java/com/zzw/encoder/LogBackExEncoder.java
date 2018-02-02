package com.zzw.encoder;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zzw.convert.ThreadNumConverter;

import java.io.IOException;

/**
 * @author zzw
 * @see
 * @since 2018/2/2
 */
public class LogBackExEncoder extends PatternLayoutEncoder {

    static {
        PatternLayout.defaultConverterMap.put("T", ThreadNumConverter.class.getName());
        PatternLayout.defaultConverterMap.put("threadNum", ThreadNumConverter.class.getName());
    }

    @Override
    public byte[] encode(ILoggingEvent event) {
        return super.encode(event);
    }
}
