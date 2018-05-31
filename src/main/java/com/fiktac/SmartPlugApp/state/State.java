package com.fiktac.SmartPlugApp.state;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiktac.SmartPlugApp.slot.Slot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long stateId;

    @ManyToOne
    @JoinColumn(name="slotId", nullable=false)
    @JsonIgnore
    private Slot slot;

    private Integer deviceType;
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
    public Integer getDeviceType() { return this.deviceType; }
    public Slot getSlot() { return this.slot; }

    public void setId(Long id) {
        this.stateId = id;
    }
    public void setValue(Long value) {
        this.value = value;
    }
    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }
    public void setDeviceType(Integer deviceType) { this.deviceType = deviceType; }
    public void setSlot(Slot slot) { this.slot = slot; }
}
