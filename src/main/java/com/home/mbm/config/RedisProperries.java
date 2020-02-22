package com.home.mbm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @Package: com.home.mbm.config
* @ClassName: RedisProperries
* @Description: configuration
* @Author: mbm
* @date: 2020/2/4 20:11
* @Version: 1.0
*/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperries {

    private String nodes;
    private String maxAttempts;
    private Integer commandTimeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(String maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Integer getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(Integer commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
