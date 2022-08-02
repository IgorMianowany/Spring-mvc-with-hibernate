package com.spring.testdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestDbServlet", value = "/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // setup connection variables

        // user same as password just for simplicity
        String user = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        // connect to db
        try {
            PrintWriter out = response.getWriter();

            out.println("Connecting to: " + jdbcUrl);

            Class.forName(driver);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, user);

            // if we connect without exception we'll print this line
            out.println("Connected to: " + jdbcUrl);

            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();

            // throwing exception so we can see it in browser
            throw new ServletException();
        }

    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
