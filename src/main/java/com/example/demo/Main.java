package com.example.demo;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Inform user
        System.out.println("Welkom op de game client, ik heb eerst een aantal informatie nodig om op te kunnen starten :)");
        System.out.println("Ik wil graag eerst weten welke server ik connectie moet maken");
        Scanner config = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Voer in: <hostname> <port>");

        // Get settings class
        Settings settings = new Settings();

        // set the settings.
        settings.setHostName(config.nextLine());
        settings.setPort(config.nextInt());

        System.out.println("Je hebt de volgende gegevens ingevuld:\nHostname = " + settings.getHostName() + "\nPort = " + settings.getPort());
        System.out.println("connectie maken....");

        // Start the connection, you already have the hostname and port (getHostName and getPortName). ConnectionController already has this
        // information in the file since this comes from 'Settings'.
        ClientConnectionController connection = new ClientConnectionController();
        connection.connection(settings.getHostName(), settings.getPort());

    }
}
