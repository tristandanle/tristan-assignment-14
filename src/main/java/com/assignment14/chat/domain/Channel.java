package com.assignment14.chat.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Channel {
	
    private Integer number;
    private static Map<Integer,Channel> channelMap = new HashMap<>();
    private List<InChatMessage> listMessages = new ArrayList<>();

	public static Channel getChannel(Integer number) {
		Channel channel = channelMap.get(number);
		if(channel == null) {
			channel =  new Channel();
			channel.setNumber(number);
			channelMap.put(number, channel);
		}
		return channel;
	}
	
	private Channel() {
		
	}
    
        public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	
	public void addMessage(InChatMessage message) {
		listMessages.add(message);
	}

	public List<InChatMessage> getListMessages() {
		return listMessages;
	}
	
	@Override
	public String toString() {
		return "Channel [number=" + number + ", listMessages=" + listMessages + "]";
	}

	
	 
	
}
