/*
 * FileName: FlowRulesInitFunc.java
 * Author:   zzw
 * Date:     2018年10月13日
 * Description:
 */
package com.zzw.web;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzw.web.config.NacosDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author zzw
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本]（可选）
 */
public class FlowRulesInitFunc implements InitFunc {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init() throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        String port = System.getProperty("csp.sentinel.api.port","8720");
        String appName = System.getProperty("project.name");
        final String groupId = "com.chezhibao.nacos.flow." + appName;
        final String dataId = appName + ".flow." + hostAddress + ":" + port;

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<List<FlowRule>>(groupId, dataId, new Converter<String, List<FlowRule>>() {
            @Override
            public List<FlowRule> convert(String source) {
                ConcurrentHashMap<String, FlowRuleEntity> map = JSON.parseObject(source, new TypeReference<ConcurrentHashMap<String, FlowRuleEntity>>() {});
                List<FlowRule> rules = new ArrayList<FlowRule>();
                if(map != null){
                    Iterator<Map.Entry<String, FlowRuleEntity>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, FlowRuleEntity> next = iterator.next();
                        rules.add(next.getValue().toFlowRule());
                    }
                }
                return rules;
            }
        });
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        logger.info("FlowRulesInitFunc init");
    }
}
