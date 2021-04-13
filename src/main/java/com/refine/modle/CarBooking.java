/**
 * 
 */
package com.refine.modle;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Mridul
 *
 */
public class CarBooking {
	
	private Integer bookingID;
	private Integer carId;
	private Integer userID;
	private String startDate;
	private String endDate;
	
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the bookingID
	 */
	public Integer getBookingID() {
		return bookingID;
	}
	/**
	 * @param bookingID the bookingID to set
	 */
	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}
	/**
	 * @return the carId
	 */
	public Integer getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	/**
	 * @return the userID
	 */
	public Integer getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
}
