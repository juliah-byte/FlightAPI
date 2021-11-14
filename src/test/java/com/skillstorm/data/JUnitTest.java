package com.skillstorm.data;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.skillstorm.beans.Flight;
import com.skillstorm.dao.FlightsDAO;

public class JUnitTest {
	
	FlightsDAO dao = new FlightsDAO();
	static Flight flight;
	
	

	
	@Test
	public void testFindbyId() {
		flight = dao.findbyId(9);
		
		assertEquals("United", flight.getAirline());
		assertEquals("SJC, San-Jose, CA", flight.getFromAirport());
		assertEquals("MSN, Madison, WI", flight.getToAirport());
		assertEquals("1110AM", flight.getDeparture());
		assertEquals("255PM", flight.getArrival());
		assertEquals("UA560", flight.getFlightNumber());

	}
	
	
	@Test
	public void testFindAll() {
	 Boolean isPopulated = dao.findAll().size() > 0 ? true:false;
	 assertTrue(isPopulated);

	}
	
	
	@Test
	public void testCreate() throws SQLException {
		
		Flight flight = new Flight("test", "test1", "test2", "test3", "test4", "test5");
		Flight flight2 = dao.create(flight);
		assertEquals(flight, flight2);
		
		
	}
	
	
	@Test
	public void testDeletebyId() {
		dao.deleteByFlightNumber(15);
		assertNull(dao.findbyId(15));
	}
	
	
	
	@Test
	public void testUpdateFlightNumber() {
		dao.updateFlightNumber("DL1099","Delta", 17);
		Flight flight1 = dao.findbyId(17);
		assertEquals("Delta", flight1.getAirline());
		assertEquals("DL1099", flight1.getFlightNumber());
	}
	

	

}
