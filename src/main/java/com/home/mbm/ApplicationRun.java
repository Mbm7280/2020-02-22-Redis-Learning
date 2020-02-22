package com.home.mbm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* @Package: com.home.mbm
* @ClassName: ApplicationRun
* @Description: run
 *      启动类
* @Author: mbm
* @date: 2020/2/4 20:02
* @Version: 1.0
*/
@SpringBootApplication
@MapperScan("com.home.mbm.mapper")
public class ApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);
    }

}
