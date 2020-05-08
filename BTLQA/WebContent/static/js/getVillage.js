var villageSelect = document.getElementById("village");
function getVillage() {
	var i = villageSelect.length - 1;
	while (i >= 0) {
		villageSelect.remove(i--);
	}
	option = document.createElement("option");
	option.value = "";
	option.text = "-Chọn Phường/Xã-";
	villageSelect.add(option);
	get(BASE_URL + "/village/getByDistrict?id="
			+ document.getElementById("district").value, function(data) {
		for (let i = 0; i < data.length; i++) {
			option = document.createElement("option");
			option.value = data[i].id;
			option.text = data[i].name;
			villageSelect.add(option);
		}
	})
}
