const BASE_URL = "http://" + location.host + "/BTLQA";
var xmlhttp;
if (window.XMLHttpRequest) {
	xmlhttp = new XMLHttpRequest();
} else {
	// code for older browsers
	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}
function get(url, callback) {
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			callback(JSON.parse(this.responseText));
		}
	};
	xmlhttp.open('GET', url, true);
	xmlhttp.send();
}