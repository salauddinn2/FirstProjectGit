package com.rest.EnquiryManagement.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rest.EnquiryManagement.dao.EnquiryDao;
import com.rest.EnquiryManagement.dao.EnquiryDaoImpl;
import com.rest.EnquiryManagement.model.Enquiry;
import com.rest.EnquiryManagement.model.User;


public class EnquiryServiceImpl implements EnquiryService {

	
	EnquiryDao dao = new EnquiryDaoImpl();
	
	public EnquiryDao getDao() {
		return dao;
	}

	public void setDao(EnquiryDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean validateUser(User user,HttpServletRequest request) {
		return dao.validateUser(user,request);
	}

	@Override
	public String registerUser(User user) {
		return dao.registerUser(user);
	}

	@Override
	public String addEnquiry(Enquiry eq) {
		return dao.addEnquiry(eq);
	}

	@Override
	public List<Enquiry> getAllEnquiries(long userId) {
		return dao.getAllEnquiries(userId);
	}

	@Override
	public String updateEnquiry(Enquiry eq) {
		return dao.updateEnquiry(eq);
	}
	
	@Override
	public String deleteEnquiry(Enquiry eq) {
		return dao.deleteEnquiry(eq);
	}
	
	@Override
	public List<Enquiry> getTodayEnquiry() {
		return dao.getTodayEnquiry();
	}
	
	@Override
	public List<Enquiry> getEnquiryName(String userName) {
		return dao.getEnquiryName(userName);
	}

}
