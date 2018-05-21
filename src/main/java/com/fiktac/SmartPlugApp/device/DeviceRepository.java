package com.fiktac.SmartPlugApp.device;

import org.springframework.data.repository.CrudRepository;
import com.fiktac.SmartPlugApp.device.Device;

public interface DeviceRepository extends CrudRepository<Device, Long> {

}
