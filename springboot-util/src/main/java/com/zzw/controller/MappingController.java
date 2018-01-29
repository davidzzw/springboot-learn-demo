package com.zzw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zzw
 * @see
 * @since 2018/1/29
 */
@RestController
public class MappingController {

    @Autowired
    private RequestMappingHandlerMapping mappingHandlerMapping;

    @GetMapping("/mapping")
    public List<HandlerMethod> mapping(){
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mappingHandlerMapping.getHandlerMethods();
        List<HandlerMethod> result = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry:handlerMethods.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
