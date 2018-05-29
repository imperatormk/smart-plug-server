package com.fiktac.SmartPlugApp.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiktac.SmartPlugApp.device.Device;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class State implements Serializable {
    @ManyToOne
    @JoinColumn(name="deviceId", nullable=false)
    @JsonIgnore
    private Device device;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long stateId;

    private Long value;
    private Date datePosted = new Date();

    public Long getId() {
        return this.stateId;
    }
    public Long getValue() {
        return this.value;
    }
    public Date getDatePosted() {
        return this.datePosted;
    }
    public Device getDevice() { return this.device; }

    public void setId(Long id) {
        this.stateId = id;
    }
    public void setValue(Long value) {
        this.value = value;
    }
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
    public void setDevice(Device device) { this.device = device; }
}
