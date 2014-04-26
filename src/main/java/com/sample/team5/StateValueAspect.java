package com.sample.team5;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

@Aspect
public class StateValueAspect {
	
	ApplicationContext appContext = AppContext.getApplicationContext();	
	private static final Logger logger = LoggerFactory.getLogger(StateValueAspect.class);	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

//	@Pointcut("execution(* GameController.getPage(..))")
//	public void getControllerPointcut(){
//		
//	}
	
//	@Around("getControllerPointcut()")
//	public void selectController(ProceedingJoinPoint joinPoint) throws Throwable {		
//		HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
//		HttpSession session = (HttpSession) joinPoint.getArgs()[1];
//		Model model = (Model) joinPoint.getArgs()[2];
//		System.out.println("@Before annotation: GameController");
//		return joinPoint.proceed();
//	}
	
}
