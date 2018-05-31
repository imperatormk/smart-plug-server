package com.fiktac.SmartPlugApp.device_type;

import com.fiktac.SmartPlugApp.device_type.DeviceType;
import com.fiktac.SmartPlugApp.device_type.DeviceTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/deviceTypes", produces = "application/json")
public class DeviceTypeController {
    private DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    @GetMapping
    @CrossOrigin(origins="*")
    public ResponseEntity<Iterable<DeviceType>> getAllDeviceTypes() {
        return new ResponseEntity<>(deviceTypeRepository.getDeviceTypes(), HttpStatus.OK);
    }
}