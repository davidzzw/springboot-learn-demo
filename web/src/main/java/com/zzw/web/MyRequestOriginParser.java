/*
 * FileName: MyRequestOriginParser.java
 * Author:   zzw
 * Date:     2018年10月15日
 * Description:
 */
package com.zzw.web;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String origin = httpServletRequest.getHeader("origin");
        return origin;
    }
}
