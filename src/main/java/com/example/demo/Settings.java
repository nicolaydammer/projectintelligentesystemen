package com.example.demo;

public class Settings {
    public String hostName = "145.33.225.170";
    public int port = 7789;

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }
}
