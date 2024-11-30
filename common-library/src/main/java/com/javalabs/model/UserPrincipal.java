package com.javalabs.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPrincipal implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String organizationId;
	private String tenantId;
	private String token;
}
