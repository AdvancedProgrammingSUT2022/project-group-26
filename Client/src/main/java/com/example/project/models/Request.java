package com.example.project.models;

import com.google.gson.Gson;

import java.util.HashMap;

public class Request {
    private RequestEnum action;
    private String data;
    private HashMap<String, Object> params = new HashMap<>();

    public Request(RequestEnum action) {
        this.action = action;
    }


    public Request(RequestEnum action, String data) {
        this.action = action;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

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

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void addToParams(String key, Object value) {
        params.put(key, value);
    }
}