/*******************************************************************************
 * Copyright (c) ecobee, Inc. 2017. All rights reserved.
 *******************************************************************************/
package com.ecobee.test.demo.api;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="home_sensors")
public class Sensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private SensorType type;
	private Location location;
	private String name;
	private LocalDateTime timestamp;

	public Sensor() {
		super();
	}
	public Sensor(Long id, SensorType type, Location location, String name, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.type = type;
		this.location = location;
		this.name = name;
		this.timestamp = timestamp;
	}
	public SensorType getType() {
		return type;
	}
	public void setType(SensorType type) {
		this.type = type;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
