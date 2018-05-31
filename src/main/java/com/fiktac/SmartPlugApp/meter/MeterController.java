package com.fiktac.SmartPlugApp.meter;

import com.fiktac.SmartPlugApp.device_type.DeviceType;
import com.fiktac.SmartPlugApp.device_type.DeviceTypeRepository;
import com.fiktac.SmartPlugApp.slot.Slot;
import com.fiktac.SmartPlugApp.state.State;
import com.fiktac.SmartPlugApp.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiktac.SmartPlugApp.slot.SlotRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/meters", produces = "application/json")
public class MeterController {
    @Autowired
    private MeterRepository meterRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private StateRepository stateRepository;

    private DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

    @GetMapping
    @CrossOrigin(origins="*")
    public ResponseEntity<Iterable<Meter>> getAllMeters() {
        return new ResponseEntity<>(meterRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Meter> getMeterById(@PathVariable Long id) {
        return new ResponseEntity<>(meterRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @CrossOrigin(origins="*")
    public ResponseEntity<Meter> insertMeter(@RequestBody Meter meter) {
        Meter newMeter = meterRepository.save(meter);
        return new ResponseEntity<>(newMeter, HttpStatus.OK);
    }

    @GetMapping("/{meterId}/slots")
    @CrossOrigin(origins="*")
    public ResponseEntity<Iterable<Slot>> getSlots(@PathVariable Long meterId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            return new ResponseEntity<>(meter.get().getSlots(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{meterId}/slots")
    @CrossOrigin(origins="*")
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
    @CrossOrigin(origins="*")
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

    @PutMapping("/{meterId}/slots/{slotId}")
    @CrossOrigin(origins="*")
    public ResponseEntity<Slot> insertSlot(@PathVariable Long meterId, @PathVariable Long slotId, @RequestBody DeviceType deviceType) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                Slot getSlot = slot.get();
                if (deviceType != null) {
                    getSlot.setDeviceType(deviceType.getTypeId());
                } else {
                    getSlot.setDeviceType(null);
                }
                getSlot.setLastUpdated(new Date());
                getSlot = slotRepository.save(getSlot);
                return new ResponseEntity<>(getSlot, HttpStatus.OK);
            }
            return new ResponseEntity<>(new Slot(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Slot(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{meterId}/slots/{slotId}")
    @CrossOrigin(origins="*")
    public ResponseEntity<State> insertSlot(@PathVariable Long meterId, @PathVariable Long slotId, @RequestBody State state) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if (meter.isPresent()) {
            Optional<Slot> slot = slotRepository.findById(slotId);
            if (slot.isPresent()) {
                Slot getSlot = slot.get();
                if (getSlot.getDeviceType() != null) {
                    state.setDeviceType(getSlot.getDeviceType());
                    state.setSlot(getSlot);

                    getSlot.setLastUpdated(new Date());
                    slotRepository.save(getSlot);

                    State getState = stateRepository.save(state);
                    return new ResponseEntity<>(getState, HttpStatus.OK);
                }
                return new ResponseEntity<>(new State(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new State(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new State(), HttpStatus.NOT_FOUND);
    }
}