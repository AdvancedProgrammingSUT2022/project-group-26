package com.example.project.models;

import com.google.gson.Gson;

public class Response {
    private Output output;

    public Response(Output output){
        setOutput(output);
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

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}