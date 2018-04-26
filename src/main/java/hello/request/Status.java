package hello.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"code",
"errorType",
"webhookTimedOut"
})
public class Status {

@JsonProperty("code")
private Integer code;
@JsonProperty("errorType")
private String errorType;
@JsonProperty("webhookTimedOut")
private Boolean webhookTimedOut;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("code")
public Integer getCode() {
return code;
}

@JsonProperty("code")
public void setCode(Integer code) {
this.code = code;
}

@JsonProperty("errorType")
public String getErrorType() {
return errorType;
}

@JsonProperty("errorType")
public void setErrorType(String errorType) {
this.errorType = errorType;
}

@JsonProperty("webhookTimedOut")
public Boolean getWebhookTimedOut() {
return webhookTimedOut;
}

@JsonProperty("webhookTimedOut")
public void setWebhookTimedOut(Boolean webhookTimedOut) {
this.webhookTimedOut = webhookTimedOut;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
