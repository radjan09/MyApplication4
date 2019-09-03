package com.example.myapplication4;

public class Device {
    private String ip;
    private int port;
    private String name;
    public Device(String ip, int port, String name){
        this.ip=ip;
        this.port=port;
        this.name=name;
    }
    public int getPort() {
        return port;
    }
    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }
}
