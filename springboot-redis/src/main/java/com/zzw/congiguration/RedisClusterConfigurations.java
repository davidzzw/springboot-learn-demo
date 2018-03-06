package com.zzw.congiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zzw
 * @see
 * @since 2018/3/6
 */
@Configuration
public class RedisClusterConfigurations {

    @Bean
    public JedisCluster jedisCluster(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(2);
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("10.96.5.183",9001));
        jedisClusterNodes.add(new HostAndPort("10.96.5.183",9002));
        jedisClusterNodes.add(new HostAndPort("10.96.5.183",9003));
        JedisCluster jc = new JedisCluster(jedisClusterNodes, 5000, 10, config);
        return jc;
    }

    @Bean
    public RedisClusterConfiguration getClusterConfiguration() {
        Map<String, Object> source = new HashMap<String, Object>();
        Set<RedisNode> clusterNodes = new HashSet<>();
        source.put("spring.redis.cluster.nodes", clusterNodes);
        source.put("spring.redis.cluster.timeout", "");
        source.put("spring.redis.cluster.max-redirects", "");
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }

    @Bean
    public JedisConnectionFactory getConnectionFactory() {
        return new JedisConnectionFactory(getClusterConfiguration());
    }

    @Bean
    public JedisClusterConnection getJedisClusterConnection() {
        return (JedisClusterConnection) getConnectionFactory().getConnection();
    }

    @Bean
    public RedisTemplate getRedisTemplate() {
        RedisTemplate clusterTemplate = new RedisTemplate();
        clusterTemplate.setConnectionFactory(getConnectionFactory());
        clusterTemplate.setKeySerializer(new StringRedisSerializer());
        clusterTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        return clusterTemplate;
    }
}
