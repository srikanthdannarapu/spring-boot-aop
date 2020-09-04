package com.srikanth.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootAopApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringbootAopApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(SpringbootAopApplication.class, args);
		for (String name: applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
