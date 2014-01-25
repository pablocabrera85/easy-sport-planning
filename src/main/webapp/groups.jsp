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
  			<li class="active">Grupos</li>
  			
		</ol>
	</div>
	<div class="container">
		<a href="#" >[ Nuevo ]</a>
	</div>
	<script>
		var addToList = function(items,pojo){
			
		}
		$(document).ready(function() {
			
			var vars = [], hash;
		    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		    for(var i = 0; i < hashes.length; i++)
		    {
		      hash = hashes[i].split('=');
		      vars.push(hash[0]);
		      vars[hash[0]] = hash[1];
		    }
		    
		    $.ajax("v1/users/"+vars['id']).done(function(data) {
				var items = [];
				
				var groups = data.groups;
				groups.forEach( function(group) {
					items.push("<li id='" + group.name + "'><a href='group.jsp?id="+group.name+"'>" +group.name + "</a></li>");	
				});
				
				$("<ul/>", {
					"class" : "my-new-list",
					html : items.join("")
				}).appendTo("#groups");

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
			
			$("#usersGroups").addClass("active");
			
		});
	</script>
</body>
</html>
