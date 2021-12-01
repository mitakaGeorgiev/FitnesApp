function CalculateLoged(){
	
	var height = document.getElementById("height").value
	var kilograms = document.getElementById("kilograms").value
	var sum=kilograms/height/height*10000;
	localStorage.setItem("sum", sum);
}

function CalculateUnloged(){
	var age = document.getElementById("ageUnloged").value
	var height = document.getElementById("heightUnloged").value
	var kilograms = document.getElementById("kilogramsUnloged").value
	
	var sum=kilograms/height/height*10000;
	localStorage.setItem("sum", sum);
}