package com.shared.app.messages;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.shared.app.messages.MusicNowMessageType.Type;

public class Response extends MusicNowMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020637194350928480L;
	
	private static String STATUS = "status";
	
	private Boolean status = Boolean.FALSE;
	
	public Response(String registrationID, Boolean status) {
		super(registrationID, Type.RESPONSE);
		
		this.status = status;
	}
	
	public Response(HttpResponse response) throws Exception {
		super(response);
		
		for(NameValuePair nameValuePair : this.getResponseNameValuePairs()) {
			if(nameValuePair.getName().equals(STATUS)) {
				this.status = Boolean.valueOf(nameValuePair.getValue());
			}
		}
	}
	
	public boolean getStatus() {
		return this.status;
	}

	public Message getMessage() {
		Builder builder = new Message.Builder();
		
		builder.addData(REG_ID, this.getRegistrationID());
		builder.addData(TYPE, this.getType().name());
		builder.addData(STATUS, this.status.toString());
		
		return builder.build();
	}
	
	
}
