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

    /**
     * Function that starts the connection between you and the server.
     * @throws IOException
     */
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

    /**
     *  Function that closes the connection between the client and the server.
     * @throws IOException
     */
    public void stopConnection() throws IOException {
        System.out.println("Connection closing..");
        in.close();
        out.close();
        gameServerSocket.close();
        System.out.println("Connection between you and " + settings.hostName + " have been successfully closed.");
    }

    /**
     * Function that sends a message to the server and returns the response.
     * @param message (String) contains the message that you want to serve to the server.
     * @return response (String) contains the response from the server.
     * @throws IOException
     */
    public String sendMessage(String message) throws IOException {
        // Send a message to the server, and return the response given from the server.
        out.println(message);
        String response = stdIn.readLine();
        return response;
    }

    /**
     * Function that sends the initial required data such as player name and game mode to the server.
     * @return response (String) contain the response from the server.
     * @throws IOException
     */
    public String sendStartData() throws IOException {
        // Get the playername and gametype from shared data, so we can communicate to the server who we are and what we want to play,
        SharedData sharedData = SharedData.getInstance();
        out.println("login " + sharedData.getPlayer());
        System.out.println(stdIn.readLine());
        out.println("subscribe " + sharedData.getGameType());

        String response = stdIn.readLine();
        System.out.println(response);
        return response;
    }

    /**
     * Function that listens to the server, and gets the message from the server.
     * @return response (String) contains the response form the server.
     * @throws IOException
     */
    public String getMessage() throws IOException {

        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            return fromServer;
        }

        return "ERROR, no message found";

    }

    /**
     * Initial method main to test the connection.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClientConnectionController connection = new ClientConnectionController();

        // Start the connection and send the initial needed data.
        connection.startConnection();
        connection.sendStartData();
    }
}
