package com.fiktac.SmartPlugApp.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fiktac.SmartPlugApp.device.Device;
import com.fiktac.SmartPlugApp.device.DeviceRepository;

@RestController // This means that this class is a Controller
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/add", produces = "application/json")
    public @ResponseBody String addDevice (@RequestParam String name) {

        Device d = new Device();
        d.setName(name);
        deviceRepository.save(d);
        return "Saved";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
    public ResponseEntity<Iterable<Device>> getAllUsers() {
        // This returns a JSON or XML with the users
        return new ResponseEntity<>(deviceRepository.findAll(), HttpStatus.OK);
    }
}