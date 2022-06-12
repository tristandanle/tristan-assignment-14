package com.assignment14.chat.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;
import com.assignment14.chat.domain.Channel;
import com.assignment14.chat.domain.InChatMessage;
import com.assignment14.chat.domain.OutChatMessage;
	
@Controller
public class ChannelChatController {
	
	
	
	
    @MessageMapping("/guestchat") 
    @SendTo("/topic/guestchats")  
    public OutChatMessage handleMessaging(InChatMessage message ) throws Exception {
    		                             
	   Thread.sleep(1000); 
    	Integer number = message.getNumber();
    	System.out.println("number+: "+number);
    	Channel channel = Channel.getChannel(number); 
    	channel.addMessage(message);
      	
       
       return new OutChatMessage(HtmlUtils.htmlEscape(message.getSenderName() + ":" + message.getMessage()));
	   
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typings")
    public OutChatMessage handleTyping(InChatMessage message) throws Exception {
        return new OutChatMessage(message.getSenderName() + " is typing ...");
     }

	
	@RequestMapping("/")
	public String redirectToWecomePage( ) {
		return "redirect:/welcome";
	}
	
	
	@RequestMapping("/welcome")
	public String welcomePageView(ModelMap model ) {
		Channel channel= Channel.getChannel(1); 
		model.put("channel", channel);
		return "welcome";
	}
	
	@RequestMapping("/channels/{number}")
	public String getChatPageView(@PathVariable  Integer number, ModelMap model) {
		
		Channel channel= Channel.getChannel(number);
		
		model.put("channel", channel);
		
		return "channel";
	}
	
}
