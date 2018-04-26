package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import hello.RestClient;
import hello.model;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

	@Autowired
	private RestClient restClient;
	
	@Autowired
	private WebhookJsonConverter webhookJsonConverter;

	@RequestMapping(method = {RequestMethod.POST})
	public @ResponseBody WebhookResponse webhookPost(@RequestBody Request request){
		//jsonInString="{\"id\":\"9f492f90-f949-48f1-9137-3ff1c8913f7a\",\"timestamp\":\"2018-04-26T04:03:54.626Z\",\"lang\":\"en\",\"result\":{\"source\":\"agent\",\"resolvedQuery\":\"tell me about the market update\",\"speech\":\"\",\"action\":\"personalhelp\",\"actionIncomplete\":false,\"parameters\":{\"ConversationType\":\"IN\"},\"contexts\":[],\"metadata\":{\"intentId\":\"327debc4-7c47-4925-9ea7-ac9b2ef976a1\",\"webhookUsed\":\"true\",\"webhookForSlotFillingUsed\":\"true\",\"intentName\":\"PersonalHelper\"},\"fulfillment\":{\"speech\":\"Hi response static\",\"messages\":[{\"type\":0,\"speech\":\"Hi response static\"}]},\"score\":1.0},\"status\":{\"code\":200,\"errorType\":\"success\",\"webhookTimedOut\":false},\"sessionId\":\"d0e16ee8-1330-44b3-93c3-f62d8583c333\"}";
		//System.out.println(jsonInString);
		//String strISO=webhookJsonConverter.convert(jsonInString);
		//strISO = restClient.callISOApi(strISO);
		//System.out.println("************ISO Code is "+strISO);
		return new WebhookResponse("Hello! Hirak...." + request.getResult().getResolvedQuery(), "Text " + request.getResult().getResolvedQuery());
	}	
	
}
