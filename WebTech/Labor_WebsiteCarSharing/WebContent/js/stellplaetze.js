/**
 * 
 */

"use strict";

var map;
function initialize() {
	map();
	showPlaces();
}
	
	
function map() {
	var latlng = new google.maps.LatLng(48.47598, 7.93434);
	var myOptions = {
		zoom : 13,
		center : latlng,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(document.getElementById("myMap"), myOptions);

	var marker1 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Jahnweg 15'
	});

	latlng = new google.maps.LatLng(48.47928, 7.94229);
	var marker2 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Rheinstr.10'
	});

	latlng = new google.maps.LatLng(48.46101, 7.92934);
	var marker3 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Meisenweg 3'
	});

	latlng = new google.maps.LatLng(48.46910, 7.95424);
	var marker4 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Moltkestr.49'
	});

	latlng = new google.maps.LatLng(48.62899, 8.07138);
	var marker5 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Hauptstr.35, Achern'
	});

	latlng = new google.maps.LatLng(48.34301, 7.87591);
	var marker6 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Turmstr.27, Lahr'
	});

	latlng = new google.maps.LatLng(48.36205, 7.83010);
	var marker7 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Heerstr.70 Lahr'
	});

	latlng = new google.maps.LatLng(48.54552, 7.97747);
	var marker8 = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Poststr.2 Appenweier'
	});

}

var stellplaetze = [ {
	"Ort" : "Offenburg",
	"Straße" : "Jahnweg 15",
	"breite" : "48.47598",
	"laenge" : "7.93434"
}, {
	"Ort" : "Offenburg",
	"Straße" : "Rheinstr 10",
	"breite" : "48.47928",
	"laenge" : "7.94229"
}, {
	"Ort" : "Offenburg",
	"Straße" : "Meisenweg 3",
	"breite" : "48.46101",
	"laenge" : "7.92934"
},{
	"Ort" : "Offenburg",
	"Straße" : "Moltkestr.49",
	"breite" : "48.46910",
	"laenge" : "7.95424"
},{
	"Ort" : "Achern",
	"Straße" : "Hauptstr.35",
	"breite" : "48.62899",
	"laenge" : "8.07138"
},{
	"Ort" : "Lahr",
	"Straße" : "Turmstr.27",
	"breite" : "48.34301",
	"laenge" : "7.87591"
},{
	"Ort" : "Lahr",
	"Straße" : "Heerstr.70",
	"breite" : "48.36205",
	"laenge" : "7.83010"
},{
	"Ort" : "Appenweier",
	"Straße" : "Poststr.2",
	"breite" : "48.54552",
	"laenge" : "7.97747"
} ]

var ids = ["place1", "place2", "place3", "place4", "place5", "place6", "place7", "place8"];

//called with onload in body
function showPlaces(){
	//alert("hier bin ich");
	var myOutput = "";
	var i;
	for(i = 0; i<stellplaetze.length; i++){
		myOutput += "<td>" + stellplaetze[i].Ort +"</td>";
		myOutput += "<td>" + stellplaetze[i].Straße +"</td>";
		myOutput += "<td>" + stellplaetze[i].breite +"</td>";
		myOutput += "<td>" + stellplaetze[i].laenge +"</td>";
	    document.getElementById(ids[i]).innerHTML = myOutput;
	    myOutput = "";
	}
}

function center(breite,laenge){
	var latlng = new google.maps.LatLng(breite, laenge);
	map.setZoom(15);
	  map.setCenter(latlng);
}