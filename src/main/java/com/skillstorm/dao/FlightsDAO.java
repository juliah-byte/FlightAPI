package com.skillstorm.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.skillstorm.beans.Flight;

public class FlightsDAO {
	//Remember to change database for testing purposes
	public final static String url = "jdbc:mysql://localhost:3306/flight-api";
	public final static String username = "root";
	public final static String password = "root";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Something bad happened while loading the driver");
			e.printStackTrace();
		}
	}

	public Flight create(Flight flight) throws SQLException{
			Connection conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			
			try {
			String sql = "insert into flights(AIRLINE, ORIGIN, DESTINATION, DEPARTURE_TIME, ARRIVAL_TIME, FLIGHT_NUMBER)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, flight.getAirline());
			stmt.setString(2, flight.getFromAirport());
			stmt.setString(3, flight.getToAirport());
			stmt.setString(4, flight.getDeparture());
			stmt.setString(5, flight.getArrival());
			stmt.setString(6, flight.getFlightNumber());
			stmt.executeUpdate();
			conn.commit();
			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int id = keys.getInt(1);
			flight.setId(id);
			
		}catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
			
		}
		return flight;
	}
	
	public void updateFlightNumber(String flightNum, String airline, int flightId) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "update flights set FLIGHT_NUMBER = ?, AIRLINE = ? where FLIGHT_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, flightNum);
			stmt.setString(2, airline);
			stmt.setInt(3, flightId);
			stmt.executeUpdate();
	
		}catch(SQLException e ) {
			
			e.printStackTrace();
			
		}
	}
		
	
	public void deleteByFlightNumber(int flightId) {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "delete from flights where FLIGHT_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flightId);
			stmt.executeUpdate();

		}catch(SQLException e ) {
			
			e.printStackTrace();
		}
		
	}
	
	public Flight findbyId(int id) {
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			Flight mine;
			String sql = "select * from flights where FLIGHT_ID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			mine = new Flight(rs.getInt("FLIGHT_ID"), rs.getString("AIRLINE"), 
						rs.getString("ORIGIN"), rs.getString("DESTINATION"), rs.getString("DEPARTURE_TIME"),
						rs.getString("ARRIVAL_TIME"), rs.getString("FLIGHT_NUMBER"));
						
			System.out.println(mine);
			return mine;
			}catch(SQLException e) {
			
			e.printStackTrace();
			return null;
		}	

	}
	
	public TreeSet<Flight> findAll(){
		Set<Flight> results = new TreeSet<>();
		try(Connection conn = DriverManager.getConnection(url, username, password)){
			String sql = "select * from flights order by FLIGHT_ID";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int flightId = rs.getInt("Flight_ID");
				String airline = rs.getString("AIRLINE");
				String origin = rs.getString("ORIGIN");
				String destination = rs.getString("DESTINATION");
				String departure_time = rs.getString("DEPARTURE_TIME");
				String arrival_time = rs.getString("ARRIVAL_TIME");
				String flight_number = rs.getString("FLIGHT_NUMBER");
				Flight flight = new Flight(flightId, airline, origin, destination, departure_time, arrival_time, flight_number);
				results.add(flight);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print(results);
		return (TreeSet<Flight>) results;
	}	

}