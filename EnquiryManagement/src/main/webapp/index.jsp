<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body >
<pre>
	User Name <input type="text" name="userName" id="userName">
	Password  <input type="text" name="password" id="password">
	
	<button onclick="submit();">Submit</button>
</pre>

	<script type="text/javascript">
		data = "";
		load = function() {
			$.ajax({
				url : 'Enquiry/user/getAll',
				type : 'POST',
				success : function(response) {
					for (var i = 0; i < response.length; i++) {
						console.log(response[i].enquiry);
					}
				}
			});

		}
		
		
		submit = function(){
			 alert('hello');
			$.ajax({
				url:'Enquiry/admin/addUser',
				type:'POST',
				data:{useName:$("#userName").val(),password:$("#password")},
				success: function(response){
						load();
				}				
			});			
	}
	</script>
</body>
</html>