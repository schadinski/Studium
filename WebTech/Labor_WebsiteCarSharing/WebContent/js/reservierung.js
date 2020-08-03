"use strict";

// called with onload in body-tag; do this first
function initialize() {
	setDateToday(0);
	setTime();
	document.getElementById("payWithCard").setAttribute("hidden", "hidden");
	document.getElementById("anotherAdr").setAttribute("hidden", "hidden");
	document.getElementById("anotherAdr").setAttribute("disabled", "disabled");
	//document.getElementById("billChoise").setAttribute(name, value)

}
// called by onclick in img-areas, set readonly input on the chose value
function setReadOnlyField(id) {
	switch (id) {
	case 0:
		document.getElementById("bereitstellungsort").value = "Hauptstr.2, Offenburg";
		break;
	case 1:
		document.getElementById("bereitstellungsort").value = "Rheinstr.15, Offenburg";
		break;
	default:
		document.getElementById("bereitstellungsort").value = "Winkelgasse 13, Offenburg";
		break;
	}
}

// called from initialized()
function setDateToday(shift) {
	var myDate = new Date();

	var today = (myDate.getDate() + shift) + "." + (myDate.getMonth() + 1)
			+ "." + myDate.getFullYear();

	if (shift == 0) {
		document.getElementById("mietbeginn_tag").value = today
				.toLocaleString();
	}
	document.getElementById("mietende_tag").value = today.toLocaleString();
	// document.write(typeof(document.getElementById("mietbeginn_tag").value));
	// document.write(typeof(today));

}

// called from initialized()
function setTime() {
	var time = new Date();
	var hours_start = time.getHours();
	var min = time.getMinutes();
	if(min<10){
		min = "0"+min;
	}

	document.getElementById("mietbeginn_zeit").value = (hours_start + "." + min)
			.toLocaleString();
	var hours_end = hours_start + 3;
	// end of day , should also consider end of month and end of year in
	// setDateToday(), but too less time
	if ((hours_end) > 23) {
		setDateToday(1);
		hours_end = hours_end % 24;
	}

	document.getElementById("mietende_zeit").value = (hours_end + "." + min)
			.toLocaleString();
}

// called with onchange and with onsubmit in "mietbeginn_tag" and "mietende_tag"
function compareAndSwitch() {
	// alert("first step");
	var date1 = document.getElementById("mietbeginn_tag").value;
	var date2 = document.getElementById("mietende_tag").value;
	// alert("second step");
	if ((date1 == "") || (date2 == "")) {
		return;
	} else {
		if (date1 > date2) {
			var conf = confirm("Das Mietende liegt vor dem Mietbeginn. Möchten Sie die Daten tauschen?");
			if (conf == true) {
				// var tempDate = date1;
				// date1 = date2;
				// date2 = tempDate;
				document.getElementById("mietbeginn_tag").value = date2;
				document.getElementById("mietende_tag").value = date1;
			}
		}

	}
}

//called with anchange in radiobuttons for "Bezahlmethode"
function hideBillOrCard(){
	if(document.getElementById("pay").value == "visa" || "mastercard"){
		//window.alert("verstecke rechnung, zeige karte");
		document.getElementById("payWithBill").setAttribute("hidden", "hidden");
		document.getElementById("payWithBill").setAttribute("disabled", "disabled");
		document.getElementById("payWithCard").removeAttribute("hidden");
		document.getElementById("payWithCard").removeAttribute("disabled");
	}
	if(document.getElementById("pay").value == "rechnung"){
		//window.alert("verstecke karte, zeige rechnung");
		document.getElementById("payWithBill").removeAttribute("hidden");
		document.getElementById("payWithBill").removeAttribute("disabled");
		document.getElementById("payWithCard").setAttribute("hidden", "hidden");
		document.getElementById("payWithCard").setAttribute("disabled", "disabled");
	}
}

//called with onchange in "rechnungsadresse"
function hideAddress(){
	if(document.getElementById("rechnung_abweich").checked){
		//window.alert("zeige form");
		document.getElementById("anotherAdr").removeAttribute("hidden");
		document.getElementById("anotherAdr").removeAttribute("disabled");
	}
	if(document.getElementById("rechnung_adr").checked  ){
		//window.alert("verstecke form");
		document.getElementById("anotherAdr").setAttribute("hidden", "hidden");
		document.getElementById("anotherAdr").setAttribute("disabled", "disabled");
	}
}

//called with onsubmit
function isValid(){
	if( (document.getElementById("gueltig_year").value == "") || (document.getElementById("gueltig_month").value == "") ){
		return;
	}
	
	
	
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth();
	
	var input_year = document.getElementById("gueltig_year").value;
	var input_month = document.getElementById("gueltig_month").value;
	
	var input_date = new Date(input_year, input_month, 1);
	
	if(today > input_date){
		alert("Achtung! Die Gültigkeit ihrer kreditkarte ist abglaufen. Bitt überprüfen Sie Ihre Eingabe.");
		document.getElementById("gueltig_year").value = "";
		document.getElementById("gueltig_month").value = "";
		document.getElementById("gueltig_month").focus();
	}
}



