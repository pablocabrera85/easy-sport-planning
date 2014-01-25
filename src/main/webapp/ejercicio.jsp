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
		<div class="row">


			<div class="col-md-8">
				<form id="form" class="form-horizontal" role="form">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Ejercicio</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label for="spinner" class="col-sm-2 control-label">Bloques</label>
								<div class="col-sm-4">
									<input id="spinner" name="spinner" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="spinner2" class="col-sm-2 control-label">Series</label>
								<div class="col-sm-4">
									<input id="spinner2" name="spinner2" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="spinner3" class="col-sm-2 control-label">Repeticiones</label>
								<div class="col-sm-10">
									<input id="spinner3" name="spinner3" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="spinner4" class="col-sm-2 control-label">Volumen</label>
								<div class="col-sm-10">
									<input id="spinner4" name="spinner4" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="template" class="col-sm-2 control-label">Unidades</label>
								<div class="col-sm-4">
									<select id="units" class="form-control">
										<option>metros</option>
										<option>kilos</option>
										<option>Unidades</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="spinner5" class="col-sm-2 control-label">Intensidad
									(%)</label>
								<div class="col-sm-10">
									<input id="spinner5" name="spinner5" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="spinner6" class="col-sm-2 control-label">Macro
									pausa</label>
								<div class="col-sm-10">
									<input id="spinner6" name="spinner6" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="spinner7" class="col-sm-2 control-label">Micro
									pausa</label>
								<div class="col-sm-10">
									<input id="spinner7" name="spinner7" value="1">
								</div>
							</div>
							<div class="form-group">
								<label for="tipo" class="col-sm-2 control-label">Tipo</label>
								<div class="col-sm-4">
									<select id="tipo" class="form-control">
										<option>Fuerza</option>
										<option>Velocidad</option>
										<option>Resistencia</option>
										<option>Aerobico</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="comments" class="col-sm-2 control-label">Comentarios</label>
								<div class="col-sm-10">
									<textarea id="comments" class="form-control" rows="3"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-8 col-sm-10">
									<button type="submit" id="formSubmit" class="btn btn-default">Agregar</button>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
			<div class="col-md-4">
				<div class="panel panel-info">
					<div class="panel-heading">Total</div>
					<div id="total" class="panel-body"></div>
				</div>
				<div class="panel panel-default"><div class="panel-heading">Acumulado</div>
					<div class="panel-body" id="bar_chart_2">
						<div class="chart"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">Plan</div>

			<div class="panel-body">
				<table id="exercices" class="table table-hover">
					<thead>
						<tr>
							<th>Tipo</th>
							<th>Bloques</th>
							<th>Series</th>
							<th>Repeticiones</th>
							<th>Volumen</th>
							<th>Total</th>
							<th>Comments</th>
						</tr>
					</thead>
				</table>

			</div>
		</div>
	</div>

	<script>
		var tooltip = d3.select("#bar_chart_2").append("div").style("position",
				"absolute").style("z-index", "10")
				.style("visibility", "hidden").text("a simple tooltip");

		var dataSet = [ {
			xCoordinate : "Fuerza",
			magnitude : 0
		}, {
			xCoordinate : "Velocidad",
			magnitude : 0
		}, {
			xCoordinate : "Resistencia",
			magnitude : 0
		}, {
			xCoordinate : "Aerobico",
			magnitude : 0
		} ];
		var chartID = "Bars1";
		var magnitudFn = function(d) {
			return d.magnitude;
		};
		var getType = function(d) {
			if (d === "index-0")
				return "Fuerza";
			if (d === "index-1")
				return "Velocidad";
			if (d === "index-2")
				return "Resistencia";
			return "Aerobico";

		};
		var totales = {};
		totales["Fuerza"] = 0;
		totales["Velocidad"] = 0;
		totales["Resistencia"] = 0;
		totales["Aerobico"] = 0;

		var getTotal = function(d) {
			return "Acumulado:" + totales[getType(d)];
		};

		(function() {
			// Just to make sure that JSONData is not altered between examples
			var data = dataSet;
			// chartID => A unique drawing identifier that has no spaces, no "." and no "#" characters.
			// dataSet => Input Data for the chart, itself.
			// selectString => String that allows you to pass in
			//           a D3 select string.
			// colors => String to set color scale.  Values can be...
			//           => "colorScale10"
			//           => "colorScale20"
			//           => "colorScale20b"
			//           => "colorScale20c"

			var canvasWidth = 500;
			var barsWidthTotal = 300
			var barWidth = barsWidthTotal / dataSet.length;
			//var canvasHeight = 200;
			var canvasHeight = 300;
			var legendOffset = 30;
			var legendBulletOffset = 30;
			var legendTextOffset = 20;

			var x = d3.scale.linear().domain([ 0, dataSet.length ]).range(
					[ 0, barsWidthTotal ]);
			var y = d3.scale.linear().range([ 0, canvasHeight ]);

			//document.writeln(selectString);

			// Color Scale Handling...
			var colorScale = d3.scale.category20c();

			var synchronizedMouseOver = function() {
				var bar = d3.select(this);
				var indexValue = bar.attr("index_value");

				var barSelector = "." + "bars-" + chartID + "-bar-"
						+ indexValue;
				var selectedBar = d3.selectAll(barSelector);
				selectedBar.style("fill", "Maroon");

				var bulletSelector = "." + "bars-" + chartID + "-legendBullet-"
						+ indexValue;
				var selectedLegendBullet = d3.selectAll(bulletSelector);
				selectedLegendBullet.style("fill", "Maroon");

				var textSelector = "." + "bars-" + chartID + "-legendText-"
						+ indexValue;
				var selectedLegendText = d3.selectAll(textSelector);
				selectedLegendText.style("fill", "Maroon");
				tooltip.text(getTotal(indexValue));
				tooltip.style("visibility", "visible");
			};

			var synchronizedMouseOut = function() {
				var bar = d3.select(this);
				var indexValue = bar.attr("index_value");

				var barSelector = "." + "bars-" + chartID + "-bar-"
						+ indexValue;
				var selectedBar = d3.selectAll(barSelector);
				var colorValue = selectedBar.attr("color_value");
				selectedBar.style("fill", colorValue);

				var bulletSelector = "." + "bars-" + chartID + "-legendBullet-"
						+ indexValue;
				var selectedLegendBullet = d3.selectAll(bulletSelector);
				var colorValue = selectedLegendBullet.attr("color_value");
				selectedLegendBullet.style("fill", colorValue);

				var textSelector = "." + "bars-" + chartID + "-legendText-"
						+ indexValue;
				var selectedLegendText = d3.selectAll(textSelector);
				selectedLegendText.style("fill", "Blue");
				tooltip.style("visibility", "hidden");
			};

			// Create the svg drawing canvas...
			var canvas = d3.select("#bar_chart_2 .chart").append("svg:svg")
			//.style("background-color", "yellow")
			.attr("width", canvasWidth).attr("height", canvasHeight);

			var refreshGraph = function() {
				totales["Fuerza"] = 0;
				totales["Velocidad"] = 0;
				totales["Resistencia"] = 0;
				totales["Aerobico"] = 0;
				var columns = $('#exercices thead th').map(function() {
					// This assumes that your headings are suitable to be used as
					//  JavaScript object keys. If the headings contain characters 
					//  that would be invalid, such as spaces or dashes, you should
					//  use a regex here to strip those characters out.
					return $(this).text();
				});

				var columnNames = [];
				$('#exercices tbody tr').map(function(i) {
					var row = {};

					// Find all of the table cells on this row.
					$(this).find('td').each(function(i) {
						// Determine the cell's column name by comparing its index
						//  within the row with the columns list we built previously.
						var rowName = columns[i];

						// Add a new property to the row object, using this cell's
						//  column name as the key and the cell's text as the value.
						row[rowName] = $(this).text();
					});
					if (totales[row.Tipo] === undefined) {
						totales[row.Tipo] = 0;
						columnNames.push(row.Tipo);
					}
					totales[row.Tipo] += +row.Total;
					// Finally, return the row's object representation, to be included
					//  in the array that $.map() ultimately returns.
					return row;

					// Don't forget .get() to convert the jQuery set to a regular array.
				}).get();

				dataSet = [ {
					xCoordinate : "Fuerza",
					magnitude : totales["Fuerza"]
				}, {
					xCoordinate : "Velocidad",
					magnitude : totales["Velocidad"]
				}, {
					xCoordinate : "Resistencia",
					magnitude : totales["Resistencia"]
				}, {
					xCoordinate : "Aerobico",
					magnitude : totales["Aerobico"]
				} ];
				// Draw individual hyper text enabled bars...
				var circles = canvas.selectAll("rect").data(dataSet);

				y.domain(d3.extent(dataSet, magnitudFn));
				circles.transition().attr("height", function(d) {
					return y(magnitudFn(d))
				}).attr("y", function(d) {
					return canvasHeight - y(magnitudFn(d))
				});

				circles.enter().append("svg:rect");
				circles.attr("x", function(d, i) {
					return x(i);
				})
				// NOTE: The following "+15" adds an offset that ensures some space
				// between the top of the canvas and the top of the highest bar, so
				// that text can be added in that space, later.
				.on('mouseover', synchronizedMouseOver).on("mouseout",
						synchronizedMouseOut).attr("height", function(d) {
					return y(magnitudFn(d))
				}).attr("width", barWidth).style("fill", "White").style(
						"stroke", "White").style("fill", function(d, i) {
					colorVal = colorScale(i);
					return colorVal;
				}).attr("index_value", function(d, i) {
					return "index-" + i;
				}).attr("class", function(d, i) {
					return "bars-" + chartID + "-bar-index-" + i;
				}).attr("color_value", function(d, i) {
					return colorScale(i);
				}) // Bar fill color...
				.style("stroke", "white"); // Bar border color...

				circles.exit().remove();

			}

			// Plot the bullet circles...
			canvas.selectAll("circle").data(dataSet).enter().append(
					"svg:circle") // Append circle elements
			.attr("cx", barsWidthTotal + legendBulletOffset).attr("cy",
					function(d, i) {
						return legendOffset + i * 20;
					}).attr("stroke-width", ".5").style("fill", function(d, i) {
				return colorScale(i);
			}) // Bar fill color
			.attr("index_value", function(d, i) {
				return "index-" + i;
			}).attr("class", function(d, i) {
				return "bars-" + chartID + "-legendBullet-index-" + i;
			}).attr("r", 5).attr("color_value", function(d, i) {
				return colorScale(i);
			}) // Bar fill color...
			.on('mouseover', synchronizedMouseOver).on("mouseout",
					synchronizedMouseOut);

			// Create hyper linked text at right that acts as label key...
			canvas.selectAll("a.legend_link").data(dataSet) // Instruct to bind dataSet to text elements
			.enter().append("text").attr("text-anchor", "left").attr("x",
					barsWidthTotal + legendBulletOffset + legendTextOffset)
					.attr("y", function(d, i) {
						return legendOffset + i * 20 - 10;
					}).attr("dx", 0).attr("dy", "1em") // Controls padding to place text above bars
					.text(function(d) {
						return d.xCoordinate;
					}).style("fill", "Blue").attr("index_value",
							function(d, i) {
								return "index-" + i;
							}).attr("class", function(d, i) {
						return "bars-" + chartID + "-legendText-index-" + i;
					}).on('mouseover', synchronizedMouseOver).on("mouseout",
							synchronizedMouseOut);

			refreshGraph()

			function updateValue() {
				var value = $("#spinner").spinner("value");

				var value2 = $("#spinner2").spinner("value");

				var value3 = $("#spinner3").spinner("value");

				var value4 = $("#spinner4").spinner("value");

				var text = $("#units option:selected").text();
				return value * value2 * value3 * value4;
			}

			$(function() {
				$("#spinner").spinner({
					min : 1
				});
				$("#spinner").on("spinchange", function(event, ui) {
					$("#total").text(updateValue());
				});
				$("#spinner2").spinner({
					min : 1
				});
				$("#spinner2").on("spinchange", function(event, ui) {
					$("#total").text(updateValue());
				});
				$("#spinner3").spinner({
					min : 1
				});
				$("#spinner3").on("spinchange", function(event, ui) {
					$("#total").text(updateValue());
				});
				$("#spinner4").spinner({
					min : 1
				});
				$("#spinner4").on("spinchange", function(event, ui) {
					$("#total").text(updateValue());
				});

				$("#spinner5").spinner({
					min : 5,
					max : 100,
					step : 5
				});

				$("#spinner6").spinner({
					min : 0,
					step : 5
				});
				$("#spinner7").spinner({
					min : 0,
					step : 5
				});

				$("#units").change(function() {
					$("#total").text(updateValue());
				});
			});

			$(document).ready(
					function() {
						updateValue();
						$('#formSubmit').click(
								function() {
									var items = [];
									items.push("<td>"
											+ $("#tipo option:selected").text()
											+ "</td>");
									items.push("<td>"
											+ $("#spinner").spinner("value")
											+ "</td>");
									items.push("<td>"
											+ $("#spinner2").spinner("value")
											+ "</td>");
									items.push("<td>"
											+ $("#spinner3").spinner("value")
											+ "</td>");
									items.push("<td>"
											+ $("#spinner4").spinner("value")
											+ "</td>");
									items
											.push("<td>" + updateValue()
													+ "</td>");
									items.push("<td>" + $("#comments").val()
											+ "</td>");

									$("<tr/>", {
										html : items.join("")
									}).appendTo("#exercices");
									refreshGraph();
								});
						$("#form").submit(function() {
							return false;
						});
						
						$("#form2").submit(function() {
							return false;
						});
					});
		})();
		
		 
	</script>


</body>
</html>
