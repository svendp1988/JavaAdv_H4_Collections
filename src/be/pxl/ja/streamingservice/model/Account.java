package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
	private String email;
	private String password;
	private PaymentInfo paymentInfo;

	private StreamingPlan streamingPlan;
	private final Map<String, Profile> profiles = new HashMap<>();

	public Account(String email, String password) {
		this.email = email;
		setPassword(password);
		profiles.put("Profile1", new Profile("Profile1"));
	}

	public void setStreamingPlan(StreamingPlan streamingPlan) {
		this.streamingPlan = streamingPlan;
	}

	public void addProfile(Profile profile) {
		if (profiles.size() >= streamingPlan.getNumberOfScreens()) {
			throw new TooManyProfilesException("Too many profiles, cannot add another");
		}
		profiles.put(profile.getName(), profile);
	}

	public String getEmail() {
		return email;
	}

	public StreamingPlan getStreamingPlan() {
		return streamingPlan;
	}

	public Map<String, Profile> getProfiles() {
		return profiles;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean verifyPassword(String password) {
		return PasswordUtil.isValid(password, this.password);
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public void setPassword(String password) {
		this.password = PasswordUtil.encodePassword(password);
	}

	public Profile getFirstProfile() {
		return profiles.get(0);
	}
}
