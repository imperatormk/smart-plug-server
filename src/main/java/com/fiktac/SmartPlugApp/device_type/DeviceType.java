package com.fiktac.SmartPlugApp.device_type;

import java.io.Serializable;

public class DeviceType implements Serializable {
    private Integer typeId;
    private String name;

    public DeviceType() {
    }

    public DeviceType(Integer typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public Integer getTypeId() { return this.typeId; }
    public String getName() { return this.name; }

    public void setTypeId(Integer id) { this.typeId = id; }
    public void setName(String name) { this.name = name; }
}
