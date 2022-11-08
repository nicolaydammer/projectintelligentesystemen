package com.example.demo;

import java.io.*;
import java.net.*;

public class ClientConnectionController {
    // Initialise and get the required settings and variables.
    SharedData sharedData = SharedData.getInstance();
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
     *
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
     * Function that closes the connection between the client and the server.
     *
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
     *
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
     * And wait for a game, if searching for a game takes longer than 5 minutes stop.
     * @return response (String) contain the response from the server.
     * @throws IOException
     */
    public Boolean sendStartData() throws IOException {
        // Get the playername and gametype from shared data, so we can communicate to the server who we are and what we want to play,
        out.println("login " + sharedData.getPlayer().getName());
        out.println("subscribe " + sharedData.getGameType());

        final long NANOSEC_PER_SEC = 1000l*1000*1000;

        long startTime = System.nanoTime();
        while ((System.nanoTime()-startTime)< 5*60*NANOSEC_PER_SEC){
            fromServer = in.readLine();
            if(fromServer.contains("SVR GAME MATCH")) {
                System.out.println(fromServer);
                String[] part = fromServer.split("\"", 3);
                System.out.println("test in checkStartingPlayer: " + part[1]);
                if (part[1].equals(sharedData.getPlayer().getName())) {
                    sharedData.setStartingPlayer(true);
                } else {
                    sharedData.setStartingPlayer(false);
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Function that checks who starts first.
     * @return boolean
     * @throws IOException
     */
    public boolean checkStartingPlayer() throws IOException {

        while (true) {
            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);

            if (fromServer.contains("SVR")) {
                String[] part = fromServer.split("\"", 3);
                System.out.println("test in checkStartingPlayer: " + part[1]);
                if (part[1].equals(sharedData.getPlayer().getName())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Function that listens to the server, and gets the message from the server.
     *
     * @return response (String) contains the response form the server.
     * @throws IOException
     */
    public String checkTurn() throws IOException {
        while(true) {
            fromServer = in.readLine();
            String[] part = fromServer.split("\"", 3);
            System.out.println(part[2]);
            if(part[2].contains("MOVE:")) {
                return part[2].substring(2, part[2].lastIndexOf(','));
            }
            if(fromServer.contains("YOURTURN")) {
                return "Jij moet een zet doen!";
            }
//            if(fromServer.contains("SVR GAME LOSS") || fromServer.contains(("SVR GAME WIN"))) {
//                return sendMessage("bye");
//            }
        }
    }

    /**
     * Initial method main to test the connection.
     *
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