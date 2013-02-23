package com.shared.app.messages;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.shared.app.messages.MusicNowMessageType.Type;


public class AuthenticateUserLoginRequest extends MusicNowMessage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3500795267366220741L;
	
	private static String USERNAME = "username";
	private static String PASSWORD = "password";
	
	private String username = null;
	private String password = null;
	
	public AuthenticateUserLoginRequest(String registrationID, String username, String password) {
		super(registrationID, Type.AUTHENTICATE_USER_LOGIN_RQST);
		
		this.username = username;
		this.password = password;
	}
	
	public AuthenticateUserLoginRequest(HttpServletRequest request) throws Exception {
		super(request);
		
		// parse all name value pairs from request message and store
		this.username = request.getParameter(USERNAME);
		this.password = request.getParameter(PASSWORD);
	}

	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public UrlEncodedFormEntity getEntity() throws UnsupportedEncodingException {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		
		// fill in all attributes
		
		// the first two are always registration ID and message type
		list.add(new BasicNameValuePair(MusicNowMessage.REG_ID, this.getRegistrationID()));
		list.add(new BasicNameValuePair(MusicNowMessage.TYPE, this.getType().name()));
		
		// remaining values are message specific
		list.add(new BasicNameValuePair(USERNAME, this.username));
		list.add(new BasicNameValuePair(PASSWORD, this.password));
		
		return new UrlEncodedFormEntity(list);
	}
}
