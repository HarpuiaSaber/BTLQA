var districtSelect = document.getElementById("district");
function getDistrict() {
	var i = districtSelect.length - 1;
	while (i >= 0) {
		districtSelect.remove(i--);
	}
	option = document.createElement("option");
	option.value = "";
	option.text = "-Chọn Quận/Huyện-";
	districtSelect.add(option);
	get(BASE_URL + "/district/getByProvince?id="
			+ document.getElementById("province").value, function(data) {
		for (let i = 0; i < data.length; i++) {
			option = document.createElement("option");
			option.value = data[i].id;
			option.text = data[i].name;
			districtSelect.add(option);
		}
	})
}
