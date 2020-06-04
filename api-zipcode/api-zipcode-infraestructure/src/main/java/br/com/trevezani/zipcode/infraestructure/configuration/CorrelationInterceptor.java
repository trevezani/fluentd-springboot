package br.com.trevezani.zipcode.infraestructure.configuration;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class CorrelationInterceptor extends HandlerInterceptorAdapter {
	private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		final String correlationId = getCorrelationIdFromHeader(request);
		MDC.put("correlationId", correlationId);

		try {
			MDC.put("path", request.getRequestURI().substring(request.getContextPath().length()));
		} catch (Exception e) {
			MDC.put("path", "NA");
		}
		
		return true;
	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
		MDC.remove("correlationId");
		MDC.remove("path");
	}

	private String getCorrelationIdFromHeader(final HttpServletRequest request) {
		String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
		
		if (correlationId == null || correlationId.isEmpty() || correlationId.equals("na")) {
			correlationId = generateUniqueCorrelationId();
		}
		
		return correlationId;
	}

	private String generateUniqueCorrelationId() {
		return UUID.randomUUID().toString();
	}
}
