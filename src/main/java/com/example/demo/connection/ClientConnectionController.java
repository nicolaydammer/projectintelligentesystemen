package com.example.demo.connection;

import com.example.demo.data.SharedData;

import java.io.*;
import java.net.*;

public class ClientConnectionController {
    // Initialise and get the required settings and variables.
    SharedData sharedData = SharedData.getInstance();
    ConnectionSettings settings = new ConnectionSettings();
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
        getEnv();
        // Open the sockets and make connection to the server.
        gameServerSocket = new Socket(hostName, portNumber);
        out = new PrintWriter(gameServerSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(gameServerSocket.getInputStream()));
        stdIn = new BufferedReader(new InputStreamReader(System.in));

        // If there is a response from the server, display it.
        if ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
        }

        // Log the player in.
        out.println("login " + sharedData.getPlayer().getName());
    }

    /**
     * Get the environment and set the host/port if needed.
     */
    private void getEnv() {
        // if the settings are for a real server, set those settings.
        if (settings.getEnvironment().equals("server")) {
            settings.setHostName("145.33.225.170");
            settings.setPort(7789);
        }
    }

    /**
     * Function that closes the connection between the client and the server.
     *
     * @throws IOException
     */
    public void stopConnection() throws IOException {

        System.out.println("Connection closing..");
        System.exit(1);
        System.out.println("Connection between you and " + settings.hostName + " have been successfully closed.");

    }

    /**
     * Function that sends a message to the server and returns the response.
     *
     * @param message (String) contains the message that you want to serve to the server.
     * @return response (String) contains the response from the server.
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        // Send a message to the server, and return the response given from the server.
        out.println(message);
    }

    /**
     * Function that sends the initial required data such as player name and game mode to the server.
     * And wait for a game, if searching for a game takes longer than 5 minutes stop.
     * @return response (String) contain the response from the server.
     * @throws IOException
     */
    public Boolean sendStartData() throws IOException {

        // Get the playername and gametype from shared data, so we can communicate to the server who we are and what we want to play,
        if (!sharedData.isTournementMode()) {
            out.println("subscribe " + sharedData.getGameType());
        }
        final long NANOSEC_PER_SEC = 1000l*1000*1000;
        long startTime = System.nanoTime();
        while ((System.nanoTime()-startTime)< 5*60*NANOSEC_PER_SEC){
            fromServer = in.readLine();
            if(fromServer.contains("SVR GAME MATCH")) {
                System.out.println(fromServer);
                String[] part = fromServer.split("\"", 3);
                System.out.println("test in checkStartingPlayer: " + part[1]);
                if (part[1].equals(sharedData.getPlayer().getName())) { sharedData.setStartingPlayer(true); }

                return true;
            }
        }
        return false;
    }

    public void subscribeToGame () {
        if(sharedData.getGameType().equals("tic-tac-toe")) {
            new TicTacToeConnection();
        }

        if(sharedData.getGameType().equals("othello")) {
            new OthelloConnection();
        }
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
                if (part[1].equals(sharedData.getPlayer().getName())) { return true; }
                return false;
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
            fromServer = in.readLine();
            System.out.println("Server: " + fromServer);
            String[] part = fromServer.split("\"", 3);

            if (part.length > 1) {
                if (part[2].contains("MOVE:") && !part[1].equals(sharedData.getPlayer().getName())) {
                    return "Zet tegenstander: " + part[2].substring(2, part[2].lastIndexOf(','));
                }
            }
            if(fromServer.contains("TURNMESSAGE:")) {
                System.out.println(fromServer + " dit is in checkturn");
                return "Jij moet een zet doen!";
            }
            if(fromServer.contains("SVR GAME LOSS")){
                return "Je hebt verloren";
            }

            if(fromServer.contains("SVR GAME WIN")) {
                return "Je hebt gewonnen";
            }

            if(fromServer.contains("SVR GAME DRAW")){
                return "Gelijkspel";
            }

        return fromServer;
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