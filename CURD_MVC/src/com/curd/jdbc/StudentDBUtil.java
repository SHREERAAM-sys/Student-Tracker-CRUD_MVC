package com.curd.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.curd.Model.Student;

public class StudentDBUtil {
	
	
	private DataSource datasource;
	
	public StudentDBUtil(DataSource theData)
	{
		datasource=theData;
	}
	
	//Listing students
	
	public  List<Student> getStudent()
	{
		List<Student> student =new ArrayList<>();
		
		
		try(
				Connection con=datasource.getConnection();
				
				 Statement s=con.createStatement();
				ResultSet rs=s.executeQuery("select * from student");)
		{
			
			
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String firstName =rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Student ns=new Student(id,firstName,lastName,email);
				student.add(ns);
				
				System.out.println(ns);
								
			}
			return student;
			
		}
		catch(Exception e)
		{
			
		}
		return null;
		
		
		
	}

	public void addStudent(Student theStudent) {
		
		
		try(Connection con=datasource.getConnection();)
		{
			
			//creating sql for insert
			PreparedStatement stm=
			con.prepareStatement
			("insert into student (first_name,last_name,email) values(?,?,?)");
			
			
			//adding the values to the statement;
			stm.setString(1, theStudent.getFirstName());
			stm.setString(2, theStudent.getLastname());
			stm.setString(3, theStudent.getEmail());
			
			//executing the statement
			stm.execute();
			
			stm.close();
		}
		catch(Exception e)
		{
			
		}
		
		
	}
//Update the student value as pre-placed in student form
	public Student getStudent(String theId) {
		
		int id;
		Student theStudent=null;
		try(Connection con=datasource.getConnection();)		
		{
			id=Integer.valueOf(theId);
			
			String sql="select * from student where id=?";
			PreparedStatement stm=con.prepareStatement(sql);
			stm.setInt(1, id);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next())
			{
				String firstName=rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				//Use the id to create the student object 
				
				theStudent= new Student(id,firstName,lastName,email);
				
				return theStudent;
			}
			
			else
			{
				throw new Exception("Could not find student id: "+id);
			}
			
			
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}

	
	//Updating the student with data got from the update-student-form from the servelet
	public void updateStudent(Student theStudent) {
		
		
		try(Connection con=datasource.getConnection();)
		{
			
			
			 PreparedStatement 
			 		 stm=con.prepareStatement
					 ("update student set first_name=?,last_name=?,email=? where id=?");
			 stm.setString(1, theStudent.getFirstName());
			 stm.setString(2, theStudent.getLastname());
			 stm.setString(3, theStudent.getEmail());
			 stm.setInt(4, theStudent.getId());
			 
			 stm.execute();
			 stm.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	//deleting the student

	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		
		
		try(Connection con=datasource.getConnection())
		{
			PreparedStatement stm=
			con.prepareStatement("delete from student where id=?");
			
			stm.setInt(1,id);
			
			stm.execute();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	

}
