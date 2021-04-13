package com.refine.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.refine.dao.DataBaseServices;
import com.refine.modle.*;

/**
 * Servlet implementation class CarServicesServlet
 */
@WebServlet(asyncSupported = true, name = "CarServlet", urlPatterns = { "/CarServicesServlet", "/api/car/*" })
public class CarServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarServicesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if (method.equals("GET")) {
			System.out.print(" CAR GetMethod");
			doGet(req, resp);
		} else if (method.equals("POST")) {
			System.out.println("Post Car");
			doPost(req, resp);
		} else if (method.equals("PUT")) {
			System.out.println("PUT Method Car");
			doPut(req, resp);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int carID = 0;
			if (request.getPathInfo() != null)
				carID = Integer.parseInt(request.getPathInfo().split("/")[1]);
			ArrayList<Car> cars = new ArrayList<Car>();

			if (carID > 0) {
				cars = DataBaseServices.getCarInfoById(carID);
			} else {
				cars = DataBaseServices.getAllCarInfo();
			}
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			JSONArray jo = new JSONArray(cars);
			pw.println(jo);
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
			JSONObject jo = new JSONObject(jb.toString());

			String result = DataBaseServices.saveCar(jo.get("ln").toString(), jo.get("pph").toString(),
					jo.get("cm").toString(), jo.get("mun").toString(), jo.get("sd").toString(),
					jo.get("bp").toString());
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();

			pw.println(result);
			doGet(request, response);

		} catch (Exception e) {
			e.getStackTrace();
		}
		// doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
