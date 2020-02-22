package com.home.mbm.service;

import com.home.mbm.mapper.UserMapper;
import com.home.mbm.model.User;
import com.home.mbm.status.EnumStatus;
import com.home.mbm.utils.JSONUtil;
import com.home.mbm.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.home.mbm.status.CodeStatue.*;

/**
* @Package: com.home.mbm.service
* @ClassName: UserService
* @Description: service
 *      service层
* @Author: mbm
* @date: 2020/2/4 20:32
* @Version: 1.0
*/
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    RedisUtil redisUtil;
    
    @Value("${user_key}")
    private String userKey;
    
    public static Map<String,Object> resultMap = new HashMap<String, Object>();

    /**
     * @Author: mbm
     * @ClassName: UserService
     * @MethodName: selectAll
     * @Param:  []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 20:55
     * @Version: 1.0
     */
    public Map<String,Object> selectAll(){

        // 将 redis 数据取出
        String redisData = redisUtil.get("userKey");
        // 将 mysql 数据查询出来
        List<User> userListFromMysql = userMapper.selectAll();
        // 对 redis 数据进行判断
        if(null == redisData || "".equals(redisData)){
            // redis 中无数据，查询出 mysql 数据，存入
            // 对 mysql 数据进行判断
            if(userListFromMysql.size() > 0){
                // 将 List 转换为 String
                String mysqlDataToString = JSONUtil.toJsonString(userListFromMysql);
                String isSuccess = redisUtil.set(userKey, mysqlDataToString);
                // 对存入结果进行判断
                if("OK".equals(isSuccess.toUpperCase())){
                    // 存入成功，返回数据
                    resultMap.put(CODE, EnumStatus.SUCCESS.getCode());
                    resultMap.put(DATA,userListFromMysql);
                } else{
                    // 存入失败
                    resultMap.put(CODE, EnumStatus.SUCCESS.getCode());
                    resultMap.put(DATA,userListFromMysql);
                }
            } else{
                // mysql 中无数据
                resultMap.put(CODE,EnumStatus.FALIED.getCode());
                resultMap.put(MSG,EnumStatus.FALIED.getMsg());
            }
        } else{
            // redis 中有数据, 为了防止脏数据和mysql 数据库中数据不匹配,进行比对
            // 将 redis 转换成 List
            List<User> userListFromRedis = JSONUtil.toList(redisData, User.class);
            if(userListFromMysql.size() == userListFromRedis.size()){
                // 数据匹配
                resultMap.put(CODE, EnumStatus.SUCCESS.getCode());
                resultMap.put(DATA,userListFromRedis);
            } else{
                // 数据不匹配
                resultMap.put(CODE, EnumStatus.SUCCESS.getCode());
                resultMap.put(DATA,userListFromMysql);
            }
        }
        return resultMap;
    }


    /**
     * @Author: mbm
     * @ClassName: UserService
     * @MethodName: deleteUser
     * @Param:  [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 21:01
     * @Version: 1.0
     */
    public Map<String,Object> deleteUser(Long id){

        //删除结果
        int i = userMapper.deleteByPrimaryKey(id);

        if(i>0){
            //删除成功,清空redis缓存
            Long result =  redisUtil.del(userKey);
            if(result>0){
                //清空数据成功,返回resultMap
                resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
            } else{
                //清空数据失败,再多清空一次,-----------查询直接走mysql,不需要我的查询有数据比对,可以直接返回成功状态码
                redisUtil.del(userKey);
                resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
            }
        } else{
            //删除失败,返回状态码
            resultMap.put(CODE,EnumStatus.FALIED.getCode());
            resultMap.put(MSG,EnumStatus.FALIED.getMsg());
        }
        return resultMap;
    }


    /**
     * @Author: mbm
     * @ClassName: UserService
     * @MethodName: selectByID
     * @Param:  [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 21:19
     * @Version: 1.0
     */
    public Map<String,Object> selectByID(Long id){

        User user = userMapper.selectByPrimaryKey(id);
        // 对 user 是否为空进行判断
        if(null != user || !"".equals(user)){
            // user 不为空
            resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
            resultMap.put(DATA,user);
        } else{
            resultMap.put(CODE,EnumStatus.FALIED.getCode());
            resultMap.put(MSG,EnumStatus.FALIED.getMsg());
        }
        return resultMap;
    }


    /**
     * @Author: mbm
     * @ClassName: UserService
     * @MethodName: insertAndUpdateUser
     * @Param:  [user]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 21:30
     * @Version: 1.0
     */
    public Map<String,Object> insertAndUpdateUser(User user){

        // 先对 user 进行判断 选择方法
        Long userId = user.getId();
        if(userId != null){
            // 修改方法
            int upResult = userMapper.updateByPrimaryKey(user);
            // 对是否 修改成功 进行判断
            if(upResult > 0){
                // 修改成功
                // 清空 redis 缓存数据
                Long delResult = redisUtil.del(userKey);
                if(delResult > 0){
                    // 清空成功
                    resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                    resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
                } else{
                    // 清空失败 但是 由于查询那边有 进行数据比对
                    resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                    resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
                }
            } else{
                // 修改失败
                resultMap.put(CODE,EnumStatus.FALIED.getCode());
                resultMap.put(MSG,EnumStatus.FALIED.getMsg());
            }
        } else{
            // 新增方法
            int insertResult = userMapper.insert(user);
            // 对是否 新增成功 进行判断
            if(insertResult > 0){
                // 新增成功
                // 清空 redis 缓存数据
                Long delResult = redisUtil.del(userKey);
                if(delResult > 0){
                    // 清空成功
                    resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                    resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
                } else{
                    // 清空失败 但是 由于查询那边有 进行数据比对
                    resultMap.put(CODE,EnumStatus.SUCCESS.getCode());
                    resultMap.put(MSG,EnumStatus.SUCCESS.getMsg());
                }
            } else{
                // 新增失败
                resultMap.put(CODE,EnumStatus.FALIED.getCode());
                resultMap.put(MSG,EnumStatus.FALIED.getMsg());
            }
        }
        return resultMap;
    }
}
