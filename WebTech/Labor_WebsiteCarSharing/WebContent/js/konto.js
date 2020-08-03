"use strict";

//called with onload in body-tag; do this first
function initialize() {
	var button1 = document.getElementById("submit_konto");
	button1.disabled = true;
	var haken = document.getElementById("haken");
	haken.style.display = "none";

}

//called with onclick in checkbox_abgs, enables and disables submit-button
function submitButton() {
	var button1 = document.getElementById("submit_konto");
	if (button1.disabled == true) {
		button1.disabled = false;
	} else if (button1.disabled == false) {
		button1.disabled = true;
	}
}

//called with onchange in password-input, checks are pws same
function isSame() {
	var isEmptyPw2 = document.getElementById("pw2").value;
	if (isEmptyPw2 == "") {
		return;
	} else {
		var pw1 = String(document.getElementById("pw1").value);
		var pw2 = String(document.getElementById("pw2").value);
		var res = pw1.localeCompare(pw2);

		var haken = document.getElementById("haken");

		if (res == 0) {
			// alert("good");

			haken.style.display = "block";
		} else if (res != 0) {
			// alert ("Bad");
			haken.style.display = "none";
			var conf = confirm("Passwörter nicht identisch!");
			if (conf == true) {
				document.getElementById("pw1").value = "";
				document.getElementById("pw2").value = "";
				document.getElementById("pw1").focus();
			}
		}
	}
}
