<html>
<head>
<%@ include file="template/scripts.jsp"%>
</head>
<body>
	<!-- begin the taglib definitions -->
	<%@ include file="template/user_header.jsp"%>
	<!-- end the taglib definitions -->

	<div id="groups" class="container">
		<ol class="breadcrumb">
  			<li><a id="usersGroupsIdLink" href="groups.jsp?id=52bc487d036428225100c78a">Grupos</a></li>
  			
		</ol>

	</div>
	<div id="addToGroup" class="container">
	<h5>Agregar al grupo:</h5>
		<div id="notInGroup" class="container">

		</div>
		<button id="add" type="button" class="btn btn-primary pull-right">Agregar</button>
	</div>
	<script>
		$(document).ready(function() {
			
			
			var trainnerId = get_cookie("trainnerId");
			if (trainnerId.length > 0) {
				$("a#usersGroupsIdLink").attr("href",
						"groups.jsp?id=" + trainnerId);
			}
			
			var vars = [], hash;
		    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		    for(var i = 0; i < hashes.length; i++)
		    {
		      hash = hashes[i].split('=');
		      vars.push(hash[0]);
		      vars[hash[0]] = hash[1];
		    }
		    
		    $(".breadcrumb").append("<li class='active'>"+unescape(vars['id'])+"</li>");
		    // OLD QUERY TO FILTER BY GROUP TYPE
		    //"v1/users/search?group="+vars['id']
		    $.ajax("v1/users/search?q=ATHLETE").done(function(data) {
		    	var items = [];
		    	var notInItems = [];
				$.each(data, function(key,val) {
					var belongs = belongsToGroup(val,unescape(vars['id']));
					if(belongs){
						var parts = val.url.split("/");
						var url;
						url = parts[parts.length-1];
						items.push("<li id='" + key + "'><a href='atleta.jsp?id="+url+"'>" +val.firstName +", "+ val.lastName+  "</a></li>");
					}else{
						var parts = val.url.split("/");
						var url;
						url = parts[parts.length-1];
						notInItems.push("<option id='" + url + "'>"+val.firstName +", "+ val.lastName+  "</option>");
					}
					
				});
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : items.join("")
				}).appendTo("#groups");
				
				
				var listHtml= "<select id='notingroup' mutiple class='form-control'";
				if(! notInItems.length > 0){
					listHtml+= "disabled" ;	
					$("#add").attr("disabled","disabled");
				}
				listHtml+= ">"+ notInItems.join("")+"</select>";
					
				$("#notInGroup").append(listHtml);
				

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
			
			$("#usersGroups").addClass("active");
			
			$('#add').click(function() {
				var userId = $("select#notingroup option").filter(":selected").attr("id");
				var user = 
				{
					groups : [
					          	{ 
					        		name: unescape(vars['id'])
					       		}
					         ]		
				};
				$.ajax({
					type : "PUT",
					url : "v1/users/"+userId,
					data : JSON.stringify(user),
					contentType: "application/json; charset=utf-8",
				    dataType: "json"
				}).done(function() {
					location.reload();
				}).fail(function( jqXHR, textStatus, errorThrown) {
					alert("error");
				});
			});
		});
	</script>
</body>
</html>
