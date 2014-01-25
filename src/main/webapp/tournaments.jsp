<html>
<head>
<%@ include file="template/scripts.jsp" %>
</head>
<body>
<!-- begin the taglib definitions -->
<%@ include file="template/user_header.jsp" %>
<!-- end the taglib definitions -->
	<div class="container">
	<div id='listView'>
	<h3>Torneos</h3>
	</div>
	<label class="checkbox">
          <input type="checkbox" onclick="show_checked()" value="remember-me"/>Ver Calendario
        </label>
    
    <div id='calendarWrapper' class="hidden">
		<div id='calendar'>
		</div>
	</div>
	</div>
	
	<script>
		$(document).ready(function() {
			
			$.ajax("v1/tournaments").done(function(data) {
				var items = [];
				var itemsList = [];
				$.each(data, function(key,val) {
						var map = {'title':val.name, 'start':val.startDate, 'end':val.endDate,'url':'v1/users'};
						itemsList.push("<dt>"+$.datepicker.formatDate('dd-mm-yy',new Date(val.startDate))+"-"+$.datepicker.formatDate('dd-mm-yy', new Date(val.endDate)	) + "</dt><dd>"+val.name+"</dd>");
						items.push(map);
				});
				
				$('#calendar').fullCalendar({
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					editable: true,
					events: items
				});
				
				$("<dl/>", {
					"class" : "dl-horizontal",
					html : itemsList.join("")
				}).appendTo("#listView");

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
			
			$("#usersTournament").addClass("active");
			
		});
		
		function show_checked() {
			 if($(':checkbox').is(':checked')){
				 $('#calendarWrapper').removeClass("hidden").addClass("show");
			 }else{
				 $('#calendarWrapper').removeClass("show").addClass("hidden");
			 }
		}
	</script>
</body>
</html>
