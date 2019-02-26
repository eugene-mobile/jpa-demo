/*******************************************************************************
 * Copyright (c) ecobee, Inc. 2017. All rights reserved.
 *******************************************************************************/
package com.ecobee.test.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecobee.test.demo.api.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Long> {}
