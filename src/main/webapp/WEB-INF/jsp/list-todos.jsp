<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	<title>Todo for:${userName}.</title>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	      rel="stylesheet">
	</head>
	<body>
	<div class="container">
		<h1>Your todos.</h1>
		<table class="table table-striped">
			<caption> Your tasks go here...</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target date</th>
					<th>Is completed?</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc} </td>
						<td>${todo.targetDate}</td>
						<td>${todo.done} </td>
						<td><a type="button" 
							class="btn btn-warning" 
							href="/delete-todo?id=${todo.id}">Delete</a>
						</td> 			
					</tr>
				</c:forEach>			
			</tbody>
		</table>	
		<div>
		 <a class="button" href="/add-todo">Add a task</a>
		</div>
		 <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		 <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		 
	 </div>
 	</body>
</html>