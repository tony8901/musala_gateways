package com.musala.gateways.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MUSALA_DEVICES")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String vendor;
    @Column(nullable = false)
    private LocalDate dateCreated;
    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gateway.id")
    @JsonIgnore
    private Gateway gateway;

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public Device() {
    }

    public Device(Long id, String vendor, LocalDate dateCreated, boolean status) {
        this.id = id;
        this.vendor = vendor;
        this.dateCreated = dateCreated;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getVendor() {
        return vendor;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "device_id=" + id +
                ", device_vendor='" + vendor + '\'' +
                ", device_dateCreated=" + dateCreated +
                ", device_status=" + status +
                '}';
    }
}
