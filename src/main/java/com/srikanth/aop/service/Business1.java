package com.srikanth.aop.service;

import com.srikanth.aop.dao.Dao1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Business1 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Dao1 dao1;
    public String calculateSomething() {
        //Business Logic
        String value = dao1.retrieveSomething();
        logger.info("In Business 1 service class calculateSomething method - {}", value);
        return value;
    }
}