package com.rest.EnquiryManagement.WSresource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.rest.EnquiryManagement.model.Enquiry;
import com.rest.EnquiryManagement.model.User;
import com.rest.EnquiryManagement.service.EnquiryService;
import com.rest.EnquiryManagement.service.EnquiryServiceImpl;

@Path("user")
public class UserResource {

	EnquiryService service = new EnquiryServiceImpl();

	public EnquiryService getService() {
		return service;
	}

	public void setService(EnquiryService service) {
		this.service = service;
	}

	@GET
	@Path("logout")
	@Produces(MediaType.TEXT_PLAIN)
	public String logoutUser(@Context HttpServletRequest request) {
		request.getSession().setAttribute("userId", null);
		return "User logged out Successfully !!";
	}

	@POST
	@Path("loginUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String validateUser(User user, @Context HttpServletRequest request) {

		if (service.validateUser(user, request)) {
			System.out.println(request.getSession().getAttribute("userId"));
			return "user login successfull";
		} else {
			return "Invalid userName or Password";
		}
	}

	@POST
	@Path("addEnquiry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEnquiry(Enquiry eq, @Context HttpServletRequest request) {
		if (request.getSession().getAttribute("userId") == null) {
			return "Please Login first.. Then Add Equiry";
		}

		long userId = (long) request.getSession().getAttribute("userId");
		eq.setUserId(userId);
		return service.addEnquiry(eq);
	}

	@PUT
	@Path("updateEnquiry/{eqId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEnquiry(Enquiry eq, @Context HttpServletRequest request, @PathParam("eqId") long eqId) {

		if (request.getSession().getAttribute("userId") == null) {
			return "Please Login first.. Then update Equiry";
		}

		long userId = (long) request.getSession().getAttribute("userId");
		eq.setUserId(userId);
		eq.setId(eqId);
		return service.updateEnquiry(eq);

	}

	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Enquiry> getAllEnquiry(@Context HttpServletRequest request) {
		List<Enquiry> enquiries = null;

		if (request.getSession().getAttribute("userId") == null) {
			return enquiries;
		}

		long userId = (long) request.getSession().getAttribute("userId");
		enquiries = service.getAllEnquiries(userId);

		return enquiries;
	}

	@DELETE
	@Path("deleteEnquiry/{eqId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEnquiry(@Context HttpServletRequest request, @PathParam("eqId") long eqId) {

		if (request.getSession().getAttribute("userId") == null) {
			return "Please Login first.. Then delete Equiry";
		}

		Enquiry eq = new Enquiry();
		eq.setId(eqId);
		return service.deleteEnquiry(eq);

	}
	
	@POST
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Enquiry> getAll(@Context HttpServletRequest request) {
		List<Enquiry> enquiries = new ArrayList<>();
		enquiries.add(new Enquiry(1L, "SOME TEXT", new Date()));

		return enquiries;
	}

}
