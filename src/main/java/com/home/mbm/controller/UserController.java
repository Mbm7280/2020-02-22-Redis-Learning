package com.home.mbm.controller;

import com.home.mbm.model.User;
import com.home.mbm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
* @Package: com.home.mbm.controller
* @ClassName: UserController
* @Description:
 *      1.0 编写 redis    20200204
 *      2.0 添加前端页面  20200208
* @Author: mbm
* @date: 2020/2/8 20:34
* @Version: 1.0
*/
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @Author: mbm
     * @ClassName: UserController
     * @MethodName: turnToUserPage
     * @Param:  []
     * @return: java.lang.String
     * @Description: Description
     * @date: 2020/2/8 20:33
     * @Version: 2.0
     */
    @RequestMapping("/")
    public String turnToUserPage(){
        return "user";
    }


    /**
     * @Author: mbm
     * @ClassName: UserController
     * @MethodName: selectAll
     * @Param:  []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 21:32
     * @Version: 1.0
     */
    @ResponseBody
    @RequestMapping("/all")
    public Map<String,Object> selectAll(){
        return userService.selectAll();
    }

    /**
     * @Author: mbm
     * @ClassName: UserController
     * @MethodName: selectById
     * @Param:  [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/4 21:33
     * @Version: 1.0
     */
    @ResponseBody
    @RequestMapping("/one")
    public Map<String,Object> selectById(Long id){
        return userService.selectByID(id);
    }


    /**
     * @Author: mbm
     * @ClassName: UserController
     * @MethodName: deleteUser
     * @Param:  [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/8 19:07
     * @Version: 2.0
     */
    @ResponseBody
    @RequestMapping("/del")
    public Map<String,Object> deleteUser(Long id){
        return userService.deleteUser(id);
    }

    /**
     * @Author: mbm
     * @ClassName: UserController
     * @MethodName: insertAndUpdateUser
     * @Param:  [user]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Description: Description
     * @date: 2020/2/8 20:33
     * @Version: 2.0
     */
    @ResponseBody
    @RequestMapping("/inAndUp")
    public Map<String,Object> insertAndUpdateUser(@RequestBody User user){

        return userService.insertAndUpdateUser(user);
    }




}
