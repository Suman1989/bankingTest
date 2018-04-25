package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import hello.RestClient;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

   @Autowired
   private RestClient restClient;
    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){

        System.out.println(obj);
        String strISO = restClient.callISOApi("IN");
        System.out.println("************ISO Code is "+strISO);
        return new WebhookResponse("Hello! Hirak...." + strISO, "Text " + strISO);
    }
}
