package com.fiktac.SmartPlugApp.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiktac.SmartPlugApp.slot.Slot;
import com.fiktac.SmartPlugApp.state.State;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device implements Serializable {
    @OneToOne
    @JoinColumn(name="slotId", nullable=true)
    @JsonIgnore
    private Slot slot;

    @OneToMany(mappedBy="device")
    private List<State> states = new ArrayList<>();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long deviceId;

    private String name;

    public Long getId() { return this.deviceId; }
    public String getName() { return this.name; }
    public Slot getSlot() { return this.getSlot(); }
    public List<State> getStates() { return this.states; }

    public void setId(Long id) { this.deviceId = id; }
    public void setName(String name) { this.name = name; }
    public void setSlot(Slot slot) { this.slot = slot; }
    public void setStates(List<State> states) { this.states = states; }

    public void addState(State state) { this.states.add(state); }
}
