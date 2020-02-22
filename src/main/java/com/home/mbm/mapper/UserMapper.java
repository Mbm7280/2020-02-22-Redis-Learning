package com.home.mbm.mapper;

import com.home.mbm.model.User;

import java.util.List;

/**
* @Package: com.home.mbm.mapper
* @ClassName: UserMapper
* @Description: mapper
 *      mapper 接口
* @Author: mbm
* @date: 2020/2/4 20:01
* @Version: 1.0
*/
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}