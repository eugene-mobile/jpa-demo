package com.ecobee.test.demo.dao;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.ecobee.test.demo.api.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {

	public Optional<Contact> findContactByHomeIdAndSensorId(String homeId, String sensorId);
	
	@Transactional(value=TxType.REQUIRED)
	public void deleteContactByHomeIdAndSensorId(String homeId, String sensorId);
	
}
