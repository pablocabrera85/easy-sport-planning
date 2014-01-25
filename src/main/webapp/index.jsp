<html>
<head>
<%@ include file="template/scripts.jsp" %>
</head>
<body>
<!-- begin the taglib definitions -->
<%@ include file="template/header.jsp" %>
<!-- end the taglib definitions -->

	<div class="container">
	<div class="jumbotron">
		<h1>Planear nunca fue tan sencillo!</h1>
		<p>Programe entrenamientos, competencias y realice un seguimiento detallado de sus atletas.</p> 
		<p>Comparta su conocimiento con el mundo y aprenda de los mejores.</p>
		<p>
			<a href="register.jsp" class="btn btn-primary btn-lg" role="button">Comience a usarlo!</a>
		</p>
	</div>
	<div id="sports"></div>
	</div>

	<script>
		$(document).ready(function() {
			$.ajax("v1/sports").done(function(data) {
				var items = [];
				$.each(data, function(key, val) {
					items.push("<li id='" + key + "'>" + val.name + "</li>");
				});
				$("<ul/>", {
					"class" : "my-new-list",
					html : items.join("")
				}).appendTo("#sports");

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
		});
	</script>
		<!-- JavaScript for interactivity. -->
	<script>
		$(document).ready(function() {
			$.ajax("v1/").done(function(data) {
				var items = [];
				$.each(data, function(key,val) {
					$.each(val,function(key2,val2){
					items.push("<li id='" + key2 + "'><a href='"+val2.url+"'>" +val2.name + "</a></li>");
					});
				});
				$("<ul/>", {
					"class" : "my-new-list",
					html : items.join("")
				}).appendTo("#resources");

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
		});
	</script>
</body>
</html>
