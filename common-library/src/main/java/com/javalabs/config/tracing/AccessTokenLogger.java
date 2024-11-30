package com.javalabs.config.tracing;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/**
 * Class responsible to put access token into MDC. Mapped Diagnostic Context provides a way to enrich log messages with information that
 * could be unavailable in the scope where the logging actually occurs but that can be indeed useful to better track the execution
 * of the program.
 */

@Component
@RequiredArgsConstructor
public class AccessTokenLogger {

    private static final String ACCESS_TOKEN = "accessToken";

    private final AccessTokenExtractor accessTokenExtractor;
    private final ObjectMapper objectMapper;

    public void putTokenInfoToMDC() {
        JsonNode node = objectMapper.valueToTree(accessTokenExtractor.extract());
        Iterator<Map.Entry<String, JsonNode>> nodeIterator = node.fields();
        while (nodeIterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = nodeIterator.next();
            if (entry.getValue().isTextual()) {
                MDC.put(ACCESS_TOKEN + "." + entry.getKey(), entry.getValue().textValue());
            }
        }
    }
}
