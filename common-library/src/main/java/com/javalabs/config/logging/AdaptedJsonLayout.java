package com.javalabs.config.logging;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptedJsonLayout extends JsonLayout {

    @Override
    protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
        super.addCustomDataToJsonMap(map, event);

        Map<String, String> mdc = event.getMDCPropertyMap();
        mergeMDCPropertiesIntoMap(map, mdc);
        processBusinessHeaders(map);
    }

    public void mergeMDCPropertiesIntoMap(Map<String, Object> map, Map<String, String> mdc){
        map.remove("mdc");
        if (mdc != null) {
            map.putAll(mdc);
        }
    }

    public void processBusinessHeaders (Map<String, Object> map){
        String businessHeaders = (String) map.get("business_headers");
        if (businessHeaders != null) {
            List<Map<String, String>> businessHeadersList = formattedBusinessHeaders(businessHeaders);
            map.put("business_headers", businessHeadersList);
            removeDuplicatedHeaders(map, businessHeadersList);
        }
    }

    public List<Map<String, String>> formattedBusinessHeaders(String businessHeaders){
        List<Map<String, String>> businessHeadersList = new ArrayList<>();
        for (String header : businessHeaders.split("\\|")){
            String[] parts = header.split("=");
            if (parts.length == 2) {
                Map<String, String> headerMap = new HashMap<>();
                headerMap.put("name", parts[0]);
                headerMap.put("value", parts[1]);
                businessHeadersList.add(headerMap);
            }
        }
        return businessHeadersList;
    }

    public void removeDuplicatedHeaders(Map<String, Object> map, List<Map<String, String>> businessHeadersList){
        for (Map<String, String> headerMap : businessHeadersList) {
            map.keySet().removeIf(key -> key.equalsIgnoreCase(headerMap.get("name")));
        }
    }

}