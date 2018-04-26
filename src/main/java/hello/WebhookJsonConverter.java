package hello;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import hello.request.WebhookRequest;

@Component
public class WebhookJsonConverter {

	public String convert(String jsonInString){
		//String jsonInString="{\"id\":\"9f492f90-f949-48f1-9137-3ff1c8913f7a\",\"timestamp\":\"2018-04-26T04:03:54.626Z\",\"lang\":\"en\",\"result\":{\"source\":\"agent\",\"resolvedQuery\":\"tell me about the market update\",\"speech\":\"\",\"action\":\"personalhelp\",\"actionIncomplete\":false,\"parameters\":{\"ConversationType\":\"Market\"},\"contexts\":[],\"metadata\":{\"intentId\":\"327debc4-7c47-4925-9ea7-ac9b2ef976a1\",\"webhookUsed\":\"true\",\"webhookForSlotFillingUsed\":\"true\",\"intentName\":\"PersonalHelper\"},\"fulfillment\":{\"speech\":\"Hi response static\",\"messages\":[{\"type\":0,\"speech\":\"Hi response static\"}]},\"score\":1.0},\"status\":{\"code\":200,\"errorType\":\"success\",\"webhookTimedOut\":false},\"sessionId\":\"d0e16ee8-1330-44b3-93c3-f62d8583c333\"}";
		ObjectMapper mapper = new ObjectMapper();
		String param ="";

		//JSON from String to Object
		try {
			WebhookRequest request = mapper.readValue(jsonInString, WebhookRequest.class);
			param = request.getResult().getParameters().getConversationType();
			System.out.println("***********param is::::"+param);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return param;
	}
	
}
