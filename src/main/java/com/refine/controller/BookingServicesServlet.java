package com.refine.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.refine.dao.DataBaseServices;
import com.refine.modle.CarBooking;
import com.refine.modle.User;

/**
 * Servlet implementation class BookingServicesServlet
 */

@WebServlet(
		asyncSupported = true, 
		name = "BookingServlet", 
		urlPatterns = { 
				"/BookingServicesServlet", 
				"/api/booking/*"
		})
public class BookingServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equals("GET")) {
			System.out.print(" booking GetMethod");
			doGet(req, resp);
		} else if (method.equals("POST")) {
			System.out.println("Post booking");
			doPost(req, resp);
		} else if (method.equals("PUT")) {
			System.out.println("PUT Method booking");
			doPut(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int userID = 0;
			int carID = 0;
			if (request.getPathInfo() != null)
			{
				String[] arrOfStr = request.getPathInfo().split("/");
				System.out.println(request.getPathInfo());
				if(arrOfStr[1].toString().toLowerCase().equals("car"))
				{
					System.out.println(carID);
					carID = Integer.parseInt(arrOfStr[2]);
				}
				else if(arrOfStr[1].toString().toLowerCase().equals("user"))
				{
					userID = Integer.parseInt(arrOfStr[2]);
				}
				else
				{
					carID = Integer.parseInt(arrOfStr[1]);
					userID = Integer.parseInt(arrOfStr[2]);
				}
				//System.out.println(carID);
				//System.out.println(userID);
				//userID = Integer.parseInt(request.getPathInfo().split("/")[1]);
			}
			ArrayList<CarBooking> carBookings = new ArrayList<CarBooking>();

			carBookings = DataBaseServices.getcarBookingInfo(userID,carID);
	
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			JSONArray jo = new JSONArray(carBookings);
			pw.println(jo);
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			System.out.print(jb);
			JSONObject jo = new JSONObject(jb.toString());
			String result = DataBaseServices.createBooking(jo.get("userid").toString(), jo.get("carid").toString(),jo.get("start").toString(),jo.get("end").toString());
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();

			pw.println(result);
			doGet(request, response);

		} catch (Exception e) {
			e.getStackTrace();
			/* report an error */ }
		// doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
