package com.school.mongolian.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;


@Configuration
//@Import(FastDfsClientConfig.class)
// 解决jmx重复注册bean的问题
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDfsClientConfig {

}
