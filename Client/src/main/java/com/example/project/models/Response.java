package com.example.project.models;

import com.google.gson.Gson;

public class Response {
    private Output output;
    private String data = "";

    public Response(Output output) {
        setOutput(output);
    }

    public Response(String data) {
        this.data = data;
    }

    public Response(Output output, String data) {
        this.output = output;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public static Response fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Response.class);
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}