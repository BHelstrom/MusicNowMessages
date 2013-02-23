package com.shared.app.messages;

import javax.servlet.http.HttpServletRequest;

import com.shared.app.messages.MusicNowMessageType.AccountType;
import com.shared.app.messages.MusicNowMessageType.Type;

public class UpdatePerformerAccountRequest extends AccountMusicNowMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855386530819522531L;

	public UpdatePerformerAccountRequest(HttpServletRequest request) throws Exception {
		super(request);
	}
	
	public UpdatePerformerAccountRequest(String registrationID, String username, String password, String email, String description, byte[] image, Integer event, String website, Integer genre) {
		super(registrationID, Type.UPDATE_PERFORMER_ACCOUNT, AccountType.PERFORMER.name(), username, password, email, description, image, event, website, genre);
	}
}
