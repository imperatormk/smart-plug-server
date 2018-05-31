package com.fiktac.SmartPlugApp.slot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiktac.SmartPlugApp.device_type.DeviceType;
import com.fiktac.SmartPlugApp.meter.Meter;
import com.fiktac.SmartPlugApp.state.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Slot implements Serializable {
    @ManyToOne
    @JoinColumn(name="meterId", nullable=false)
    @JsonIgnore
    private Meter meter;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long slotId;

    @OneToMany(mappedBy="slot")
    private List<State> states = new ArrayList<>();
    private Date lastUpdated = new Date();

    private Integer deviceType;

    public Long getId() {
        return this.slotId;
    }
    public List<State> getStates() { return this.states; }
    public Date getLastUpdated() {
        return this.lastUpdated;
    }
    public Meter getMeter() {
        return this.meter;
    }
    public Integer getDeviceType() { return this.deviceType; }

    public void setId(Long id) {
        this.slotId = id;
    }
    public void setStates(List<State> states) {
        this.states = states;
    }
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public void setMeter(Meter meter) { this.meter = meter; }
    public void setDeviceType(Integer deviceType) { this.deviceType = deviceType; }
}
