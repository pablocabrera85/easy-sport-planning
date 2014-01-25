	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Deportes</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="trainnerLink"><a href="trainners.jsp">Entrenadores</a></li>
				<li id="usersLink"><a href="users.jsp">Atletas</a></li>
				<li id="usersTournament"><a href="tournaments.jsp">Competencias</a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Buscar</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="user_home.jsp"><span class="glyphicon glyphicon-user"></span>Usuario</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Configuracion<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"></a></li>
						<li><a href="#">Perfil</a></li>
						<li><a href="#">Cuenta</a></li>
						<li class="divider"></li>
						<li><a href="#">Salir</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</nav>