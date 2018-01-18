package com.zzw.controller;

import com.zzw.util.ApplicationContextUtil;
import com.zzw.vo.BeanDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zzw
 * @see
 * @since 2018/1/17
 */
@RestController
public class BeansController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping
    public List<BeanDetails> beans(){
        return ApplicationContextUtil.getBeanDetails(context);
    }
}
