package com.skillstorm.service;

import java.sql.SQLException;
import java.util.Set;

import com.skillstorm.beans.Flight;
import com.skillstorm.dao.FlightsDAO;


public class FlightService {
	
	
	FlightsDAO dao = new FlightsDAO();
	
	public Flight createFlight(Flight flight) {
		try {
			return dao.create(flight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
	}
		
	public void updateFlight(String flightNum, String airline, int flightId) {
		
		dao.updateFlightNumber(flightNum, airline, flightId);
	}
	
	public void deleteFlight(int id) {
		dao.deleteByFlightNumber(id);
	}
	
	public Flight retrieveFlight(int id){
		return dao.findbyId(id);
	}
	
	public Set<Flight> retrieveAllFlights() {
		return dao.findAll();
	}

}
