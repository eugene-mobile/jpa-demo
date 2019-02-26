package com.ecobee.test.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecobee.test.demo.api.Contact;
import com.ecobee.test.demo.api.Location;
import com.ecobee.test.demo.api.State;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

	@Autowired
	private ContactRepository _repo;
	
	@Test
	public void createContactSuccess() {
		Contact contact = newContact();
		contact = _repo.save(contact);
		
		Optional<Contact> loadResult = _repo.findById(contact.getId());
		assertTrue(loadResult.isPresent());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void createContactConstraintFailure() {
		Contact contact = newContact();
		contact.setSensorId(null);
		contact = _repo.save(contact);
	}

	@Test
	public void testUpdateContactSuccess() {
		State oldState = State.STATE_A;
		State newState = State.STATE_B;

		Contact contact = newContact();
		contact.setState(oldState);
		contact = _repo.save(contact);
		
		assertEquals(oldState, _repo.findById(contact.getId()).get().getState());
		
		contact.setState(newState);
		_repo.save(contact);
		assertEquals(newState, _repo.findById(contact.getId()).get().getState());
	}
	
	@Test
	public void testFindContactByHomeIdAndSensorIdSuccess() {
		Contact contact = newContact();
		String homeID = contact.getHomeId();
		String sensorID = contact.getSensorId();
		_repo.save(contact);
		
		Optional<Contact> result = _repo.findContactByHomeIdAndSensorId(homeID, sensorID);
		assertTrue(result.isPresent());
	}
	
	@Test
	public void testFindContactByHomeIdAndSensorIdFailure() {
		Contact contact = newContact();
		String homeID = contact.getHomeId();

		_repo.save(contact);
		
		Optional<Contact> result = _repo.findContactByHomeIdAndSensorId(homeID, UUID.randomUUID().toString());
		assertFalse("Contact was found by wrong sensorID", result.isPresent());
	}
	
	@Test
	public void deleteContactHomeIdAndSensorIdSuccess() {
		Contact contact = newContact();
		String homeID = contact.getHomeId();
		String sensorID = contact.getSensorId();
		_repo.save(contact);
		
		_repo.deleteContactByHomeIdAndSensorId(homeID, sensorID);
		
		Optional<Contact> result = _repo.findContactByHomeIdAndSensorId(homeID, sensorID);
		assertFalse("Contact was not deleted", result.isPresent());
	}
	
	@Test
	public void deleteContactByWrongHomeIdAndSensorId() {
		Contact contact = newContact();
		String homeID = contact.getHomeId();
		String sensorID = contact.getSensorId();
		_repo.save(contact);
		
		_repo.deleteContactByHomeIdAndSensorId(UUID.randomUUID().toString(), sensorID);
		
		Optional<Contact> result = _repo.findContactByHomeIdAndSensorId(homeID, sensorID);
		assertTrue("Contact was deleted but shouldn't be", result.isPresent());
	}
	
	private Contact newContact() {
		Contact contact = new Contact();
		contact.setHomeId(UUID.randomUUID().toString());
		contact.setSensorId(UUID.randomUUID().toString());
		contact.setLocation(Location.HEAR);
		contact.setState(State.STATE_A);
		return contact;
	}
	
}
