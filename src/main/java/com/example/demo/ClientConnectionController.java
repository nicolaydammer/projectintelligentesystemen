package com.example.demo;

import java.io.*;
import java.net.*;

public class ClientConnectionController {
    // Initialise and get the required settings and variables.
    Settings settings = new Settings();
    String hostName = settings.getHostName();
    int portNumber = settings.getPort();
    String fromServer;
    String fromUser;
    private Socket gameServerSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;


    public void startConnection() throws IOException {

        // Open the sockets and make connection to the server.
        gameServerSocket = new Socket(hostName, portNumber);
        out = new PrintWriter(gameServerSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(gameServerSocket.getInputStream()));
        stdIn = new BufferedReader(new InputStreamReader(System.in));

        // If there is a response from the server, display it.
        if ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
        }
    }

    public void stopConnection() {
        System.out.println("Connection closed");
        System.exit(1);
    }

    public String sendMessage(String message) throws IOException {
        // Send a message to the server, and return the response given from the server.
        out.println(message);
        String response = stdIn.readLine();
        return response;
    }

    public void sendStartData() throws IOException {
        // Get the playername and gametype from shared data, so we can communicate to the server who we are and what we want to play,
        SharedData sharedData = SharedData.getInstance();
        out.println("login " + sharedData.getPlayer());
        System.out.println(stdIn.readLine());
        out.println("subscribe " + sharedData.getGameType());
        System.out.println(stdIn.readLine());
    }

    public static void main(String[] args) throws IOException {
        ClientConnectionController connection = new ClientConnectionController();

        // Start the connection and send the initial needed data.
        connection.startConnection();
        connection.sendStartData();
    }
}
