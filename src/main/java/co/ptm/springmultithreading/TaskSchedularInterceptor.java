package co.ptm.springmultithreading;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskSchedularInterceptor {

	@Autowired
	@Qualifier("mdcCopyTaskDecoratorSchedular")
	private TaskDecorator taskDecorator;

	@Pointcut("within(org.springframework.scheduling.TaskScheduler+)")
	void taskSchedular() {
		// empty
	}

	@Around("taskSchedular() && args(run,..)")
	Object around(ProceedingJoinPoint pjp, Runnable run) throws Throwable {
		Object[] args = pjp.getArgs();

		if (!(run instanceof PtmMDCContextTaskDecoratorSchedular.MDCContextTaskDecorator))
			args[0] = taskDecorator.decorate(run);

		return pjp.proceed(args);
	}

}
