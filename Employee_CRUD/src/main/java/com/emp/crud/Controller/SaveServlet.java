package com.emp.crud.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.crud.DAO.EmployeeDAO;
import com.emp.crud.DTO.Employee;

 

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String name=req.getParameter("name");
		String pwd=req.getParameter("pwd");
		String email=req.getParameter("email");
		String ctry=req.getParameter("ctry");

		Employee e1=new Employee();
		e1.setName(name);
		e1.setPwd(pwd);
		e1.setEmail(email);
		e1.setCtry(ctry);
        int status=EmployeeDAO.saveEmployee(e1);
        if(status>0)
        {
        	out.print("<p>Record Saved Successfully</p>");
        	req.getRequestDispatcher("index.html").include(req, res);
        	
        }
        else
        {
        	out.println("Sorry...! unable to save the record");
        }
        out.close();
 	}

}
