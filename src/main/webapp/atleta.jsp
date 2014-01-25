<html>
<head>
<%@ include file="template/scripts.jsp"%>
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/user_header.jsp"%>
	<!-- end the taglib definitions -->
	<div class="container">
		<div class="row">
	  		<div class="col-md-4">
				<div class="panel panel-primary">
	  				<div class="panel-heading">Atleta</div>
		  			<div id="userName" class="panel-body">
		  			</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-info">
	  				<div class="panel-heading">Grupos</div>
		  			<div id="groups" class="panel-body">
		  			</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-warning">
	  				<div class="panel-heading">Torneos</div>
		  			<div id="tournaments" class="panel-body">
		  			</div>
				</div>
			</div>
		</div>
		<div class="row">
	  		<div class="col-md-12">
				<div class="panel panel-success">
					<div class="panel-heading">Planes <a id="newPlan" href="#">[ Nuevo ]</a>	
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
		    
		    $("a#newPlan").attr('href', 
		    		'plan.jsp?userId='+vars['id']);
		    
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
							groups.push("<li id='" + key2 + "'><a href='group.jsp?id="+val2.name+"'>" + val2.name + "</a></li>");
						});
					}else if(key === "tournaments"){
						$.each(val, function(key2, val2) {
							tournaments.push("<li id='" + key2 + "'>" + val2.description + "</li>");
						});
					}else if(key === "plans"){
						$.each(val, function(key2, val2) {
							plans.push("<li id='" + key2 + "'>" + val2.name + " - <span class='glyphicon  glyphicon-eye-open'></span> - <a href='plan_edit.jsp?id="+vars['id']+"&name="+data.firstName + ' '+data.lastName+"&path="+val2.name+"'> <span class='glyphicon  glyphicon-pencil'></span></a></li>");
							var innerPlans = [];
							if(val2.plans!==undefined){
							$.each(val2.plans,function(key3, val3) {
								innerPlans.push("<li id='" + key3 + "'>" + val3.name + " - <span class='glyphicon  glyphicon-eye-open'></span> - <a href='plan_edit.jsp?id="+vars['id']+"&name="+data.firstName + ' '+data.lastName+"&path="+val2.name+","+val3.name+"'> <span class='glyphicon  glyphicon-pencil'></span></a></li>");
							});
							
							
							
								plans.push("<ul>"+innerPlans.join("")+"</ul>");
							}
						});
					}else{
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
