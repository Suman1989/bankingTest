package hello;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RestClient{

  @Autowired
  private RestTemplate restTemplate;
  
  public String callISOApi(String strIso){
	  
   HttpHeaders headers = new HttpHeaders();
   headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
   Strin url = "http://services.groupkt.com/country/get/iso2code/IN"+strIso;
   UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);   

  HttpEntity<?> entity = new HttpEntity<>(headers);

  HttpEntity<ISOCodeResponse> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ISOCodeResponse.class);
  String countryName = response.getRestResponse().getResult().getName();
  return  countryName;
  }
}
