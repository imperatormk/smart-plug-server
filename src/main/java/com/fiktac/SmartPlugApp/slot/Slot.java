package com.fiktac.SmartPlugApp.slot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiktac.SmartPlugApp.meter.Meter;
import com.fiktac.SmartPlugApp.device.Device;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Slot implements Serializable {
    @ManyToOne
    @JoinColumn(name="meterId", nullable=false)
    @JsonIgnore
    private Meter meter;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long slotId;

    private Long totalState;
    private Date lastUpdated = new Date();

    @OneToOne(mappedBy="slot")
    private Device device;

    public Long getId() {
        return this.slotId;
    }
    public Long getTotalState() {
        return this.totalState;
    }
    public Date getLastUpdated() {
        return this.lastUpdated;
    }
    public Meter getMeter() {
        return this.meter;
    }
    public Device getDevice() { return this.device; }

    public void setId(Long id) {
        this.slotId = id;
    }
    public void setTotalState(Long totalState) {
        this.totalState = totalState;
    }
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public void setMeter(Meter meter) { this.meter = meter; }
    public void setDevice(Device device) { this.device = device; }
}
