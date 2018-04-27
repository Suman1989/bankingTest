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
	
	@Value(value = "classpath:00DS0000003Kwz9.jks")
	private Resource serverSSLKeyStore;
	
	@Bean
	@Qualifier("restTemplateOpen")
	public RestTemplate restTemplateOpen() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException  {
		
	    TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
	        @Override
	        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
	            return true;
	        }
	    };
	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier(){
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}});
	    //Proxy only reqd. if calling from Citi network
		//HttpHost httpHost = new HttpHost("webproxy.wlb2.nam.nsroot.net", 8080, "http");
		//HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).setProxy(httpHost).build();
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);	  
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory); 
        SSLContext.setDefault(sslContext);
	    return restTemplate;
	}
		
	@Bean
	@Qualifier("restTemplate")
	public RestTemplate restTemplate() throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException  {
		
		RestTemplate restTemplate = new RestTemplate(setTrustStore());
		return restTemplate;
	}
	
	@Bean
	@Qualifier("restTemplateBasic")
	public RestTemplate restTemplateBasic(){
		
		return new RestTemplate();
	}
	
	private ClientHttpRequestFactory setTrustStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException{
		
		String keyStorePassword = "sfdc123";
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    keyStore.load(serverSSLKeyStore.getInputStream(), keyStorePassword.toCharArray());

	    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
	            new SSLContextBuilder()
	                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
	                    .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
	                    .build(),
	            NoopHostnameVerifier.INSTANCE);

	    //Proxy only reqd. if calling from Citi network
	   // HttpHost httpHost = new HttpHost("webproxy.wlb2.nam.nsroot.net", 8080, "http");
	    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

	    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    return requestFactory;   
		
	}
}
