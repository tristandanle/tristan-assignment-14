package com.assignment14.chat.domain;

public class OutChatMessage {

	private String content;
	
	public OutChatMessage() {
	}
	
	public OutChatMessage(String content) {
        this.content = content;
        }
	
	
	 public String getContent() {
		return content;
	 }
	
	 public void setContent(String content) {
		this.content = content;
	 }

	 @Override
	 public String toString() {
		return "OutChatMessage [content=" + content + "]";
	 }

}
