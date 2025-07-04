package co.ptm.springmultithreading;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PtmSchedular {

@Scheduled(fixedRate = 4*1000)
    void gret(){
    log.info("hello from schedular");
    log.info("MDC hero: {}", MDC.get("hero"));
}
}
