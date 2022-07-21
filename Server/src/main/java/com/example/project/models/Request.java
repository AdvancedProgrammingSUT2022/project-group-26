package com.example.project.models;

import com.google.gson.Gson;

import java.util.HashMap;

public class Request {
    private RequestEnum action;
    private HashMap<String, Object> params = new HashMap<>();

    public RequestEnum getAction() {
        return action;
    }

    public void setAction(RequestEnum action) {
        this.action = action;
    }

    public static Request fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Request.class);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }
}