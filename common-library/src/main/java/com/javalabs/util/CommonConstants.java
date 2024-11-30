package com.javalabs.util;

public interface CommonConstants {
	String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	String CONTEXT_STRING = "context";
	String ID = "id";
	String NAME = "name";
	String VALID_FOR = "validFor";
	String HREF = "href";
	String VERSION = "version";
	String LAST_UPDATE = "lastUpdate";
	String TYPE = "type";
	String BASE_TYPE = "baseType";
	String LIFECYCLE_STATUS = "lifecycleStatus";
	String CATEGORY = "category";
	String ROLE = "role";

	String PARENT_ID = "parentId";
	String SCHEMA_DIRECTORY = "static/schemas/";
	String SCHEMA_REQUIRED_KEY = "required";

	String REST_HEADER_X_TOTAL_COUNT = "X-Total-Count";
	String REST_HEADER_CONTENT_RANGE = "Content-Range";
	String REST_HEADER_LINK = "Link";
	String REST_HEADER_LOCATION = "Location";
	String REST_HEADER_X_LIMIT = "X-Limit"; // Only included for JsonPath filter queries
	String REST_HEADER_X_FILTER_COUNT = "X-Filter-Count"; // Only included for JsonPath filter queries

	String OFFSET = "offset";
	String ENTITYTYPENAME = "entityTypeName";
	String LIMIT = "limit";
	String SORT = "sort";
	String FILTER = "filter";
	String SORT_DIRECTION_TOKEN = "-";
	String SORT_KEY_ESCAPE = ",";
	String QUERY_KEY_FIELD = "fields";
	String QUERY_KEY_FIELD_ESCAPE = ",";
	String QUERY_KEY_FIELD_NESTING_IND = ".";
	String COLON = ":";
	String QUERY_KEY_FIELD_NONE = "none";

	String SELF = "self";
	String NEXT = "next";
	String PREVIOUS = "previous";
	String FIRST = "first";
	String LAST = "last";

	String DEPTH = "depth";
	String EXPAND = "expand";

	String PARAMETER_KEY_LIFECYCLE_STATUS_TRANSITIONS = "PK_LIFECYCLE_STATUS_TRANSITIONS";
	String INTERNAL = "internal";

	String JUNIT_PROFILE = "junit";

	String USER_AGENT_WEB_CLIENT = "WebClient";
	String DEFAULT_ERROR_HTTP_STATUS = "500";
	int DEFAULT_LIMIT = 100;

	String EMPTY = "";

	String DEFAULT_SORT_ATTRIBUTE = "name";

	String SEPARATOR_DOT = "\\.";
	String SEPARATOR_COMMA = ",";
	String FIELD_PATH_DELIMITER = "->";

	String BEARER_PREFIX = "Bearer ";
	String BASIC_PREFIX = "Basic ";
	String ANONYMOUS_USER_NAME = "Anonymous";

	String S2S_USER_NAME_HEADER = "X-s2s-claim-userName";
	String S2S_ORGANIZATION_HEADER = "X-s2s-claim-organizationId";
	String S2S_TENANT_ID_HEADER = "X-s2s-claim-tenantId";

	String DEPTH_CAN_NOT_BE_HIGHER_THEN_THREE = "Depth can not be higher then 3. Please use proper depth value.";

	String MEDIA_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
	String ACCESS_TOKEN = "access_token";
	String SLASH = "/";
	String GROUPS = "groups";

	String ANSI_RED = "\u001B[31m";
	String ANSI_RESET = "\u001B[0m";

}