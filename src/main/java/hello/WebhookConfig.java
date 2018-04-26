package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;


@Configuration
public class WebhookConfig {
	
	@Bean
	public RestTemplate restTemplate()  {
		RestTemplate restTemplate = null;
		try {
			restTemplate = new RestTemplate(getClientHttpRequestFactory());
			System.out.println("******Rest Template created*****");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	      return restTemplate;
	}
	
public ClientHttpRequestFactory getClientHttpRequestFactory()  {
		
        HttpClient httpClient = HttpClients.custom().build();
        
	    return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
}
