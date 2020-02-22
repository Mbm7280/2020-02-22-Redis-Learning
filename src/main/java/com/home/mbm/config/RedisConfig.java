package com.home.mbm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RedisConfig {

    @Autowired
    RedisProperries redisProperties;

    // 声明成 Bean
    @Bean
    public JedisCluster jedisCluster(){
        // 获取所有节点
        String nodes = redisProperties.getNodes();
        // 分割成单独的节点
        String[] ipsAndPorts = nodes.split(",");
        // set集合
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        // 循环 节点
        for (String ipAndPort:ipsAndPorts) {
            // 将 ip 和 port 分开
            String[] hostPort = ipAndPort.split(":");
            // 创建 HostAndPort 对象,并且将 IP 和 Port 传入
            HostAndPort hostAndPort= new HostAndPort(hostPort[0],Integer.parseInt(hostPort[1]));
            // 放入 set 集合
            hostAndPortSet.add(hostAndPort);
        }
        // 将 节点信息传入， 同时传入 最大连接数， 超时连接设置
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet,redisProperties.getCommandTimeout(),Integer.parseInt(redisProperties.getMaxAttempts()));
        // return 对象
        return jedisCluster;
    }

}
