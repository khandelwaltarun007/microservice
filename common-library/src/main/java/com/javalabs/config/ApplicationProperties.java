package com.javalabs.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * A class having definitions of all the attributes which are being used in yml
 * configuration files of microservices.
 */
@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

	@NotEmpty
	@Value("${spring.application.name}")
	@NotNull(message = "application name cannot be null!")
	private String applicationName;
	@Value("${server.servlet.context-path}")
	private String basePath;
	private boolean eTagEnabled = false;
	private String multiOrganizationHeaderName = "organizationId";
	private boolean multiOrganization = false;

	@Value("${application.s2s.claims.s2sTokenClaim:s2s}")
	private String s2sTokenClaim;

	@NotEmpty
	@NotNull(message = "application URL cannot be null!")
	private String url;

	@NotNull(message = "schemaValidationEnabled value cannot be null!")
	private Boolean schemaValidationEnabled = true;

	@NotNull(message = "maxRecordLimit value cannot be null!")
	private Integer maxRecordLimit = 100;

	@NotNull(message = "okhttpConnectTimeout value cannot be null!")
	@Pattern(regexp = "[1-9][0-9]*", message = "okhttpConnectTimeout must be provided!")
	private String okhttpConnectTimeout;

	@NotNull(message = "okhttpReadTimeout value cannot be null!")
	@Pattern(regexp = "[1-9][0-9]*", message = "okhttpReadTimeout must be provided!")
	private String okhttpReadTimeout;

	@NotNull(message = "okhttpWriteTimeout value cannot be null!")
	@Pattern(regexp = "[1-9][0-9]*", message = "okhttpWriteTimeout must be provided!")
	private String okhttpWriteTimeout;

	private boolean okhttpSelfSignedCertificateEnabled= false;

	private List<Integer> notRetryableResponseCodes;
	private Boolean tenancyEnabled = false;
	private String tenantIdField = "tenantId";
	private String adminTenantId = null;
	private Boolean organizationFilterEnabled = false;
	private String organizationFilterField = "organizationId";
	private String adminOrganizationId = null;
	private List<String> dnextUrlPrefixes;
	@Value("${tracing.header.site:site}")
	private String site = "site";
	@Value("${tracing.header.company:company}")
	private String company = "company";
	@Value("${tracing.header.channel:channel}")
	private String channel = "channel";
	@Value("${tracing.header.application:application}")
	private String application = "application";
	@Value("${tracing.header.user:user}")
	private String user = "user";
	@Value("${tracing.header.session-id:sessionId}")
	private String sessionId = "sessionId";
	@Value("${tracing.header.business-transaction-id:businessInteractionId}")
	private String businessInteractionId = "businessInteractionId";
	@Value("${tracing.header.transaction-id:transactionId}")
	private String transactionId = "transactionId";
	@Value("${auth.organization-id:organizationId}")
	private String authOrganizationId = "organizationId";
	@Value("${tracing.header.business-headers:business_headers}")
	private String businessHeader = "business_headers";
	@Value("${X-Forwarded-For:X-Forwarded-For}")
	private String xForwardedFor = "X-Forwarded-For";
	@Value("${tracing.header.business-interaction-type:businessInteractionType}")
	private String businessInteractionType = "businessInteractionType";

	@Setter
    @Getter
    private HttpRequestLogProperties httpRequestLog = new HttpRequestLogProperties();


	@Getter
	@Setter
	public static class HttpRequestLogProperties {
		private boolean enabled = false;
		private String loglevel = "basic";
		private List<String> redactedHeaders;
	}

	public List<String> getTaggableHeaders() {
		List<String> validHeaderNames = new ArrayList<>();
		validHeaderNames.add(site);
		validHeaderNames.add(company);
		validHeaderNames.add(channel);
		validHeaderNames.add(application);
		validHeaderNames.add(user);
		validHeaderNames.add(sessionId);
		validHeaderNames.add(businessInteractionId);
		validHeaderNames.add(transactionId);
		validHeaderNames.add(businessInteractionType);
		return validHeaderNames;
	}

}