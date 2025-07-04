package co.ptm.springmultithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class SpringMultiThreadingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMultiThreadingApplication.class, args);
	}

	@Primary
	@Bean
	TaskDecorator mdcCopyTaskDecorator() {
		return new PtmMDCContextTaskDecorator();
	}

	@Bean
	TaskDecorator mdcCopyTaskDecoratorSchedular() {
		return new PtmMDCContextTaskDecoratorSchedular();
	}

}
