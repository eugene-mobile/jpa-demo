/*******************************************************************************
 * Copyright (c) ecobee, Inc. 2017. All rights reserved.
 *******************************************************************************/
package com.ecobee.test.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecobee.test.demo.api.Location;
import com.ecobee.test.demo.api.Sensor;
import com.ecobee.test.demo.api.SensorType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorRepositoryTest {

	@Autowired
	private SensorRepository _repository;
	
	@Test
	public void testInsert() {
		long countBefore = _repository.count();
		
		Sensor sensor = new Sensor(null, SensorType.TYPE1, Location.THERE, "My sensor", LocalDateTime.now());
		sensor = _repository.save(sensor);
		
		assertNotNull(sensor.getId());
		
		assertEquals(countBefore+1, _repository.count());
	}
	
	@Test
	public void testFindById() {
		Sensor sensor = new Sensor(null, SensorType.TYPE2, Location.THERE, "My sensor", LocalDateTime.now());
		sensor = _repository.save(sensor);
		
		Optional<Sensor> saved = _repository.findById(sensor.getId());
		assertTrue(saved.isPresent());
	}
	
	
}
