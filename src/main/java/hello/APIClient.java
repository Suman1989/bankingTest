package hello;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class APIClient {
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	/**
	 * 
	 * @return
	 */
	
	
	
	/**
	 * 
	 * @param userSoeId
	 * @return
	 */
	public JSONObject getCaseDetails(String userSoeId){
		
		String url= "https://sit.apib2b.citi.com/gcgapi/dev3/api/v1/ccb/users/{soeId}/caseDetails/retrieve";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<String> response = null;
		
		String body = "{\"userType\":\"INTERNAL\",\"userRole\":\"RM\",\"messageId\":\"12563\"}";
		
		httpHeaders.add("Authorization", "Bearer " + getAccessToken());
		httpHeaders.add("Content-Type", "application/json");
		httpHeaders.add("Accept", "application/json");
		httpHeaders.add("APIm-Debug", "true");
		httpHeaders.add("client_id", "bf5c92b6-6041-4d13-9177-83634d991457");
		httpHeaders.add("channelID", "UCP");
		httpHeaders.add("countryCode", "US");
		httpHeaders.add("businessCode", "CCB");
		HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
		
		response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class, userSoeId);	
		
		return new JSONObject(response.getBody());
	}
	
	
	private String getAccessToken(){
		String url= "https://sit.apib2b.citi.com/gcgapi/dev3/api/oauth/token";
		
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<String> response = null;
		
		httpHeaders.add("Authorization", "Basic " + "YmY1YzkyYjYtNjA0MS00ZDEzLTkxNzctODM2MzRkOTkxNDU3Okc2dkY3bVUyaEkzaEU0bk44dk40ckIyeUg0aEoybFA0ck0xY0cybVE1aVk0blQ0Zk01");
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
		httpHeaders.add("Accept", "application/x-www-form-urlencoded");
		httpHeaders.add("APIm-Debug", "true");
		HttpEntity<?> httpEntity = new HttpEntity<>("grant_type=client_credentials&scope=/api",httpHeaders);
		
		response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);		
		JSONObject tokenResponse = new JSONObject(response.getBody());
	
		String token = tokenResponse.getString("access_token");
		
		return token;
	}

}
