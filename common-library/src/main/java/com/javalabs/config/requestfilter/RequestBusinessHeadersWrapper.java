package com.javalabs.config.requestfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.javalabs.config.ApplicationProperties;
import com.javalabs.config.event.AbstractEvent;
import com.javalabs.config.event.Trace;
import com.javalabs.util.CommonConstants;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Class responsible for wrapping original request header.to be able to retrieve
 * business header when ever it's needed
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestBusinessHeadersWrapper {
	public static final String NODE_NAME_APPLICATION = "application";
	public static final String NODE_NAME_BUSINESS_INTERACTION_ID = "businessInteractionId";
	public static final String NODE_NAME_BUSINESS_INTERACTION_TYPE = "businessInteractionType";
	public static final String NODE_NAME_CHANNEL = "channel";
	public static final String NODE_NAME_COMPANY = "company";
	public static final String NODE_NAME_SESSION_ID = "sessionId";
	public static final String NODE_NAME_SITE = "site";
	public static final String NODE_NAME_TRANSACTION_ID = "transactionId";

	private static final InheritableThreadLocal<Map<String, String>> businessHeaders = new InheritableThreadLocal<>();
	private final ApplicationProperties applicationProperties;

	public static String getHeader(String name) {
		if (Objects.nonNull(businessHeaders.get())) {
			return businessHeaders.get().get(name);
		}
		return null;
	}

	public static List<String> getBusinessHeaders() {
		List<String> keysAndValue = new ArrayList<>();
		if (Objects.nonNull(businessHeaders.get())) {
			businessHeaders.get().keySet().forEach(s -> keysAndValue
					.add(s + "=" + (businessHeaders.get().get(s) == null ? "" : businessHeaders.get().get(s))));
		}
		return keysAndValue;
	}

	public void extractBusinessHeaders(HttpServletRequest httpServletRequest) {
		businessHeaders.set(new HashMap<>());
		businessHeaders.get().put(applicationProperties.getTransactionId(),
				httpServletRequest.getHeader(applicationProperties.getTransactionId()));
		businessHeaders.get().put(applicationProperties.getBusinessInteractionId(),
				httpServletRequest.getHeader(applicationProperties.getBusinessInteractionId()));
		businessHeaders.get().put(applicationProperties.getBusinessInteractionType(),
				httpServletRequest.getHeader(applicationProperties.getBusinessInteractionType()));
		businessHeaders.get().put(applicationProperties.getSite(),
				httpServletRequest.getHeader(applicationProperties.getSite()));
		businessHeaders.get().put(applicationProperties.getCompany(),
				httpServletRequest.getHeader(applicationProperties.getCompany()));
		businessHeaders.get().put(applicationProperties.getChannel(),
				httpServletRequest.getHeader(applicationProperties.getChannel()));
		businessHeaders.get().put(applicationProperties.getSessionId(),
				httpServletRequest.getHeader(applicationProperties.getSessionId()));
		businessHeaders.get().put(applicationProperties.getApplication(),
				httpServletRequest.getHeader(applicationProperties.getApplication()));
	}

	public void extractBusinessHeaders(AbstractEvent abstractEvent) {
		businessHeaders.set(new HashMap<>());
		if (!Optional.ofNullable(abstractEvent).map(AbstractEvent::getTrace).isPresent()) {
			log.debug("extractBusinessHeaders - abstractEvent or trace is not present");
			return;
		}
		Trace trace = abstractEvent.getTrace();
		businessHeaders.get().put(applicationProperties.getTransactionId(), trace.getTransactionId());
		businessHeaders.get().put(applicationProperties.getBusinessInteractionId(), trace.getBusinessInteractionId());
		businessHeaders.get().put(applicationProperties.getBusinessInteractionType(),
				trace.getBusinessInteractionType());
		businessHeaders.get().put(applicationProperties.getSite(), trace.getSite());
		businessHeaders.get().put(applicationProperties.getCompany(), trace.getCompany());
		businessHeaders.get().put(applicationProperties.getChannel(), trace.getChannel());
		businessHeaders.get().put(applicationProperties.getSessionId(), trace.getSessionId());
		businessHeaders.get().put(applicationProperties.getApplication(), trace.getApplication());
	}

	public void installBusinessHeaders(JsonNode jsonNode) {
		if (!Optional.ofNullable(jsonNode).isPresent()) {
			log.debug("jsonNode is not present");
			return;
		}
		final Map<String, String> headers = new HashMap<>();
		headers.put(applicationProperties.getApplication(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_APPLICATION).asText());
		headers.put(applicationProperties.getBusinessInteractionId(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_BUSINESS_INTERACTION_ID).asText());
		headers.put(applicationProperties.getBusinessInteractionType(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_BUSINESS_INTERACTION_TYPE).asText());
		headers.put(applicationProperties.getChannel(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_CHANNEL).asText());
		headers.put(applicationProperties.getCompany(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_COMPANY).asText());
		headers.put(applicationProperties.getSessionId(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_SESSION_ID).asText());
		headers.put(applicationProperties.getSite(), jsonNode.at(CommonConstants.SLASH + NODE_NAME_SITE).asText());
		headers.put(applicationProperties.getTransactionId(),
				jsonNode.at(CommonConstants.SLASH + NODE_NAME_TRANSACTION_ID).asText());

		businessHeaders.set(headers);
	}

	public void clear() {
		businessHeaders.remove();
	}
}