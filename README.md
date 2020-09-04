# Implementing the @Before Advice
Typically, when we implement security using AOP, we would want to intercept the call to the method and apply your check. This is typically done using the @Before advice.

An implementation is shown below:
```java
@Aspect
@Configuration
public class UserAccessAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //What kind of method calls I would intercept
    //execution(* PACKAGE.*.*(..))
    //Weaving & Weaver
    @Before("execution(* com.srikanth.aop.dao.*.*(..))")
    public void before(JoinPoint joinPoint) {
        //Advice
        logger.info(" Check for user access ");
        logger.info(" Allowed execution for {}", joinPoint);
    }
}
```
Notes

* @Aspect: indicates that this is an Aspect
* @Configuration: indicates that this file contains a Spring Bean Configuration for an Aspect.
* @Before : We would want to execute the Aspect before the execution of the method
("execution(* com.srikanth.aop.dao.*.*(..))": This defines the pointcut. We would want to intercept all method calls made to any methods in package com.srikanth.aop.dao

When you run the unit test, you will see that, before executing the DAO method, the user access check code is executed:
```text
2020-09-04 11:13:56.233  INFO 18064 --- [           main] sAspect$$EnhancerBySpringCGLIB$$d2b832ee :  Check for user access 
2020-09-04 11:13:56.233  INFO 18064 --- [           main] sAspect$$EnhancerBySpringCGLIB$$d2b832ee :  Allowed execution for execution(String com.srikanth.aop.dao.Dao1.retrieveSomething())


2020-09-04 11:13:56.243  INFO 18064 --- [           main] sAspect$$EnhancerBySpringCGLIB$$d2b832ee :  Check for user access 
2020-09-04 11:13:56.244  INFO 18064 --- [           main] sAspect$$EnhancerBySpringCGLIB$$d2b832ee :  Allowed execution for execution(String com.srikanth.aop.dao.Dao2.retrieveSomething())
```

## What is Pointcut, Advice, Aspect, Join Point ?

Let’s spend some time understanding the AOP terminology.

* Pointcut: the expression used to define when a call to a method should be intercepted. In the above example, "execution(* com.srikanth.aop.dao.*.*(..))" is the pointcut.
* Advice: What do you want to do? An advice is the logic that you want to invoke when you intercept a method. In the above example, it is the code inside the before(JoinPoint joinPoint) method.
* Aspect: A combination of defining when you want to intercept a method call (Pointcut) and what to do (Advice) is called an Aspect.
* Join Point: When the code is executed and the condition for pointcut is met, the advice is executed. The Join Point is a specific execution instance of an advice.
* Weaver: Weaver is the framework that implements AOP — AspectJ or Spring AOP.

## Using the @After, @AfterReturning, and @AfterThrowing Advices
Let’s now the other interception options AOP provides.
```text
* @After: executed in two situations — when a method executes successfully or it throws an exception.
* @AfterReturning: executed only when a method executes successfully.
* @AfterThrowing: executed only when a method throws an exception.
* @Around : Advice that surrounds a join point such as a method invocation. git repo example :https://github.com/srikanthdannarapu/spring-boot-apo2
```
Let’s create a simple Aspect with a couple of these variations.
```java
@Aspect
@Configuration
public class AfterAopAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//TODO
	@AfterReturning(value = "execution(* com.srikanth.aop.service.*.*(..))",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {}", joinPoint, result);
	}
	
	@After(value = "execution(* com.srikanth.aop.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}
}
```
