package com.shared.app.messages;

public class MusicNowMessageType {
	
	public enum Type {
		AUTHENTICATE_USER_LOGIN_RQST,

		RESPONSE,
		
		UPDATE_PERFORMER_ACCOUNT,
		
		RETRIEVE_ACCOUNT_RESPONSE
	};
	
	public enum AccountType {
		PERFORMER,
		
		VENUE
	}

	public static Type toType(String type) throws Exception {
		for(Type t : Type.values()) {
			if(t.name().equals(type)) {
				return t;
			}
		}
		throw new Exception("No matching message type! (" + type + ")");
	}
}