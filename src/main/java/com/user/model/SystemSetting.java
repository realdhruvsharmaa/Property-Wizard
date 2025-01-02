package com.user.model;

import java.time.LocalDateTime;

public class SystemSetting {

	private int settingID;

    
    private String key;

    
    private String value;

    
    private LocalDateTime dateUpdated;


	public SystemSetting() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SystemSetting(int settingID, String key, String value, LocalDateTime dateUpdated) {
		super();
		this.settingID = settingID;
		this.key = key;
		this.value = value;
		this.dateUpdated = dateUpdated;
	}


	public int getSettingID() {
		return settingID;
	}


	public void setSettingID(int settingID) {
		this.settingID = settingID;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}


	@Override
	public String toString() {
		return "SystemSetting [settingID=" + settingID + ", key=" + key + ", value=" + value + ", dateUpdated="
				+ dateUpdated + "]";
	}
    
    
    
}
