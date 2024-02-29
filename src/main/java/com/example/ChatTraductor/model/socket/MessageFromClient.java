package com.example.ChatTraductor.model.socket;

public class MessageFromClient {
    
	private String message;    
    private Integer receiverId;

    public MessageFromClient() {
        super();
    }
    public MessageFromClient(String message, Integer receiverId) {
    	super();
        this.message = message;
        this.receiverId = receiverId;
    }

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setRoom(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageFromClient [receiverId=" + receiverId + ", message=" + message + "]";
	}

    
}