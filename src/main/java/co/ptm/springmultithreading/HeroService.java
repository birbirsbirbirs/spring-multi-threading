package co.ptm.springmultithreading;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeroService {

	@Async
	public CompletableFuture<Hero> getHero() {
		Hero hero = Hero.builder().name(UUID.randomUUID().toString()).power(UUID.randomUUID().toString()).build();
		log.info("returning hero: {}", hero);
		log.info("mdc for hero: {}", MDC.get("hero"));
		log.info("mdc for batchId: {}", MDC.get("batchId"));
		return CompletableFuture.completedFuture(hero);
	}

	@NewSpan
	public Hero getHeroHero() {
		Hero hero = Hero.builder().name(UUID.randomUUID().toString()).power(UUID.randomUUID().toString()).build();
		log.info("returning hero: {}", hero);
		return hero;
	}

}
