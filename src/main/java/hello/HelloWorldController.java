package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import hello.RestClient;
import hello.request.WebhookRequest;
import org.springframework.web.bind.annotation.RequestBody;
import hello.RestClient;
import hello.APIClient;
import org.json.JSONArray;
import org.json.JSONObject;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

	@Autowired
	private RestClient restClient;
	
	@Autowired
	private APIClient apiClient;
	
	@Autowired
	private WebhookJsonConverter webhookJsonConverter;

	@RequestMapping(method = {RequestMethod.POST})
	public @ResponseBody WebhookResponse webhookPost(@RequestBody WebhookRequest request){
		//jsonInString="{\"id\":\"9f492f90-f949-48f1-9137-3ff1c8913f7a\",\"timestamp\":\"2018-04-26T04:03:54.626Z\",\"lang\":\"en\",\"result\":{\"source\":\"agent\",\"resolvedQuery\":\"tell me about the market update\",\"speech\":\"\",\"action\":\"personalhelp\",\"actionIncomplete\":false,\"parameters\":{\"ConversationType\":\"IN\"},\"contexts\":[],\"metadata\":{\"intentId\":\"327debc4-7c47-4925-9ea7-ac9b2ef976a1\",\"webhookUsed\":\"true\",\"webhookForSlotFillingUsed\":\"true\",\"intentName\":\"PersonalHelper\"},\"fulfillment\":{\"speech\":\"Hi response static\",\"messages\":[{\"type\":0,\"speech\":\"Hi response static\"}]},\"score\":1.0},\"status\":{\"code\":200,\"errorType\":\"success\",\"webhookTimedOut\":false},\"sessionId\":\"d0e16ee8-1330-44b3-93c3-f62d8583c333\"}";
		//System.out.println(jsonInString);
		//String strISO=webhookJsonConverter.convert(jsonInString);
		//strISO = restClient.callISOApi(strISO);
		//System.out.println("************ISO Code is "+strISO);
		String iSo ="";
		try{
			iSo = request.getResult().getResolvedQuery();
			JSONObject jsonCaseDetails = apiClient.getCaseDetails("sr05453");
		}catch(Exception e){
		iSo ="Error";	
		}
		return new WebhookResponse("Hello! ...." + request.getResult().getResolvedQuery(), "Text " + request.getResult().getResolvedQuery());
	}
	
	private String buildSpeech(JSONObject jsonCaseDetails){
		
		StringBuffer speech = new StringBuffer();		
		JSONArray caseDetails = jsonCaseDetails.getJSONArray("caseDetails");
		/*for (int i = 0; i < caseDetails.length(); i++) {
			JSONObject caseDetail = caseDetails.getJSONObject(i);
				speech.append("Client Name is " + caseDetail.getString("clientName"))
						.append(" and  ").append(caseDetail.getString("entityNumber")).append(" .");
	
			} */
		
		speech.insert(0,"I have found "+caseDetails.length()+" cases assigned to you.");
		
		return speech.toString();
	}
	
}
