package com.fiktac.SmartPlugApp.meter;

import org.springframework.data.repository.CrudRepository;
import com.fiktac.SmartPlugApp.meter.Meter;

public interface MeterRepository extends CrudRepository<Meter, Long> {

}