package com.epam.servlet;

import com.epam.model.User;
import com.epam.repo.JDBCUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AddUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");

		try {
			JDBCUserRepository repo = JDBCUserRepository.getInstance();

			User user = new User();
			user.setFirstName(fname);
			user.setLastName(lname);

			User saved = repo.save(user);

			req.setAttribute("addedId", saved.getId());

			req.getRequestDispatcher("userList").forward(req, resp);

		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
