package com.rest.EnquiryManagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enquiry {

	@Id
	@GeneratedValue
	private long id;
	private String enquiry;
	private Date eqDate;
	private long userId;

	public Enquiry() {
		// TODO Auto-generated constructor stub
	}

	public Enquiry(long id, String enquiry, Date eqDate) {
		super();
		this.id = id;
		this.enquiry = enquiry;
		this.eqDate = eqDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	public Date getEqDate() {
		return eqDate;
	}

	public void setEqDate(Date eqDate) {
		this.eqDate = eqDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
