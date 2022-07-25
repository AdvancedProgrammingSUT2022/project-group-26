package com.example.project.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    public static Network instance;

    public static Network getInstance() {
        if (instance == null) instance = new Network();
        return instance;
    }

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public void setUp(Socket socket) {
        this.socket = socket;
        try {
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Output sendRequestAndGetResponseOutput(Request request) {
        try {
            outputStream.writeUTF(request.toJson());
            outputStream.flush();
            while (true) {
                String input = inputStream.readUTF();
                return Response.fromJson(input).getOutput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response sendRequestAndGetResponse(Request request) {
        try {
            outputStream.writeUTF(request.toJson());
            outputStream.flush();
            while (true) {
                String input = inputStream.readUTF();
                return Response.fromJson(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendRequestWithoutResponse(Request request) {
        try {
            outputStream.writeUTF(request.toJson());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response getResponse() {
        while (true) {
            String input = null;
            try {
                input = inputStream.readUTF();
            } catch (IOException e) {
                return null;
            }
            return Response.fromJson(input);
        }
    }
}