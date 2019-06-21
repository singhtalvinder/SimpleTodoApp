<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<h1>Your Tasks.</h1>
		<table class="table table-striped">
			<caption>A list of your work items are listed below:-</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target date</th>
					<th>Is completed?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc} </td>
						<td><fmt:formatDate value ="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done} </td>
						<td><a type="button" 
							class="btn btn-success" 
							href="/update-todo?id=${todo.id}">Update</a>
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
<%@ include file="common/footer.jspf" %>