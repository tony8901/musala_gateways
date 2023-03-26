package com.musala.gateways.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MUSALA_GATEWAYS")
public class Gateway {

    @Id
    @Column(nullable = false)
    private String gateway_id;
    @Column(nullable = false)
    private String gateway_name;
    @Column(nullable = false)
    private String gateway_ip;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device_gateway", cascade = CascadeType.ALL)
    private List<Device> gateway_devices = new ArrayList<>();

    public List<Device> getGateway_devices() {
        return gateway_devices;
    }

    public void setGateway_devices(List<Device> gateway_devices) {
        this.gateway_devices = gateway_devices;
    }

    public Gateway() {
    }

    public Gateway(String gateway_id, String gateway_name, String gateway_ip) {
        this.gateway_id = gateway_id;
        this.gateway_name = gateway_name;
        this.gateway_ip = gateway_ip;
    }

    public String getGateway_id() {
        return gateway_id;
    }

    public String getGateway_name() {
        return gateway_name;
    }

    public String getGateway_ip() {
        return gateway_ip;
    }

    public void setGateway_id(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public void setGateway_name(String gateway_name) {
        this.gateway_name = gateway_name;
    }

    public void setGateway_ip(String gateway_ip) {
        this.gateway_ip = gateway_ip;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "gateway_id='" + gateway_id + '\'' +
                ", gateway_name='" + gateway_name + '\'' +
                ", gateway_ip='" + gateway_ip + '\'' +
                '}';
    }
}

