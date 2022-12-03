package com.musala.gateways;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
public class Gateway {

    @Id
    private String serialNumber;
    private String name;
    private int[] ip;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gateway", cascade = CascadeType.ALL)
    List<Device> devices;

    public Gateway() {
    }

    public Gateway(String serialNumber, String name, int[] ip) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.ip = ip;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getIp() {
        return ip;
    }

    public void setIp(int[] ip) {
        this.ip = ip;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", ip=" + Arrays.toString(ip) +
                '}';
    }
}
