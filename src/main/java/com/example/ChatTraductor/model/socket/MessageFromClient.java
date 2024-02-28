package com.example.ChatTraductor.model.socket;

public class MessageFromClient {
	
    private Integer room;
    private String message;

    public MessageFromClient() {
        super();
    }
    public MessageFromClient(Integer room, String message) {
    	super();
        this.message = message;
        this.room = room;
    }

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageFromClient [room=" + room + ", message=" + message + "]";
	}

    
}