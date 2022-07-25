package com.example.project.models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private User loggedInUser;
    private boolean isOnMainMenu;

    public Network(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
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

    public void flush() throws IOException {
        outputStream.flush();
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Request readRequest() throws IOException {
        String requestString = this.getInputStream().readUTF();
        Request request = Request.fromJson(requestString);
        return request;
    }

    public void sendResponseWithOutput(Output output) throws IOException {
        outputStream.writeUTF(new Response(output).toJson());
        outputStream.flush();
    }

    public void sendResponse(Response response) throws IOException {
        outputStream.writeUTF(response.toJson());
        outputStream.flush();
    }

    public boolean isOnMainMenu() {
        return isOnMainMenu;
    }

    public void setOnMainMenu(boolean onMainMenu) {
        isOnMainMenu = onMainMenu;
    }
}