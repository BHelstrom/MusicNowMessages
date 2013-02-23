package com.shared.app.messages;

import org.apache.http.HttpResponse;

import com.shared.app.messages.MusicNowMessageType.Type;

public class RetrieveAccountResponse extends AccountMusicNowMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946611041619899163L;
	
	public RetrieveAccountResponse(String registrationID, String accountType, String username, String password, String email, String description, byte[] image, Integer event, String website, Integer genre) {
		super(registrationID, Type.RETRIEVE_ACCOUNT_RESPONSE, accountType, username, password, email, description, image, event, website, genre);
	}
	
	public RetrieveAccountResponse(HttpResponse response) throws Exception {
		super(response);
	}
}
