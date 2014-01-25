<html>
<head>
<%@ include file="template/scripts.jsp"%>
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/user_header.jsp"%>
	<!-- end the taglib definitions -->

	<div class="container">
		<h2>Atletas</h2>
		<div id="atletas" class="container"></div>
		<div class="container">
			<a id="showNew" href="#"><span class="glyphicon glyphicon-plus"></span> Nuevo</a>
		</div>
		<div id="userAddForm" class="panel panel-primary hidden">
			<div class="panel-heading">Nuevo Atleta</div>
			<div class="panel-body">
			<form id="userData" class="form-horizontal">
				<div class="form-group">
					<label for="inputName" class="col-sm-2 control-label">Nombre</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputName"
							placeholder="Nombre">
					</div>
				</div>
				<div class="form-group">
					<label for="inputLastName" class="col-sm-2 control-label">Apellido</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputLastName"
							placeholder="Apellido">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail"
							placeholder="Email">
					</div>
				</div>
				<div class="form-group">
				<label for="optionsRadios1" class="col-sm-2 control-label">Sexo</label>
				<div class="col-xs-1">
				<div class="radio">
					<label> <input type="radio" name="optionsRadios"
						id="optionsRadios1" value="MALE" checked> M
					</label>
				</div>
				</div>
				<div class="col-xs-1">
				<div class="radio">
					<label> <input type="radio" name="optionsRadios"
						id="optionsRadios2" value="FEMALE"> F
					</label>
				</div>
				</div>
				</div>	
				<div class="form-group">
					<label for="dateOfBirth" class="col-xs-2 control-label">Fecha
						de nacimiento</label>
					<div class="col-xs-2">
						<input type="text" id="dateOfBirth" placeholder="dd/mm/yyyy">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" id="formSubmitCancel" class="btn btn-default">Cancelar</button>
						<button type="submit" id="formSubmit" class="btn btn-primary">Agregar</button>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>


	<script>
		$(document)
				.ready(
						function() {
							$
									.ajax("v1/users/search?q=ATHLETE")
									.done(
											function(data) {
												var items = [];
												$
														.each(
																data,
																function(key,
																		val) {
																	var parts = val.url
																			.split("/");
																	var url;
																	url = parts[parts.length - 1];
																	items
																			.push("<li id='" + key + "'><a href='atleta.jsp?id="
																					+ url
																					+ "'>"
																					+ val.firstName
																					+ ", "
																					+ val.lastName
																					+ "</a></li>");

																});
												$("<ul/>", {
													"class" : "my-new-list",
													html : items.join("")
												}).appendTo("#atletas");

											}).fail(function() {
										alert("error");
									}).always(function() {

									});

							$("#usersLink").addClass("active");

							$("#dateOfBirth").datepicker({
								changeMonth : true,
								changeYear : true
							});
							$("#dateOfBirth").datepicker("option", "yearRange",
									"1920:" + new Date().getFullYear());
							$("#dateOfBirth").datepicker("option",
									"dateFormat", "dd/mm/yy");

							$("#formSubmitCancel").click(function() {
								$("#userAddForm").addClass("hidden");
							});
							
							$("#showNew").click(function(){
								$("#userAddForm").removeClass("hidden");
							});
							
							$('#formSubmit').click(function() {

								$.ajax({
									type : "POST",
									url : "v1/users",
									data : JSON.stringify({
										firstName : $("#inputName").val(),
										lastName : $("#inputLastName").val(),
										gender : $( "input:checked" ).val(),
										email : $("#inputEmail").val(),
										dateOfBirth : $("#dateOfBirth").val(),
										roles : [ "ATHLETE" ]
									}),
									contentType : 'application/json',
									dataType : "json"
								}).done(function() {
									$("#userAddForm").addClass("hidden");
									location.reload();
								}).fail(function() {
									alert("error");
								});
							});
							
							$('#userData').submit(function() {
								return false;
							});
						});
	</script>
</body>
</html>
