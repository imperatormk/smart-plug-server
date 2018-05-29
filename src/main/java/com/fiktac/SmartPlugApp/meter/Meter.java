package com.fiktac.SmartPlugApp.meter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiktac.SmartPlugApp.slot.Slot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meter implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long meterId;

    @OneToMany(mappedBy="meter")
    private List<Slot> slots = new ArrayList<>();

    private String name;

    public Long getId() {
        return this.meterId;
    }
    public String getName() {
        return this.name;
    }
    public List<Slot> getSlots() {
        return this.slots;
    }

    public void setId(Long id) {
        this.meterId = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSlots(List<Slot> slots) { this.slots = slots; }
}
