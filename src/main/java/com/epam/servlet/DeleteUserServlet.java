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

public class DeleteUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parameter = req.getParameter("userId");

		Integer userId = Integer.valueOf(parameter);
		try {
			JDBCUserRepository repo = JDBCUserRepository.getInstance();

			repo.delete(userId);

			req.setAttribute("deletedId", userId);

			req.getRequestDispatcher("userList").forward(req, resp);

		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
