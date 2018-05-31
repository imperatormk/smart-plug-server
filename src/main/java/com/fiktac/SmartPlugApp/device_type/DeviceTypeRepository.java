package com.fiktac.SmartPlugApp.device_type;

import java.util.ArrayList;
import java.util.List;

public class DeviceTypeRepository {
    public List<DeviceType> deviceTypes = new ArrayList<>();

    public DeviceTypeRepository() {
        deviceTypes.add(new DeviceType(1, "TV"));
        deviceTypes.add(new DeviceType(2, "A/C"));
        deviceTypes.add(new DeviceType(3, "Bass Amp"));
        deviceTypes.add(new DeviceType(4, "Fridge"));
        deviceTypes.add(new DeviceType(5, "Fan"));
    }

    public List<DeviceType> getDeviceTypes() {
        return this.deviceTypes;
    }
}
