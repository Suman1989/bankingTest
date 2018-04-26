package hello;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hello.model.ISOCodeResponse;


@Service
public class RestClient{

	@Autowired
	private RestTemplate restTemplate;

	public String callISOApi(String strIso){

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		String url = "http://services.groupkt.com/country/get/iso2code/"+strIso;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);   

		HttpEntity<?> entity = new HttpEntity<>(headers);
		String countryName="Error in calling rest call";
		
		System.out.println("URL:::"+builder.toUriString());
		try{	
			HttpEntity<ISOCodeResponse> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ISOCodeResponse.class);
			System.out.println("Response body is like:::"+response.getBody());
			countryName = response.getBody().getRestResponse().getResultRest().getName();
		} catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex);
		}

		return  countryName;
	}
}
