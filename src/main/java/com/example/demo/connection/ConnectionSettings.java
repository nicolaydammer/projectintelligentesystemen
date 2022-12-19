package com.example.demo.connection;

public class ConnectionSettings {

    // Initialise DEFAULT LOCAL TEST settings.
    public String hostName = "localhost";
    public int port = 8082;

    /** ONLY use this to set the port and hostname to YOUR OWN settings.
     * @option local = Uses these settings that you set above to connect to your local server properties
     * @option server = Uses the default settings to connect to Hanzehogeschool Groningen their server properties.
     * options should be lowercase.
    */
    public String environment = "local";

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setEnvironment() {this.environment = environment; }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public String getEnvironment() { return environment; }
}
