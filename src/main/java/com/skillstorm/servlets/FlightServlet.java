package com.skillstorm.servlets;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import com.skillstorm.beans.Flight;
import com.skillstorm.dao.FlightsDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.service.FlightService;


@WebServlet("/api/flight")
public class FlightServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	FlightService serv = new FlightService();
	FlightsDAO dao = new FlightsDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("id") != null) {
			String param = req.getParameter("id");
			int id = Integer.parseInt(param);
		 	Flight flight = serv.retrieveFlight(id);
			String json = new ObjectMapper().writeValueAsString(flight);
			resp.getWriter().print(json);
			resp.setStatus(200);
			resp.setContentType("application/json");
		}else {
			Set<Flight> flights = dao.findAll();
			String json = new ObjectMapper().writeValueAsString(flights);
			resp.getWriter().print(json);
			resp.setContentType("application/json");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream requestBody = req.getInputStream();
		Flight flight = new ObjectMapper().readValue(requestBody, Flight.class);
		Flight updated = serv.createFlight(flight);
		resp.setStatus(201); // "return"
		resp.setContentType("application/json");
		resp.getWriter().print(new ObjectMapper().writeValueAsString(updated));

	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("id") != null) {
			String param1 = req.getParameter("id");
			String param2 = req.getParameter("airline");
			String param3 = req.getParameter("fid");
			int id = Integer.parseInt(param1);
			serv.updateFlight(param3, param2, id);
			resp.setStatus(201);
			resp.setContentType("application/json");
		}else {
			System.out.println("The entered flight id does not exist.");
			resp.setStatus(400);
		}
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//String clientOrigin = req.getHeader("origin");
		
		if(req.getParameter("id") != null) {
			String param = req.getParameter("id");
			int id = Integer.parseInt(param);
			serv.deleteFlight(id);
//			resp.setContentType("application/json");
//			resp.setCharacterEncoding("UTF-8");
//			resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//			resp.addHeader("Access-Control-Max-Age", "1728000");
//			resp.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
//			resp.setHeader("Access-Control-Allow-Origin", "*");
//			resp.setHeader("Access-Control-Allow-Methods", "DELETE");
//			resp.setHeader("Access-Control-Allow-Origin", "Content-Type");
//			resp.setHeader("Access-Control-Max-Age", "86400");
			resp.setStatus(200);
//			resp.setContentType("application/json");
		}else {
			System.out.println("The entered flight id does not exist.");
			resp.setStatus(400);
			resp.setContentType("application/json");
		}
	}
	
	

}
