package cn.itcast.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 消息生产者
 * @author Administrator
 *
 */
@RestController
public class QueueController {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@RequestMapping("/send")
	public void send(String text){
	
		jmsMessagingTemplate.convertAndSend("itcast", text);
	}
	
	
	@RequestMapping("/sendmap")
	public void sendMap(){
		Map map=new HashMap<>();
		map.put("mobile", "18665871090");
		map.put("template_code", "SMS_149101898");
		map.put("sign_name","品优购");
		map.put("param", "{\"code\":\"981431\"}");
		
		jmsMessagingTemplate.convertAndSend("sms", map);
	}
}
