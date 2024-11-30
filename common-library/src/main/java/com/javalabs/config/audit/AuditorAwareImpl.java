package com.javalabs.config.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.javalabs.authentication.AuthenticationUserPrincipalStore;

import lombok.RequiredArgsConstructor;

@Component("auditorProvider")
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

	private final AuthenticationUserPrincipalStore authenticationUserPrincipalStore;

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(authenticationUserPrincipalStore.getUserPrincipal().getUserName());
	}
}