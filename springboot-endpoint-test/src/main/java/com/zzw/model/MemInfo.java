package com.zzw.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author zzw
 * @see
 * @since 2018/1/16
 */
public class MemInfo {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date recordTime;//记录时间.

    private long maxMemory;//能构从操作系统那里挖到的最大的内存，以字节为单位
    private long totalMemory;//进程当时所占用的所有 内存
    public Date getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    public long getMaxMemory() {
        return maxMemory;
    }
    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }
    public long getTotalMemory() {
        return totalMemory;
    }
    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }
}
