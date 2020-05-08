var provinceSelect = document.getElementById("province");
get(BASE_URL + "/province/getAll", function(data) {
	for (let i = 0; i < data.length; i++) {
		option = document.createElement("option");
		option.value = data[i].id;
		option.text = data[i].name;
		provinceSelect.add(option);
	}
})
