package com.shared.app.messages;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Message.Builder;
import com.shared.app.messages.MusicNowMessageType.Type;

public class AccountMusicNowMessage extends MusicNowMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4233043888302010357L;

	protected static String ACCOUNT_TYPE = "accountType";
	protected static String USERNAME = "username";
	protected static String PASSWORD = "password";
	protected static String EMAIL = "email";
	protected static String DESCRIPTION = "description";
	protected static String IMAGE = "image";
	protected static String EVENTS = "events";
	protected static String WEBSITE = "website";
	protected static String GENRE = "genre";

	private String accountType = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String description = null;
	private byte[] image = null;
	private Integer event = 0;
	private String website = null;

	// performer specific
	private Integer genre = 0;

	public AccountMusicNowMessage(String registrationID, Type type,
			String accountType, String username, String password, String email,
			String description, byte[] image, Integer event, String website,
			Integer genre) {
		super(registrationID, type);

		this.accountType = accountType;
		this.username = username;
		this.password = password;
		this.email = email;
		this.description = description;
		this.image = image;
		this.event = event;
		this.website = website;
		this.genre = genre;
	}

	public AccountMusicNowMessage(HttpServletRequest request) throws Exception {
		super(request);

		this.accountType = request.getParameter(ACCOUNT_TYPE);
		this.username = request.getParameter(USERNAME);
		this.password = request.getParameter(PASSWORD);
		this.email = request.getParameter(EMAIL);
		this.description = request.getParameter(DESCRIPTION);
		this.image = request.getParameter(IMAGE).getBytes("UTF-8");
		this.event = Integer.valueOf(request.getParameter(EVENTS));
		this.website = request.getParameter(WEBSITE);
		this.genre = Integer.valueOf(request.getParameter(GENRE));
	}

	public AccountMusicNowMessage(HttpResponse response) throws Exception {
		super(response);

		for (NameValuePair nameValuePair : this.getResponseNameValuePairs()) {
			if (nameValuePair.getName().equals(ACCOUNT_TYPE)) {
				this.accountType = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(USERNAME)) {
				this.username = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(PASSWORD)) {
				this.password = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(EMAIL)) {
				this.email = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(DESCRIPTION)) {
				this.description = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(IMAGE)) {
				this.image = nameValuePair.getValue().getBytes();
			} else if (nameValuePair.getName().equals(EVENTS)) {
				this.event = Integer.valueOf(nameValuePair.getValue());
			} else if (nameValuePair.getName().equals(WEBSITE)) {
				this.website = nameValuePair.getValue();
			} else if (nameValuePair.getName().equals(GENRE)) {
				this.genre = Integer.valueOf(nameValuePair.getValue());
			}
		}
	}

	public String getAccountType() {
		return accountType;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getDescription() {
		return description;
	}

	public byte[] getImage() {
		return image;
	}

	public Integer getEvent() {
		return event;
	}

	public String getWebsite() {
		return website;
	}

	public Integer getGenre() {
		return genre;
	}

	public UrlEncodedFormEntity getEntity() throws UnsupportedEncodingException {
		List<NameValuePair> list = new ArrayList<NameValuePair>();

		// fill in all attributes

		// the first two are always registration ID and message type
		list.add(new BasicNameValuePair(MusicNowMessage.REG_ID, this
				.getRegistrationID()));
		list.add(new BasicNameValuePair(MusicNowMessage.TYPE, this.getType()
				.name()));

		list.add(new BasicNameValuePair(ACCOUNT_TYPE, this.accountType));
		list.add(new BasicNameValuePair(USERNAME, this.username));
		list.add(new BasicNameValuePair(PASSWORD, this.password));
		list.add(new BasicNameValuePair(EMAIL, this.email));
		list.add(new BasicNameValuePair(DESCRIPTION, this.description));
		list.add(new BasicNameValuePair(IMAGE, new String(this.image, "UTF-8")));
		list.add(new BasicNameValuePair(EVENTS, String.valueOf(this.event)));
		list.add(new BasicNameValuePair(WEBSITE, this.website));
		list.add(new BasicNameValuePair(GENRE, String.valueOf(this.genre)));

		return new UrlEncodedFormEntity(list);
	}

	public Message getMessage() throws UnsupportedEncodingException {
		Builder builder = new Message.Builder();
		
		builder.addData(REG_ID, this.getRegistrationID());
		builder.addData(TYPE, this.getType().name());
		builder.addData(ACCOUNT_TYPE, this.accountType);
		builder.addData(USERNAME, this.username);
		builder.addData(PASSWORD, this.password);
		builder.addData(EMAIL, this.email);
		builder.addData(DESCRIPTION, this.description);
		builder.addData(IMAGE, new String(this.image, "UTF-8"));
		builder.addData(EVENTS, String.valueOf(this.event));
		builder.addData(WEBSITE, this.website);
		builder.addData(GENRE, String.valueOf(this.genre));
		
		return builder.build();
	}
}
