<html>
<head>
<%@ include file="template/scripts.jsp"%>
<style>
	#form {
		width: 500px;
		}
</style>
		
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/user_header.jsp"%>
	<!-- end the taglib definitions -->

	<div id="container" class="container">
		<h1>Registrarse/Entrar</h1>
		<form id="userData" class="form-horizontal">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="inputEmail3"
						placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Registrarse</button>
					<button type="submit" id="formSubmit" class="btn btn-primary">Sign in</button>
				</div>
			</div>
		</form>

	</div>

	<script>

		$(document).ready(function() {

			function ConvertFormToJSON() {
				var json = {

					password : $("#inputPassword3").val(),
					email : $("#inputEmail3").val()
				};

				return json;
			}

			$('#formSubmit').click(function() {

				$.ajax({
					type : "POST",
					url : "v1/users/login",
					data : JSON.stringify({
						password : $("#inputPassword3").val(),
						email : $("#inputEmail3").val()
					}),
					contentType : 'application/json',
					dataType : "json"
				}).done(function() {
					window.location = "/";
				}).fail(function() {
					alert("error");
				});
			});
		});
	</script>
</body>
</html>
