package com.user.model;

import java.time.LocalDateTime;

public class Message {

	 private int messageID;

	    
	    private User sender;

	    
	    private User receiver;

	    private String content;

	    private LocalDateTime dateSent;


	    private MessageStatus status;


		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Message(int messageID, User sender, User receiver, String content, LocalDateTime dateSent,
				MessageStatus status) {
			super();
			this.messageID = messageID;
			this.sender = sender;
			this.receiver = receiver;
			this.content = content;
			this.dateSent = dateSent;
			this.status = status;
		}


		public int getMessageID() {
			return messageID;
		}


		public void setMessageID(int messageID) {
			this.messageID = messageID;
		}


		public User getSender() {
			return sender;
		}


		public void setSender(User sender) {
			this.sender = sender;
		}


		public User getReceiver() {
			return receiver;
		}


		public void setReceiver(User receiver) {
			this.receiver = receiver;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public LocalDateTime getDateSent() {
			return dateSent;
		}


		public void setDateSent(LocalDateTime dateSent) {
			this.dateSent = dateSent;
		}


		public MessageStatus getStatus() {
			return status;
		}


		public void setStatus(MessageStatus status) {
			this.status = status;
		}


		@Override
		public String toString() {
			return "Message [messageID=" + messageID + ", sender=" + sender + ", receiver=" + receiver + ", content="
					+ content + ", dateSent=" + dateSent + "]";
		}
	    
		public enum MessageStatus {
	        SENT, READ, DELETED
	    }

}
