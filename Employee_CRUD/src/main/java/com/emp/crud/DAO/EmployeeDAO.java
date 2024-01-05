package com.emp.crud.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.emp.crud.DTO.Employee;

public class EmployeeDAO {
	public static Connection getConnection() {
		Connection con=null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_crud","root","admin");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return con;
	}
	public static int saveEmployee(Employee emp)
	{
		int status=0;
		try {
			Connection con=EmployeeDAO.getConnection();
			PreparedStatement stmnt=con.prepareStatement("insert into emp_table(name,password,email,country)values(?,?,?,?)");
 			stmnt.setString(1,emp.getName());
 			stmnt.setString(2,emp.getPwd());
 			stmnt.setString(3,emp.getEmail());
 			stmnt.setString(4,emp.getCtry());
 			status=stmnt.executeUpdate();
 			con.close();

		}catch(Exception e) {
			System.out.println(e);
  
		}
		return status;
	}
	public static List<Employee> getEmployee()
	{
		ArrayList<Employee>l1=new ArrayList<Employee>();
		try {
		Connection con=EmployeeDAO.getConnection();
  		PreparedStatement stmnt=con.prepareStatement("Select * from emp_table");
 		ResultSet rs=stmnt.executeQuery();
 		while(rs.next())
 		{
 			Employee e1=new Employee();
 			e1.setId(rs.getInt(1));
 			e1.setName(rs.getString(2));
 			e1.setPwd(rs.getString(3));
 			e1.setEmail(rs.getString(4));
 			e1.setCtry(rs.getString(5));
 			l1.add(e1);

 		}
 		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return l1;
	}
	public static int Update(Employee emp)
	{
		int status=0;
		try {
			Connection con=EmployeeDAO.getConnection();
			PreparedStatement stmnt=con.prepareStatement("update emp_table set name=?,password=?,email=?,country=? where id=?");
			stmnt.setString(1,emp.getName());
			stmnt.setString(2,emp.getPwd());
			stmnt.setString(3,emp.getEmail());
			stmnt.setString(4,emp.getCtry());
			stmnt.setInt(5,emp.getId());
			status=stmnt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return status;
	}
	public static Employee getEmployeeById(int id){
		Employee e1=new Employee();
		
		try{
			Connection con=EmployeeDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from emp_table where id=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e1.setId(rs.getInt(1));
				e1.setName(rs.getString(2));
				e1.setPwd(rs.getString(3));
				e1.setEmail(rs.getString(4));
				e1.setCtry(rs.getString(5));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e1;
	}
	public static int delete(int id){
		int status=0;
		try{
			Connection con=EmployeeDAO.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from emp_table  where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}


}
