package com.school.mongolian.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogHelper {
    private static final Logger LOG = LoggerFactory.getLogger(LogHelper.class);

    public void helpLog(){
        LOG.debug("打印debug级别");
        LOG.info("打印info级别");
        LOG.warn("打印warn级别");
        LOG.error("打印error级别");
    }

}
