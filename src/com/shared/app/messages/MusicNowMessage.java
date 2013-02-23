package com.shared.app.messages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.shared.app.messages.MusicNowMessageType.Type;

public abstract class MusicNowMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4455907777774536330L;
	
	public static String REG_ID = "registrationID";
	public static String TYPE = "type";
	
	private String registrationID;
	private Type type;
	
	private List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

	public MusicNowMessage(String registrationID, Type type) {
		this.registrationID = registrationID;
		this.type = type;
	}
	
	public MusicNowMessage(HttpServletRequest request) throws Exception {
		this.registrationID = request.getParameter(REG_ID);
		this.type = MusicNowMessageType.toType(request.getParameter(TYPE));
	}
	
	public MusicNowMessage(HttpResponse response)  throws Exception {
		InputStreamReader is = new InputStreamReader(response
				.getEntity().getContent());
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(is);
		String read = br.readLine();
		while (read != null) {
			sb.append(read);
			read = br.readLine();
		}
		String data = sb.toString();
		String message = data.substring(data.indexOf('{') + 1, data.indexOf('}'));
		String[] tokens = message.split(",");
		
		for(String tok : tokens) {
			String[] nameValuePair = tok.split("=");
			if(nameValuePair[0].equals(REG_ID)) {
				this.registrationID = nameValuePair[1];
			}
			if(nameValuePair[0].equals(TYPE)) {
				this.type = MusicNowMessageType.toType(nameValuePair[1]);
			}
			this.nameValuePair.add(new BasicNameValuePair(nameValuePair[0], nameValuePair[1]));
		}
	}
	
	public String getRegistrationID() {
		return this.registrationID;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public List<NameValuePair> getResponseNameValuePairs() {
		return this.nameValuePair;
	}
}
