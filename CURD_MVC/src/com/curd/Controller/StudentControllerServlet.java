package com.curd.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.curd.Model.Student;
import com.curd.jdbc.StudentDBUtil;

/*
 * *
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDBUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			studentDBUtil = new StudentDBUtil(datasource);
		}
		catch(Exception e)
		{
			throw new ServletException(e);
		}
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			String theCommand=request.getParameter("command");
			
			if(theCommand==null) theCommand="LIST";
			
			switch(theCommand)
			{
				case "LIST":
					listStudents(request, response);
					break;
				case "ADD":
					addStudent(request,response);
					break;
				case "LOAD":
					loadStudent(request,response);
					break;
				case "UPDATE":
					updateStudent(request,response);
					break;
				case "DELETE":
					deleteStudent(request,response);
					break;
				default:
					listStudents(request,response);
			}
			
		} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id=Integer.parseInt(request.getParameter("studentId"));
		
		studentDBUtil.deleteStudent(id);
		
		
		listStudents(request, response);
		
	}



	//read updated from data from update-student-form.jsp
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int id=Integer.parseInt(request.getParameter("studentId"));
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		
		Student theStudent=new Student(id,firstName,lastName,email);
		
		studentDBUtil.updateStudent(theStudent);
		
		listStudents(request, response);
		
		
		
	}



	//load student in place holder in update form
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String theId = request.getParameter("studentId");
		
		Student theStudent = studentDBUtil.getStudent(theId);
		
		request.setAttribute("the_stud", theStudent);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		
		dispatcher.forward(request, response);
		
	}



	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String email= request.getParameter("email");
		
		Student theStudent = new Student(firstName,lastName,email);
		
		studentDBUtil.addStudent(theStudent);
		
		//send back to main page
		
		listStudents(request,response);
	}



	private  void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		List<Student> student = studentDBUtil.getStudent();
		
		request.setAttribute("stu_list", student);
		
		/*for(Student s:student)
		{
			out.println(s);
		}*/
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("list-students.jsp");
		
		dispatcher.forward(request, response);
	}

}
