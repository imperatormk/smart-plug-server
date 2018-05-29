package com.fiktac.SmartPlugApp.meter;

import com.fiktac.SmartPlugApp.slot.Slot;
import com.fiktac.SmartPlugApp.state.State;
import com.fiktac.SmartPlugApp.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiktac.SmartPlugApp.meter.Meter;
import com.fiktac.SmartPlugApp.meter.MeterRepository;

import com.fiktac.SmartPlugApp.slot.Slot;
import com.fiktac.SmartPlugApp.slot.SlotRepository;

import com.fiktac.SmartPlugApp.device.Device;
import com.fiktac.SmartPlugApp.device.DeviceRepository;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController // This means that this class is a Controller
@RequestMapping(value = "/meters", produces = "application/json")
public class MeterController {
    @Autowired
    private MeterRepository meterRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public ResponseEntity<Iterable<Meter>> getAllMeters() {
        return new ResponseEntity<>(meterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meter> getMeterById(@PathVariable Long id) {
        return new ResponseEntity<>(meterRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Meter> insertMeter(@RequestBody Meter meter) {
        Meter newMeter = meterRepository.save(meter);
        return new ResponseEntity<>(newMeter, HttpStatus.OK);
    }

    @GetMapping("/{meterId}/slots")
    public ResponseEntity<Iterable<Slot>> getSlots(@PathVariable Long meterId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            return new ResponseEntity<>(meter.get().getSlots(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{meterId}/slots")
    public ResponseEntity<Slot> insertSlot(@PathVariable Long meterId, @RequestBody Slot slot) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            slot.setMeter(meter.get());
            Slot newSlot = slotRepository.save(slot);
            return new ResponseEntity<>(newSlot, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Slot(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{meterId}/slots/{slotId}")
    public ResponseEntity<Slot> getSlot(@PathVariable Long meterId, @PathVariable Long slotId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                return new ResponseEntity<>(slot.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Slot(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Slot(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{meterId}/slots/{slotId}/devices")
    public ResponseEntity<Device> getDevice(@PathVariable Long meterId, @PathVariable Long slotId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                return new ResponseEntity<>(slot.get().getDevice(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Device(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Device(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{meterId}/slots/{slotId}/devices")
    public ResponseEntity<Device> getSlot(@PathVariable Long meterId, @PathVariable Long slotId, @RequestBody Device device) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                device.setSlot(slot.get());
                Device getDevice = deviceRepository.save(device);

                // TEMP
                State state = new State();
                state.setDevice(getDevice);
                state.setValue(new Long(7));
                stateRepository.save(state);
                // TEMP

                return new ResponseEntity<>(getDevice, HttpStatus.OK);
            }
            return new ResponseEntity<>(new Device(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Device(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{meterId}/slots/{slotId}/devices")
    public ResponseEntity<Boolean> deleteDevice (@PathVariable Long meterId, @PathVariable Long slotId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                Slot getSlot = slot.get();
                deviceRepository.deleteById(getSlot.getDevice().getId());

                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}