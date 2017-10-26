package com.rest.EnquiryManagement.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.rest.EnquiryManagement.hibernate.HibernateUtils;
import com.rest.EnquiryManagement.model.Enquiry;
import com.rest.EnquiryManagement.model.User;

@Component
public class EnquiryDaoImpl implements EnquiryDao {

	@Override
	public boolean validateUser(User user, HttpServletRequest request) {
		Session session = HibernateUtils.openSession();

		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("userName", user.getUserName()));
		cr.add(Restrictions.eq("password", user.getPassword()));
		List<User> results = cr.list();
		if (results != null && results.size() > 0) {
			request.getSession().setAttribute("userId", results.get(0).getUserId());
			return true;
		}
		return false;
	}

	@Override
	public String registerUser(User user) {
		try {
			Session session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();

			session.save(user);

			tx.commit();
			session.close();
			return "User Registered";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Technical Error..while registering user";
	}

	@Override
	public String addEnquiry(Enquiry eq) {
		try {
			Session session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();

			eq.setEqDate(new Date());
			session.save(eq);

			tx.commit();
			session.close();
			return "Enquiry Registered";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Technical Error..while adding Enquiry";
	}

	@Override
	public List<Enquiry> getAllEnquiries(long userId) {
		Session session = HibernateUtils.openSession();

		Criteria cr = session.createCriteria(Enquiry.class);
		cr.add(Restrictions.eq("userId", userId));

		List<Enquiry> results = cr.list();

		return results;
	}

	@Override
	public String updateEnquiry(Enquiry eq) {
		try {
			Session session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();

			eq.setEqDate(new Date());
			session.update(eq);

			tx.commit();
			session.close();
			return "Enquiry Updated";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Technical Error..while updating Enquiry";
	}

	@Override
	public String deleteEnquiry(Enquiry eq) {
		try {
			Session session = HibernateUtils.openSession();
			Transaction tx = session.beginTransaction();

			eq.setEqDate(new Date());
			session.delete(eq);

			tx.commit();
			session.close();
			return "Enquiry delete";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Technical Error..while delete Enquiry";
	}

	@Override
	public List<Enquiry> getTodayEnquiry() {
		List<Enquiry> todayList = new ArrayList<>();
		Session session = HibernateUtils.openSession();

		Criteria cr = session.createCriteria(Enquiry.class);
		List<Enquiry> results = cr.list();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		for (Enquiry eq : results) {
			c1.setTime(eq.getEqDate());
			System.out.println(c1.get(Calendar.DAY_OF_MONTH));
			System.out.println(c2.get(Calendar.DAY_OF_MONTH));

			if (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)) {
				todayList.add(eq);
			}
		}

		return todayList;
	}

	@Override
	public List<Enquiry> getEnquiryName(String userName) {

		List<Enquiry> todayList = new ArrayList<>();
		Session session = HibernateUtils.openSession();

		String sql = "SELECT * FROM Enquiry e where e.userId=(select u.userId from User u where u.userName='" + userName
				+ "') ";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Enquiry.class);
		List results = query.list();
		return results;
	}

}
