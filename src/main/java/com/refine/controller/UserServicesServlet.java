package com.refine.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.refine.dao.*;
import com.refine.modle.*;
import java.util.*;
import org.json.*;

/**
 * Servlet implementation class UserServicesServlet
 */
@WebServlet(asyncSupported = true, name = "UserServlet", urlPatterns = { "/UserServlet", "/api/user/*" })
public class UserServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServicesServlet() {
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
			System.out.print("GetMethod");
			doGet(req, resp);
		} else if (method.equals("POST")) {
			System.out.println("Post");
			doPost(req, resp);
		} else if (method.equals("PUT")) {
			System.out.println("PUT Method");
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			int userID = 0;
			if (request.getPathInfo() != null)
				userID = Integer.parseInt(request.getPathInfo().split("/")[1]);
			ArrayList<User> users = new ArrayList<User>();

			if (userID > 0) {
				users = DataBaseServices.getUserInfoById(userID);
			} else {
				users = DataBaseServices.getAllUserInfo();
			}
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			JSONArray jo = new JSONArray(users);
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
		// TODO Auto-generated method stub

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);

			System.out.print(jb);
			JSONObject jo = new JSONObject(jb.toString());
			System.out.print(jo.get("id"));

			String result = DataBaseServices.saveUser(jo.get("id").toString(), jo.get("mobile").toString());
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();

			pw.println(result);
			doGet(request, response);

		} catch (Exception e) {
			/* report an error */ }
		// doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("User do put");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
