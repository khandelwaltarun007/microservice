package com.javalabs.config.event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Auth {
    private String userId;
    private String organizationId;
    private List<String> roles;
    private List<String> groups;
    private String tokenIssuer;
    private String tokenIssuedTime;
    private String tokenExpiryTime;
    private String authenticationToken;
}