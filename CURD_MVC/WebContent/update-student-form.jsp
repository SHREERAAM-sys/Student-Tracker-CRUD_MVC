<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/add-student-style.css">
</head>
<body>

		<div id="wrapper">
			<div id="header">
				<h2>FooBar University</h2>
			</div>
		</div>
			
		<div id="container">
			<h3>Update Student</h3>
			
			<form action="StudentControllerServlet" method="GET">
				<input type="hidden" name="command" value="UPDATE" />
				<input type="hidden" name="studentId" value="${ the_stud.id}" />
				
				<table>
				
					<tbody>
						<tr>
							<td><lable>First name:</lable></td>
							<td><input type="text" name="firstName" 
							
										value="${ the_stud.firstName}"/></td>
							
						</tr>
						<tr>
							<td><lable>Last name:</lable></td>
							<td><input type="text" name="lastName" 
							
												value="${ the_stud.lastname}"/></td>
								
						</tr>
						<tr>
							<td><lable>Email:</lable></td> 
							<td><input type="text" name="email" 
											value="${ the_stud.email}"/></td>
							
						</tr>
						<tr>
							<td><lable></lable></td>
							<td><input type="submit" value="Save" class="save"  /></td>
							
						</tr>
					</tbody>
				</table>
			</form>
			
			<div style="clear: both;"></div>
			
			<p>
				<a href="StudentControllerServlet">Back to list</a>
			</p>
		</div>
</body>
</html>