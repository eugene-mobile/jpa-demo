/*******************************************************************************
 * Copyright (c) ecobee, Inc. 2017. All rights reserved.
 *******************************************************************************/
package com.ecobee.test.demo.api;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class SensorPairingEndpoint {
	
	@PostMapping(path="/home/{homeId}")
	public void addSensorToHome(
			@PathVariable("homeId") String homeId,
			@RequestBody Sensor sensor
		) {
		System.out.println("Posting sensor to home "+homeId);
		try {
			System.out.println("   "+new ObjectMapper().writeValueAsString(sensor));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@GetMapping(path="/home/{homeId}", produces="application/json")
	public Collection<Sensor> getHomeSensors(
			@PathVariable("homeId") String homeId
		) {
		System.out.println("Loading sensors for home "+homeId);
		
		return Collections.singleton(
				new Sensor(
						1L,
						SensorType.TYPE1,
						Location.HEAR,
						"Some sensor",
						LocalDateTime.now()
			));
		
	}
	
	@DeleteMapping(path="/home/{homeId}/sensors/{sensorId}")
	public void deleteSensorFromHome(
			@PathVariable("homeId") String homeId,
			@PathVariable("sensorId") String sensorId
		) {
		System.out.println("Deleting sensor "+sensorId+" from home "+homeId);
	}
	
	
}
