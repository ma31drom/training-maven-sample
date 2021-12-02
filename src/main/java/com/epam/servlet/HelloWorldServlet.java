package com.epam.servlet;

import com.epam.model.User;
import com.epam.repo.JDBCUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;

public class HelloWorldServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();



        String test = req.getParameter("test");

        resp.setContentType("text/html; charset=UTF-8");



        writer.println("<p>");

        if (test == null) {
            writer.println("No param provided");
        } else {
            writer.println("Test param value = " + test);
        }
        writer.println("</p>");

        try {
            JDBCUserRepository repo = JDBCUserRepository.getInstance();

            Collection<User> all = repo.getAll();

            all.stream().forEach(user -> writer.println(user.toString()));
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}
