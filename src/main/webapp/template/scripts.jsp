<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<link rel="stylesheet" href="css/themes/base/jquery.ui.all.css">
<script src="js/bootstrap.min.js"></script>
<link href='js/fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='js/fullcalendar/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='js/lib/jquery-ui.custom.min.js'></script>
<script src='js/fullcalendar/fullcalendar.min.js'></script>
<script src='js/d3/d3.v3.min.js'></script>
<script src='js/ui/jquery-ui.js'></script>
<script src='js/esp-utils.js'></script>
<script>
	function set_cookie(cookie_name, cookie_value, lifespan_in_days,
			valid_domain) {
		// http://www.thesitewizard.com/javascripts/cookies.shtml
		var domain_string = valid_domain ? ("; domain=" + valid_domain) : '';
		document.cookie = cookie_name + "=" + encodeURIComponent(cookie_value)
				+ "; max-age=" + 60 * 60 * 24 * lifespan_in_days + "; path=/"
				+ domain_string;
	}

	function get_cookie(cookie_name) {
		// http://www.thesitewizard.com/javascripts/cookies.shtml
		var cookie_string = document.cookie;
		if (cookie_string.length != 0) {
			var cookie_value = cookie_string.match('(^|;)[\s]*' + cookie_name
					+ '=([^;]*)');
			return decodeURIComponent(cookie_value[2]);
		}
		return '';
	}
</script>