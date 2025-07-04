package co.ptm.springmultithreading;

import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class SpringMultiThreadingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMultiThreadingApplication.class, args);
    }

    @Bean
    TaskDecorator mdcCopyTaskDecorator(){
        return new PtmMDCContextTaskDecorator();
    }

    @Override
    public void run(String... args) throws Exception {
        MDC.put("hero","hero200");
    }

}
