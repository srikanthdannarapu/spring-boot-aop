package com.srikanth.aop.dao;

import org.springframework.stereotype.Repository;
@Repository
public class Dao2 {
    public String retrieveSomething() {
        return "some dummy String from Dao2";
    }
}