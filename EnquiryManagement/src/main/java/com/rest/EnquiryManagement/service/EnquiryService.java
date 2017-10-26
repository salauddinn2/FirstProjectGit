package com.rest.EnquiryManagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rest.EnquiryManagement.model.Enquiry;
import com.rest.EnquiryManagement.model.User;

public interface EnquiryService {

	public boolean validateUser (User user, HttpServletRequest request);

	public String registerUser(User user);

	public String addEnquiry(Enquiry eq);

	public List<Enquiry> getAllEnquiries(long userId);

	public String updateEnquiry(Enquiry eq);

	public String deleteEnquiry(Enquiry eq);

	public List<Enquiry> getTodayEnquiry();

	public List<Enquiry> getEnquiryName(String userName);
}
