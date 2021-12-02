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

public class UserListPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			JDBCUserRepository repo = JDBCUserRepository.getInstance();

			Collection<User> all = repo.getAll();

			req.setAttribute("users", all);

			req.getRequestDispatcher("users.jsp").forward(req, resp);

		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
