package co.ptm.springmultithreading;

import io.opentelemetry.api.trace.Span;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

@Slf4j
public class PtmMDCContextTaskDecorator implements TaskDecorator {

	public static class MDCContextTaskDecorator implements Runnable {

		private final Runnable runnable;

		private final Map<String, String> contextMap;

		public MDCContextTaskDecorator(Runnable runnable) {
			super();
			this.runnable = runnable;
			MDC.put("batchId", Span.current().getSpanContext().getSpanId());
			this.contextMap = MDC.getCopyOfContextMap();
		}

		@Override
		public void run() {

			try {
				MDC.setContextMap(contextMap);
				runnable.run();
			}
			finally {
				MDC.clear();
			}
		}

	}

	@Override
	public Runnable decorate(Runnable runnable) {
		return new MDCContextTaskDecorator(runnable);
	}

}
