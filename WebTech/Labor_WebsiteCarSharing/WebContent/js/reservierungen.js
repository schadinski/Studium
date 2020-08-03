/**
 * 
 */

var reservierungen = [ {
	"Zeitraum_von" : "25.12.2016, 10.30",
	"Zeitraum_bis" : "30.12.2016 18.30",
	"Kennzeichen" : "FR-OG 222",
	"Bereitstellungsort" : "Winkelgasse 13, Offenburg",
	"Preis" : "250,00€"
}, {
	"Zeitraum_von" : "12.01.2017, 8.00",
	"Zeitraum_bis" : "12.01.2017 10:00",
	"Kennzeichen" : "FR-A 852",
	"Bereitstellungsort" : "Rheinstr.12, Offenburg",
	"Preis" : "25,00€"
}, {
	"Zeitraum_von" : "03.02.2017, 19.30",
	"Zeitraum_bis" : "04.02.2017 9.00",
	"Kennzeichen" : "OG-KL 123",
	"Bereitstellungsort" : "Haupstr.3, Offenburg",
	"Preis" : "70,00€"
}, {
	"Zeitraum_von" : "23.03.2017, 10.30",
	"Zeitraum_bis" : "23.03.2017 16.30",
	"Kennzeichen" : "FR-GH 591",
	"Bereitstellungsort" : "Winkelgasse 13, Offenburg",
	"Preis" : "60,00€"
}, {
	"Zeitraum_von" : "30.03.2017, 7.00",
	"Zeitraum_bis" : "30.03.2017 12.00",
	"Kennzeichen" : "OG-LO 3489",
	"Bereitstellungsort" : "Hauptstr.3, Offenburg",
	"Preis" : "35,00€"
} ];

var ids = ["line1", "line2", "line3", "line4", "line5"];

//called with onload in body
function showReservierungen(){
	//alert("hier bin ich");
	var myOutput = "";
	var i;
	for(i = 0; i<reservierungen.length; i++){
		myOutput += "<td>" + reservierungen[i].Zeitraum_von +"</td>";
		myOutput += "<td>" + reservierungen[i].Zeitraum_bis +"</td>";
		myOutput += "<td>" + reservierungen[i].Kennzeichen +"</td>";
		myOutput += "<td>" + reservierungen[i].Bereitstellungsort +"</td>";
		myOutput += "<td>" + reservierungen[i].Preis +"</td>";
	    document.getElementById(ids[i]).innerHTML = myOutput;
	    myOutput = "";
	}
}
