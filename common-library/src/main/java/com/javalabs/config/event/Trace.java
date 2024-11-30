package com.javalabs.config.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Trace {
	private String site;
	private String company;
	private String channel;
	private String application;
	private String sessionId;
	private String businessInteractionId;
	private String transactionId;
	private String businessInteractionType;

}