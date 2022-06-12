package com.assignment14.chat.domain;

public class InChatMessage {

      private String senderName;
      private String message;
      private Integer number;
   
       public InChatMessage() {
      }
    
       public InChatMessage(String message) {
    	this.message = message;
       }
    
       public InChatMessage(Integer number, String senderName, String message) {
		this.message = message;
		this.senderName = senderName;
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderName() {
        return senderName;
        }

        public void setSenderName(String senderName) {
        this.senderName = senderName;
        }

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "InChatMessage [senderName=" + senderName + ", message=" + message + ", number=" + number + "]";
	}
	
}
