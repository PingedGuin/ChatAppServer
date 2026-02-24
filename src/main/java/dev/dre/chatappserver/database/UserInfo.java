package dev.dre.chatappserver.database;

import dev.dre.chatappserver.dtos.RegisterRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class UserInfo {
    HashMap<String, RegisterRequest> userInfo = new LinkedHashMap<>();

    public void addInfo(String username, RegisterRequest request) {
        userInfo.put(username, request);
    }
    public boolean isUserExist(String username) {
        return userInfo.containsKey(username);
    }
    public void removeInfo(String username) {
        userInfo.remove(username);
    }
}
