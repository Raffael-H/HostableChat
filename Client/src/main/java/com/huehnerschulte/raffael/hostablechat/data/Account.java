package com.huehnerschulte.raffael.hostablechat.data;

import javax.json.JsonObject;
import java.time.LocalDateTime;

public class Account {

    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isAdmin;

    private String userTag;

    private String displayName;

    private String username;

    public static Account fromJsonObject(JsonObject jsonObject){
        Account account = new Account();
        account.setId(jsonObject.getString("id",null));
        String cDate = jsonObject.getString("createdDate", null);
        String uDate = jsonObject.getString("updatedDate", null);
        account.setCreatedDate(cDate != null ? LocalDateTime.parse(cDate) : null);
        account.setUpdatedDate(uDate != null ? LocalDateTime.parse(uDate) : null);
        account.setAdmin(jsonObject.getBoolean("isAdmin",false));
        account.setUserTag(jsonObject.getString("userTag",null));
        account.setDisplayName(jsonObject.getString("displayName",null));
        account.setUsername(jsonObject.getString("username",null));
        return account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
