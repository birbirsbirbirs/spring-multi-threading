package co.ptm.springmultithreading;

import io.opentelemetry.context.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/hero")
public class HeroController {

	private final HeroService heroService;

	@GetMapping
	public Hero getHero() throws ExecutionException, InterruptedException {
		MDC.put("hero", "hero100");
		log.info("{}", Context.current().toString());
		for (int i = 0; i < 10; i++) {
			heroService.getHero();
		}
		return heroService.getHero().get();
	}

	@GetMapping("/hero")
	public Hero getHeroHero() {
		Hero hero = heroService.getHeroHero();
		log.info("from controller");
		return hero;
	}

}
