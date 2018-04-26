
package hello.model;

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
    "account.original",
    "account"
})
public class Parameters_ {

    @JsonProperty("account.original")
    private String accountOriginal;
    @JsonProperty("account")
    private String account;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("account.original")
    public String getAccountOriginal() {
        return accountOriginal;
    }

    @JsonProperty("account.original")
    public void setAccountOriginal(String accountOriginal) {
        this.accountOriginal = accountOriginal;
    }

    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
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
