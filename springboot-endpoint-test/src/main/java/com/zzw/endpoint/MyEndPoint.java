package com.zzw.endpoint;

import com.zzw.model.MemInfo;
import org.springframework.boot.actuate.endpoint.Endpoint;

import java.util.Date;

/**
 * @author zzw
 * @see
 * @since 2018/1/16
 */
public class MyEndPoint implements Endpoint<MemInfo> {
    @Override
    public String getId() {
        return "myendpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return true;
    }

    @Override
    public MemInfo invoke() {
        MemInfo memInfo = new MemInfo();
        Runtime runtime = Runtime.getRuntime();

        memInfo.setRecordTime(new Date());
        memInfo.setMaxMemory(runtime.maxMemory());
        memInfo.setTotalMemory(runtime.totalMemory());
        return memInfo;
    }
}
