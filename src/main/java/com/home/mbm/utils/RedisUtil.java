package com.home.mbm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
* @Package: com.home.mbm.utils
* @ClassName: RedisUtil
* @Description: util
 *      jedis
* @Author: mbm
* @date: 2020/2/4 20:29
* @Version: 1.0
*/
@Component
public class RedisUtil {

    // DI 注入 JedisCluster 对象，用于操作 redis
    @Autowired
    JedisCluster jedisCluster;

    /**
     * @Author: mbm
     * @ClassName: RedisUtil
     * @MethodName: get
     * @Param:  [key]
     * @return: java.lang.String
     * @Description: Description
     * @date: 2020/2/4 20:31
     * @Version: 1.0
     */
    public String get(String key){
        return jedisCluster.get(key);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisUtil
     * @MethodName: set
     * @Param:  [key, value]
     * @return: java.lang.String
     * @Description: Description
     * @date: 2020/2/4 20:30
     * @Version: 1.0
     */
    public String set(String key,String value){
        return jedisCluster.set(key, value);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisUtil
     * @MethodName: del
     * @Param:  [key]
     * @return: java.lang.Long
     * @Description: Description
     * @date: 2020/2/4 20:30
     * @Version: 1.0
     */
    public Long del(String key){

        return jedisCluster.del(key);
    }

    /**
     * @Author: mbm
     * @ClassName: RedisUtil
     * @MethodName: expire
     * @Param:  [key, seconds]
     * @return: java.lang.Long
     * @Description: Description
     * @date: 2020/2/4 20:30
     * @Version: 1.0
     */
    public Long expire(String key,Integer seconds){

        return jedisCluster.expire(key,seconds);
    }

}
