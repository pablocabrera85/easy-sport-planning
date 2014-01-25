<html>
<head>
<%@ include file="template/scripts.jsp" %>
</head>
<body>
<!-- begin the taglib definitions -->
<%@ include file="template/user_header.jsp" %>
<!-- end the taglib definitions -->
	
	<div id="atletas" class="container">
		<h2>Atletas</h2>
		
	</div>
	
	<script>
		$(document).ready(function() {
			$.ajax("v1/users/search?q=ATHLETE").done(function(data) {
				var items = [];
				$.each(data, function(key,val) {
					var parts = val.url.split("/");
						var url;
						url = parts[parts.length-1];
						items.push("<li id='" + key + "'><a href='atleta.jsp?id="+url+"'>" +val.firstName +", "+ val.lastName+ "</a></li>");
					
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
			
		});
	</script>
</body>
</html>
