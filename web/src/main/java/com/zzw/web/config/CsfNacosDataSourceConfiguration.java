package com.zzw.web.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CsfNacosDataSourceConfiguration {

    private static Logger logger = LoggerFactory.getLogger(CsfNacosDataSourceConfiguration.class);

    static {
        try {
            loadFlowRules();
            loadDegradeRules();
            loadSystemRules();
            logger.info("CsfNacosDataSourceConfiguration init success");
        } catch (UnknownHostException e) {
            logger.error("CsfNacosDataSourceConfiguration init" + e);
        }
    }

    private static void loadFlowRules() throws UnknownHostException {
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
    }

    private static void loadDegradeRules() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        String port = System.getProperty("csp.sentinel.api.port","8720");
        String appName = System.getProperty("project.name");
        final String groupId = "com.chezhibao.nacos.degrade." + appName;
        final String dataId = appName + ".degrade." + hostAddress + ":" + port;

        ReadableDataSource<String, List<DegradeRule>> flowRuleDataSource = new NacosDataSource<List<DegradeRule>>(groupId, dataId, new Converter<String, List<DegradeRule>>() {
            @Override
            public List<DegradeRule> convert(String source) {
                ConcurrentHashMap<String, DegradeRuleEntity> map = JSON.parseObject(source, new TypeReference<ConcurrentHashMap<String, DegradeRuleEntity>>() {});
                List<DegradeRule> rules = new ArrayList<DegradeRule>();
                if(map != null){
                    Iterator<Map.Entry<String, DegradeRuleEntity>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, DegradeRuleEntity> next = iterator.next();
                        rules.add(next.getValue().toDegradeRule());
                    }
                }
                return rules;
            }
        });
        DegradeRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private static void loadSystemRules() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        String port = System.getProperty("csp.sentinel.api.port","8720");
        String appName = System.getProperty("project.name");
        final String groupId = "com.chezhibao.nacos.system." + appName;
        final String dataId = appName + ".system." + hostAddress + ":" + port;

        ReadableDataSource<String, List<SystemRule>> flowRuleDataSource = new NacosDataSource<List<SystemRule>>(groupId, dataId, new Converter<String, List<SystemRule>>() {
            @Override
            public List<SystemRule> convert(String source) {
                ConcurrentHashMap<String, SystemRuleEntity> map = JSON.parseObject(source, new TypeReference<ConcurrentHashMap<String, SystemRuleEntity>>() {});
                List<SystemRule> rules = new ArrayList<SystemRule>();
                if(map != null){
                    Iterator<Map.Entry<String, SystemRuleEntity>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, SystemRuleEntity> next = iterator.next();
                        rules.add(next.getValue().toSystemRule());
                    }
                }
                return rules;
            }
        });
        SystemRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
