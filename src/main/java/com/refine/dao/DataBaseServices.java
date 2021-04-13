package com.refine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import com.refine.modle.*;
import com.refine.db.DataBaseConnection;

public class DataBaseServices {

	public static ArrayList<User> getAllUserInfo() {
		ArrayList<User> users = new ArrayList<User>();
		User obj;
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Select * from user";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				obj = new User();
				obj.setUserID(Integer.parseInt(res.getString("ID")));
				obj.setMobileNumber(Integer.parseInt(res.getString("Mobile")));
				users.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static ArrayList<User> getUserInfoById(int userID) {
		ArrayList<User> users = new ArrayList<User>();
		User obj;
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Select * from user where ID = " + userID;
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				obj = new User();
				obj.setUserID(Integer.parseInt(res.getString("ID")));
				obj.setMobileNumber(Integer.parseInt(res.getString("Mobile")));
				users.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static String saveUser(String id, String mobile) {
		// TODO Auto-generated method stub
		String result = "Failed";
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Insert into user(id,mobile) values(" + id + "," + mobile + ")";
			PreparedStatement stmt = con.prepareStatement(query);
			int res = stmt.executeUpdate();

			if (res > 0)
				result = "User has been Saved";
			else
				result = "User Not saved";

		} catch (Exception e) {
			e.printStackTrace();
			result = "Some error Ocuured!!" + e.getMessage();
		}
		return result;

	}

	public static ArrayList<Car> getCarInfoById(int carID) {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car obj;
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Select * from cars where car_id = " + carID;
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				obj = new Car();
				obj.setCarID(Integer.parseInt(res.getString("CAR_ID")));
				obj.setCarLienceNumber(res.getString("CAR_LICENSE_NO"));
				obj.setCarModel(res.getString("MODEL"));
				obj.setManufacturer(res.getString("MANUFACTURER"));
				obj.setBasePrice(Integer.parseInt(res.getString("BASE_PRICE")));
				obj.setPricePerHour(Integer.parseInt(res.getString("PPH")));
				obj.setSecurityDeposit(Integer.parseInt(res.getString("SECURITY_DEPOSIT")));
				cars.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cars;
	}

	public static ArrayList<Car> getAllCarInfo() {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car obj;
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Select * from cars";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				obj = new Car();
				obj.setCarID(Integer.parseInt(res.getString("CAR_ID")));
				obj.setCarLienceNumber(res.getString("CAR_LICENSE_NO"));
				obj.setCarModel(res.getString("MODEL"));
				obj.setManufacturer(res.getString("MANUFACTURER"));
				obj.setBasePrice(Integer.parseInt(res.getString("BASE_PRICE")));
				obj.setPricePerHour(Integer.parseInt(res.getString("PPH")));
				obj.setSecurityDeposit(Integer.parseInt(res.getString("SECURITY_DEPOSIT")));
				cars.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cars;
	}

	public static String saveCar(String ln, String pph, String cm, String mun, String sd, String bp) {
		String result = "Failed";
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "Insert into cars(CAR_LICENSE_NO,MANUFACTURER,MODEL,BASE_PRICE,PPH,SECURITY_DEPOSIT)values('"
					+ ln + "','" + mun + "','" + cm + "'," + bp + "," + pph + "," + sd + ")";
			System.out.println(query);
			PreparedStatement stmt = con.prepareStatement(query);
			int res = stmt.executeUpdate();

			if (res > 0)
				result = "Car has been Saved";
			else
				result = "Car Not saved";

		} catch (Exception e) {
			e.printStackTrace();
			result = "Some error Ocuured!!" + e.getMessage();
		}
		return result;
	}

	public static String createBooking(String userID, String carID, String startDate, String endDate) {
		// TODO Auto-generated method stub
		String result = "Failed";
		try {
			Connection con = DataBaseConnection.initializeDatabase();
			String query = "";
			PreparedStatement stmt;
			ResultSet rs;

			// Check for the user
			query = "Select * from User where ID = " + userID;
			System.out.println(query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.last();
			System.out.println(rs.getRow());
			if (rs.getRow() <= 0) {
				result = "User Not Found";
				return result;
			}

			// check for car

			query = "Select * from cars where car_id = " + carID;
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.last();
			if (rs.getRow() <= 0) {
				result = "Car Not Found";
				return result;
			}

			// Check for car avaliblity

			query = "Select * from carbooking where carid = " + carID + " and (endDate >='" + startDate
					+ "' or startDate <='" + endDate + "')";
			stmt = con.prepareStatement(query);
			System.out.println(query);
			rs = stmt.executeQuery();
			rs.last();
			if (rs.getRow() >= 1) {
				result = "Car Not avaliable for booking for the given time";
				return result;
			}

			query = "Insert into carbooking(userid,carid,startDate,endDate)values(" + userID + "," + carID + ",'"
					+ startDate + "','" + endDate + "')";
			System.out.println(query);
			stmt = con.prepareStatement(query);
			int res = stmt.executeUpdate();

			if (res > 0)
				result = "boking has been saved";
			else
				result = "booking not completed";

		} catch (Exception e) {
			e.printStackTrace();
			result = "Some error Ocuured!!" + e.getMessage();
		}
		return result;
	}

	public static ArrayList<CarBooking> getcarBookingInfo(int userID, int carID) {
		ArrayList<CarBooking> carbookings = new ArrayList<CarBooking>();
		CarBooking obj;
		try {
			String query = "";
			Connection con = DataBaseConnection.initializeDatabase();

			if (userID == 0 && carID > 0)
				query = "Select * from carbooking where carid = " + carID;
			else if (userID > 0 && carID == 0)
				query = "Select * from carbooking where userid = " + userID;
			else if(userID > 0 && carID > 0)
				query = "Select * from carbooking where userid = " + userID +" and carid = "+carID ;
			else
				query = "Select * from carbooking ";
			
			System.out.println(query);
			System.out.println(userID);
			System.out.println(carID);
			
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				obj = new CarBooking();
				obj.setBookingID(Integer.parseInt(res.getString("idcarbooking")));
				obj.setCarId(Integer.parseInt(res.getString("carID")));
				obj.setUserID(Integer.parseInt(res.getString("userID")));
				obj.setEndDate(res.getString("endDate"));
				obj.setStartDate(res.getString("startDate"));
				carbookings.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return carbookings;
	}

}
