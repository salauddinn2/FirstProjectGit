package com.rest.EnquiryManagement.WSresource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.rest.EnquiryManagement.model.Enquiry;
import com.rest.EnquiryManagement.model.User;
import com.rest.EnquiryManagement.service.EnquiryService;
import com.rest.EnquiryManagement.service.EnquiryServiceImpl;

@Path("admin")
public class AdminResource {
	EnquiryService service = new EnquiryServiceImpl();

	@GET
	@Path("TodayEnquiry")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Enquiry> getAllEnquiry(@Context HttpServletRequest request) {
		List<Enquiry> enquiries = null;

		enquiries = service.getTodayEnquiry();

		return enquiries;
	}

	@GET
	@Path("ByName/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Enquiry> getAllEnquiryByName(@Context HttpServletRequest request,
			@PathParam("userName") String userName) {
		List<Enquiry> enquiries = null;

		enquiries = service.getEnquiryName(userName);

		return enquiries;
	}

	@POST
	@Path("addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerUser(User user) {
		return service.registerUser(user);
	}
}
