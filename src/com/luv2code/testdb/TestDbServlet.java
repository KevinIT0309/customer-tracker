package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// setup connection variable
		String user = "springstudent";
		String pass = "springstudent";
		
//		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String jdbcUrl = "jdbc:mysql://localhost:3306/centercoffee?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		// get connection to database
		try {
			PrintWriter out = response.getWriter();
			
			out.print("Connecting to database ...");
			
			Class.forName(driver);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			
			out.print("SUCCESS");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		
	}

}
