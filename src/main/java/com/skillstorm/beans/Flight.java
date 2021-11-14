package com.skillstorm.beans;

import javax.print.attribute.standard.MediaSize.Other;

public class Flight implements Comparable<Flight> {

	private int id;
	private String airline;
	private String fromAirport;
	private String toAirport;
	private String departure;
	private String arrival;
	private String flightNumber;
	
	public Flight() {
		super();
	}

	public Flight(int id, String airline, String fromAirport, String toAirport, String departure, String arrival,
			String flightNumber) {
		super();
		this.id = id;
		this.airline = airline;
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.departure = departure;
		this.arrival = arrival;
		this.flightNumber = flightNumber;
	}

	public Flight(String airline, String fromAirport, String toAirport, String departure, String arrival,
			String flightNumber) {
		super();
		this.airline = airline;
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.departure = departure;
		this.arrival = arrival;
		this.flightNumber = flightNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public String getToAirport() {
		return toAirport;
	}

	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", airline=" + airline + ", fromAirport=" + fromAirport + ", toAirport=" + toAirport
				+ ", departure=" + departure + ", arrival=" + arrival + ", flightNumber=" + flightNumber + "]";
	}

	@Override
	public int compareTo(Flight other) {
		return this.id - other.id;
	}
	
	
	
	
	
	
	
}
