package com.musala.gateways.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MUSALA_DEVICES")
public class Device {

    @Id
    @Column(name = "device_id", nullable = false)
    private Long device_id;
    @Column(nullable = false)
    private String device_vendor;
    @Column(nullable = false)
    private LocalDate device_date_created;
    @Column(nullable = false)
    private boolean device_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gateway_id")
    @JsonIgnore
    private Gateway device_gateway;

    public Gateway getDevice_gateway() {
        return device_gateway;
    }

    public void setDevice_gateway(Gateway device_gateway) {
        this.device_gateway = device_gateway;
    }

    public Device() {
    }

    public Device(Long device_id, String device_vendor, LocalDate device_date_created, boolean device_status) {
        this.device_id = device_id;
        this.device_vendor = device_vendor;
        this.device_date_created = device_date_created;
        this.device_status = device_status;
    }

    public Long getDevice_id() {
        return device_id;
    }

    public String getDevice_vendor() {
        return device_vendor;
    }

    public LocalDate getDevice_date_created() {
        return device_date_created;
    }

    public boolean isDevice_status() {
        return device_status;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public void setDevice_vendor(String device_vendor) {
        this.device_vendor = device_vendor;
    }

    public void setDevice_date_created(LocalDate device_date_created) {
        this.device_date_created = device_date_created;
    }

    public void setDevice_status(boolean device_status) {
        this.device_status = device_status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "device_id=" + device_id +
                ", device_vendor='" + device_vendor + '\'' +
                ", device_dateCreated=" + device_date_created +
                ", device_status=" + device_status +
                '}';
    }
}
