<html>
<head>
<%@ include file="template/scripts.jsp" %>
<style>
	#wrap {
		width: 1100px;
		margin: 0 auto;
		}
		
	#external-events {
		float: left;
		width: 150px;
		padding: 0 10px;
		border: 1px solid #ccc;
		background: #eee;
		text-align: left;
		}
		
	#external-events h4 {
		font-size: 16px;
		margin-top: 0;
		padding-top: 1em;
		}
		
	.external-event { /* try to mimick the look of a real event */
		margin: 5px 0;
		padding: 2px 2px;
		background: #3366CC;
		color: #fff;
		font-size: .85em;
		cursor: pointer;
		}
		
	#external-events p {
		margin: 1.5em 0;
		font-size: 11px;
		color: #666;
		}
		
	#external-events p input {
		margin: 0;
		vertical-align: middle;
		}

	#calendar {
		float: right;
		width: 900px;
		}
</style>
</head>
<body>
<!-- begin the taglib definitions -->
<%@ include file="template/user_header.jsp" %>
<!-- end the taglib definitions -->

	<div id='wrap'>

		<div id='external-events'>
			<h4>Ejercicio</h4>
			<h5>Fuerza</h5>
			<div class='external-event'>2x5x80</div>
			<div class='external-event'>5x100</div>
			<h5>Velocidad</h5>
			<div class='external-event'>3x400</div>
			<div class='external-event'>1x10x1000</div>
			<div class='external-event'>2x600</div>
			

		
		</div>

		<div id='calendar'></div>

		<div style='clear: both'></div>
	</div>
	<script>
		$(document).ready(function() {
			
			/* initialize the external events
			-----------------------------------------------------------------*/
		
			$('#external-events div.external-event').each(function() {
			
				// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
				// it doesn't need to have a start or end
				var eventObject = {
					title: $.trim($(this).text()) // use the element's text as the event title
				};
				
				// store the Event Object in the DOM element so we can get to it later
				$(this).data('eventObject', eventObject);
				
				// make the event draggable using jQuery UI
				$(this).draggable({
					zIndex: 999,
					revert: true,      // will cause the event to go back to its
					revertDuration: 0  //  original position after the drag
				});
				
			});
			
			$.ajax("v1/tournaments").done(function(data) {
				var items = [];
				$.each(data, function(key,val) {
					$.each(val,function(key2,val2){
						var map = {'title':val2.name, 'start':val2.startDate, 'end':val2.endDate};
						items.push(map);
					});
				});
				
				$('#calendar').fullCalendar({
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,basicWeek,basicDay'
					},
					editable: true,
					events: items,
					droppable: true, // this allows things to be dropped onto the calendar !!!
					drop: function(date, allDay) { // this function is called when something is dropped
					
						// retrieve the dropped element's stored Event Object
						var originalEventObject = $(this).data('eventObject');
						
						// we need to copy it, so that multiple events don't have a reference to the same object
						var copiedEventObject = $.extend({}, originalEventObject);
						
						// assign it the date that was reported
						copiedEventObject.start = date;
						copiedEventObject.allDay = allDay;
						
						// render the event on the calendar
						// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
						$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
						
						
					}
				});

			}).fail(function() {
				alert("error");
			}).always(function() {

			});
			
			$("#usersTrainning").addClass("active");
			
		});
	</script>
</body>
</html>
