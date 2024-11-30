package com.javalabs.authentication;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.javalabs.model.UserPrincipal;
import com.javalabs.util.CommonConstants;

import jakarta.annotation.PreDestroy;

@Component
public class AuthenticationUserPrincipalStore {

	private static final ThreadLocal<UserPrincipal> userPrincipalThreadLocal = new ThreadLocal<>();

	public void saveUserPrincipal(UserPrincipal userPrincipal) {
		userPrincipalThreadLocal.set(userPrincipal);
	}

	public UserPrincipal getUserPrincipal() {
		if (Objects.isNull(userPrincipalThreadLocal.get())) {
			return UserPrincipal.builder().userName(CommonConstants.ANONYMOUS_USER_NAME).build();
		}
		UserPrincipal userPrincipal = userPrincipalThreadLocal.get();
		if (StringUtils.isBlank(userPrincipal.getUserName())) {
			userPrincipal.setUserName(CommonConstants.ANONYMOUS_USER_NAME);
		}
		return userPrincipal;
	}

	@PreDestroy
	public void clear() {
		userPrincipalThreadLocal.remove();
	}

}