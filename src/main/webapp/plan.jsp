<html>
<head>
<%@ include file="template/scripts.jsp"%>
<style>
.chart div {
	font: 10px sans-serif;
	background-color: steelblue;
	text-align: right;
	padding: 3px;
	margin: 1px;
	color: red;
}
</style>
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/user_header.jsp"%>
	<!-- end the taglib definitions -->
	
	<div class="container">
		<div class="panel panel-success">
			<div class="panel-heading">Plan</div>

			<div class="panel-body">
				
				<form id="form2" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="group" class="col-xs-2 control-label">Nombre</label>
						<div class="col-xs-4">
							<input id="name" type="text">
						</div>
					</div>
					<div class="form-group">
						<label for="group" class="col-xs-2 control-label">Grupo</label>
						<div class="col-xs-2">
							<select id="group" class="form-control">
								<option>Velocidad</option>
								<option>Medio Fondo</option>
								<option>Saltos</option>
							</select>
							
						</div>
					</div>
					<div class="form-group">
						<label for="from" class="col-xs-2 control-label">Desde</label>
						<div class="col-xs-2">
							<input type="text" id="from">
						</div>
						<label for="to" class="col-xs-2 control-label">Hasta</label>
						<div class="col-xs-2">
							<input type="text" id="to">
						</div>
					</div>
					<div class="form-group">
						<label for="comments" class="col-xs-2 control-label">Comentarios</label>
						<div class="col-xs-10">
							<textarea id="comments" class="form-control" rows="3"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-offset-10 col-xs-10">
							<button type="submit" id="saveSubmit" class="btn btn-default">Grabar</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>

	<script>
	var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
      hash = hashes[i].split('=');
      vars.push(hash[0]);
      vars[hash[0]] = hash[1];
    }
    
		$("#form2").submit(function() {
			return false;
		});
		
		 $(function() {
			 $( "#from" ).datepicker({
			 defaultDate: "+1w",
			 changeMonth: true,
			 numberOfMonths: 1,
			 onClose: function( selectedDate ) {
			 $( "#to" ).datepicker( "option", "minDate", selectedDate );
			 }
			 });
			 $( "#to" ).datepicker({
			 defaultDate: "+1w",
			 changeMonth: true,
			 numberOfMonths: 1,
			 onClose: function( selectedDate ) {
			 $( "#from" ).datepicker( "option", "maxDate", selectedDate );
			 }
			 });
			 });
		 
		 function ConvertFormToJSON() {
				var json = {
						name : $("#name").val(),
						from : $("#from").val(),
						to : $("#to").val(),
						comments : $("#comments").val(),
						tags :[{
							name : $("select#group option").filter(":selected").val()
						}]
				};

				return json;
			}

			$('#saveSubmit').click(function() {

				$.ajax({
					type : "POST",
					url : "v1/plans/"+vars['userId'],
					data : JSON.stringify(ConvertFormToJSON()),
					contentType : 'application/json',
					dataType : "json"
				}).done(function() {
					window.location = "atleta.jsp?id="+vars['userId'];
				}).fail(function() {
					alert("error");
				});
			});
	</script>


</body>
</html>
