package com.javalabs.config.tracing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Token {
    @JsonProperty("jti")
    protected String id;
    @JsonProperty("iss")
    protected String issuer;
    @JsonProperty("aud")
    protected List<String> audience;
    @JsonProperty("sub")
    protected String subject;
    @JsonProperty("typ")
    protected String type;
    @JsonProperty("azp")
    protected String issuedFor;
}
