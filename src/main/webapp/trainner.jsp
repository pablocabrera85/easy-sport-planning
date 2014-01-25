	<html>
<head>
<%@ include file="template/scripts.jsp"%>
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/header.jsp"%>
	<!-- end the taglib definitions -->
	<div class="container">
		<div class="row">
	  		<div class="col-md-4">
				<div class="panel panel-primary">
	  				<div class="panel-heading">Entrenador</div>
		  			<div id="userName" class="panel-body">
		  			</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-info">
	  				<div class="panel-heading">Deporte</div>
		  			<div id="groups" class="panel-body">
		  			</div>
				</div>
			</div>
		</div>
		<div class="row">
	  		<div class="col-md-12">
				<div class="panel panel-success">
					<div class="panel-heading">Atletas</span></a>	
					</div>
		  			<div id="plans" class="panel-body">
		  			</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	
		$(document).ready(function() {
			var vars = [], hash;
		    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		    for(var i = 0; i < hashes.length; i++)
		    {
		      hash = hashes[i].split('=');
		      vars.push(hash[0]);
		      vars[hash[0]] = hash[1];
		    }
		    
		    set_cookie("trainnerId",vars['id'],7);
		    
			$.ajax("v1/users/"+vars['id']).done(function(data) {
				var items = [];
				var groups = [];
				var plans = [];
				var tournaments = [];
				$("<p/>", {
					"class" : "my-new-list",
					html : data.firstName + ' '+data.lastName
				}).appendTo("#userName");
				
				$.each(data, function(key, val) {
					if(key === "groups"){
						$.each(val, function(key2, val2) {
							groups.push("<li id='" + key2 + "'>" + val2.name + "</li>");
						});
					}else if(key === "tournaments"){
						if(val.description!==undefined){
							
							tournaments.push("<li id='" + key + "'><a href='"+val.url+"'>" + val.description + "</a></li>");
						}else{
						$.each(val, function(key2, val2) {
							tournaments.push("<li id='" + key2 + "'>" + val2.description + "</li>");
						});
						}
					}else if(key === "plans"){
						$.each(val, function(key2, val2) {
							plans.push("<li id='" + key2 + "'>" + val2.name + " <span class='glyphicon  glyphicon-eye-open'></span></li>");
						});
					}else{
						if(key=="email"){
							set_cookie("email",val,7);
						}
						items.push("<li id='" + key + "'>" + val + "</li>");
					}
				});
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : items.join("")
				}).appendTo("#userName");
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : groups.join("")
				}).appendTo("#groups");
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : plans.join("")
				}).appendTo("#plans");
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : tournaments.join("")
				}).appendTo("#tournaments");

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
		});
	</script>
	<!-- JavaScript for interactivity. -->
	<script>
		$(document)
				.ready(
						function() {
							$
									.ajax("v1/")
									.done(
											function(data) {
												var items = [];
												$
														.each(
																data,
																function(key,
																		val) {
																	$
																			.each(
																					val,
																					function(
																							key2,
																							val2) {
																						items
																								.push("<li id='" + key2 + "'><a href='atleta.jsp'>"
																										+ val2.name
																										+ "</a></li>");
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
