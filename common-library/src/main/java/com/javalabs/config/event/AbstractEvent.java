package com.javalabs.config.event;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javalabs.data.AccessPolicyConstraintData;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * Skeletal definition for events that are defined across Orbitant
 * microservices.
 */
@Getter
@Setter
public abstract class AbstractEvent {
	@JsonProperty("eventId")
	private String eventId = null;

	@Valid
	@JsonProperty("eventTime")
	private OffsetDateTime eventTime = null;

	@JsonProperty("eventType")
	private String eventType = null;

	@JsonProperty("correlationId")
	private String correlationId = null;

	@JsonProperty("domain")
	private String domain = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("priority")
	private String priority = null;

	@Valid
	@JsonProperty("timeOccurred")
	private OffsetDateTime timeOccurred = null;

	private List<? extends AccessPolicyConstraintData> accessPolicyConstraint;

	@JsonProperty("auth")
	private Auth auth;

	@JsonProperty("trace")
	private Trace trace;

	@JsonProperty("originIp")
	private String originIp;
	
	@JsonProperty("objectName")
	private String objectName;
}