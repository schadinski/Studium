"use strict";

/*******************************************************************************
 * Beginnen Sie mit dieser Datei und erarbeiten Sie sich die Beispiele im
 * Dokuprojekt (s. Moodle)
 ******************************************************************************/

var count1 = 0;
var count2 = 0;
var count3 = 0;
var count4 = 0;

/**
 * Hauptfunktion. Sie wird bei onload aufgerufen
 * 
 */
function initialize(theCanvasID) {
	theSimulation = new DemoSimulation(theCanvasID);

	/** >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
	var gras;
	var haus;
	var truck;
	var man;
	var fire;
//	var myX = 275;
//	var myY;
	// var truckSpeed =parseInt(document.getElementById("nr").value); //
	// document.getElementById("nr").value; //soll zahlenwert eingelesen werden

	// = document.getElementById("nr").value;
	theSimulation.setEvent_mouseDown(true);
	// theSimulation.setEvent_keyDown(true);

	theSimulation.addNewGroup("Gras", false, true);
	for (var i = 0; i < 5; i++) {
		gras = new grasElement(theSimulation);
		gras.setPosRandom(0, 480, 980, 60);
		theSimulation.addObjectToGroup(gras, "Gras");
	}

	theSimulation.addNewGroup("Haeuser", false, true);
	for (var i = 0; i < 5; i++) {
		haus = new hausElement(theSimulation, i);
		theSimulation.addObjectToGroup(haus, "Haeuser");
	}

	// start();
	// theSimulation.addNewGroup("feuerwehr", true, true);
	// truck = new firetruck(theSimulation,truckSpeed);
	// theSimulation.addObjectToGroup(truck, "feuerwehr");

	theSimulation.addNewGroup("feuerwehrmann", false, true);
	man = new firefighter(theSimulation);
	theSimulation.addObjectToGroup(man, "feuerwehrmann");
	
	theSimulation.addNewGroup("fire1", false, true);
	fire = new feuer1Element(theSimulation, 275, 110);
	theSimulation.addObjectToGroup(fire, "fire1");
	
	theSimulation.addNewGroup("fire2", false, true);
	fire = new feuer1Element(theSimulation, 475, 110);
	theSimulation.addObjectToGroup(fire, "fire2");
	
	theSimulation.addNewGroup("fire3", false, true);
	fire = new feuer1Element(theSimulation, 675, 110);
	theSimulation.addObjectToGroup(fire, "fire3");
	
	theSimulation.addNewGroup("fire4", false, true);
	fire = new feuer1Element(theSimulation, 875, 110);
	theSimulation.addObjectToGroup(fire, "fire4");


//	theSimulation.addNewGroup("fires", false, true);
//	for (var i = 0; i < 4; i++) {
//		myY = 110;
//		fire = new feuer1Element(theSimulation, myX, myY);
//		myX += 200;
//		theSimulation.addObjectToGroup(fire, "fires");
//	}
	theSimulation.addNewGroup("water", true, true);

	/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
}

function start() {
	var truck;
	var truckSpeed = parseInt(document.getElementById("nr").value);
	theSimulation.addNewGroup("feuerwehr", true, true);
	truck = new firetruck(theSimulation, truckSpeed);
	theSimulation.addObjectToGroup(truck, "feuerwehr");
	document.getElementById("myButton").setAttribute("disabled", "disabled");

	// document.getElementById("nr").setAttribute("disabled", "disabled");

	theSimulation.animate();
}

function DemoSimulation(theCanvasID) {
	// Aufruf der Superklasse
	Simulation.call(this, theCanvasID);

	// die Funktion drawBackground muss hier überschrieben werden.
	this.drawBackground = function() {

		this.ctx.save();
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		var verlauf = this.ctx
				.createLinearGradient(0, 0, 0, this.canvas.height);

		if (document.getElementById("night").checked) {
			// TODO if(night) evtl.sehr transparentes blau oder schwarz
			// drüberlegen,
			// random sterne
			verlauf.addColorStop(0, "rgb(0, 0, 153)");// dunkelblau
		}
		else if (document.getElementById("day").checked) {
			verlauf.addColorStop(0, "rgb(153, 235, 255)");// hellblau
		}

		verlauf.addColorStop(0.5, "rgb(37, 160, 9)");// Grünstreifen
		this.ctx.fillStyle = verlauf;
		this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
		this.ctx.restore();

		// graue Straße
		this.ctx.save();
		this.ctx.fillStyle = "rgb(104, 109, 103)";
		this.ctx.fillRect(0, 330, this.canvas.width, 180);
		this.ctx.restore();

		// Mittelstreifen
		this.ctx.save();
		this.ctx.fillStyle = "rgb(255, 255, 255)";
		this.ctx.fillRect(0, 410, this.canvas.width, 25);
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		this.ctx.restore();

	}

	this.cb_mouseDown = function(evt) {
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		var myFunction;

		var fire;
		var mousePos = theSimulation.getMousePos(evt);
		// click überhaupt auf y Höhe der Häuser?
		if (mousePos.y > 85 && mousePos.y < 330) {
			// Haus 2 geklickt
			if (mousePos.x > 200 && mousePos.x < 400) {
				// for(var i = 0; i<15; i++)
				// {
				// window.setTimeout(
				myFunction = new wasserMarsch(1), 50;
				count1++;
				//alert(count1);
				// );
				// }
			}
			// Haus 3 geklickt
			else if (mousePos.x > 400 && mousePos.x < 600) {
				myFunction = new wasserMarsch(2);
				count2++;
			}
			// Haus 4 geklickt
			else if (mousePos.x > 600 && mousePos.x < 800) {
				myFunction = new wasserMarsch(3);
				count3++;
			}
			// Haus 5 geklickt
			else if (mousePos.x > 800 && mousePos.x < 1000) {
				myFunction = new wasserMarsch(4);
				count4++;
			} else {
				return;
			}

			theSimulation.addNewGroup("loeschen", true, true);
			theSimulation.addObjectToGroup(myFunction, "loeschen");
		}
		
		//feuer gelöscht, entferne feuer
		if( count1 > 20 ){
			theSimulation.changeGroupProp("fire1", false, false);
//			theSimulation.addNewGroup("smoke1", false, true);
//			var smoke1 = new rauch(theSimulation, 275, 110);
//			theSimulation.addObjectToGroup(smoke1, "smoke1");
			//theSimulation.drawGroup(smoke1);
		}
		if( count2 > 20 ){
			theSimulation.changeGroupProp("fire2", false, false);
//			theSimulation.addNewGroup("smoke2", false, true);
//			var smoke2 = new rauch(theSimulation, 475, 110);
//			theSimulation.addObjectToGroup(smoke2, "smoke2");
			}
		if( count3 > 20 ){
			theSimulation.changeGroupProp("fire3", false, false);
//			theSimulation.addNewGroup("smoke3", false, true);
//			var smoke3 = new rauch(theSimulation, 675, 110);
//			theSimulation.addObjectToGroup(smoke3, "smoke3");
			}
		if( count4 > 20 ){
			theSimulation.changeGroupProp("fire4", false, false);
//			theSimulation.addNewGroup("smoke4", false, true);
//			var smoke4 = new rauch(theSimulation, 875, 110);
//			theSimulation.addObjectToGroup(smoke4, "smoke4");
			}
		// alert("test Down x:" + mousePos.x + " y: " + mousePos.y);
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	}
}

function grasElement(theSimulation) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	StaticObjekt.call(this, theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		var gras = new Image();
		gras.src = "../images/gras.png";
		this.simInstance.ctx.drawImage(gras, this.xPos, this.yPos);

		this.simInstance.ctx.restore();
	}
}

function hausElement(theSimulation, i) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	StaticObjekt.call(this, theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		var mySources = [ "../images/haus2.png", "../images/haus1.png",
				"../images/haus3.png", "../images/haus4.png",
				"../images/haus5.png" ];

		var myX = i * 200;

		var haus = new Image();
		haus.src = mySources[i];
		// this.simInstance.ctx.scale(50, 50);
		this.simInstance.ctx.drawImage(haus, myX, 86);

		this.simInstance.ctx.restore();
	}
}

function feuer1Element(theSimulation, myX, myY) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	StaticObjekt.call(this, theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
		var feuer1 = new Image();
		feuer1.src = "../images/fire1.png";
		this.simInstance.ctx.drawImage(feuer1, myX, myY);
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */

		this.simInstance.ctx.restore();
	}

}


function firetruck(theSimulation, myXv) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	DynObjekt.call(this, theSimulation);

	this.xVel = myXv;
	// alert(myXv);
	// this.setVelocity(myXv,0);

	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {
		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */

		if (this.xPos > 900) {
			// yv bleibt gleich!
			this.xVel = this.xVel * (-1);
		}
		if (this.xPos < -100) {
			this.xVel = this.xVel * (-1);
		}

		this.xPos = this.xPos + this.xVel;
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
	}

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		var truck = new Image();
		truck.src = "../images/firetruck.png";
		this.simInstance.ctx.drawImage(truck, this.xPos, 300);

		this.simInstance.ctx.restore();
	}

}

function firefighter(theSimulation) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	StaticObjekt.call(this, theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		var man = new Image();
		man.src = "../images/firefight.png";
		this.simInstance.ctx.drawImage(man, 0, 230);
		this.simInstance.ctx.restore();
	}

}

function water(theSimulation, nr) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	// staticObjekt.call(this,theSimulation);
	DynObjekt.call(this, theSimulation);
	// alert("grad="+grad+"xv="+xv+"yv="+yv)

	var grad;
	var localxv = 3;
	var localyv = 0;
	var startX;
	var startY = 260;

	// alert(nr);
	switch (nr) {
	case 1:
		var grad = 305;
		var startX = 200;
		break;
	case 2:
		var grad = 305;
		var startX = 400;
		break;
	case 3:
		var grad = 315;
		var startX = 600;
		break;
	default:
		var grad = 315;
		var startX = 800;
		break;
	}

	this.setPos(startX, startY);

	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {
		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */

		this.xPos = this.xPos + localxv;
		this.yPos = this.yPos + localyv;
		if (this.yPos < 85) {
			// lösche das Element, bzw beginne von vorne
		}
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
	}

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		if (this.yPos < 260) {
			this.simInstance.ctx.fillStyle = "rgba(63, 53, 204, 0.0)";
			// lösche das Element, bzw beginne von vorne
		} else {
			this.simInstance.ctx.fillStyle = "rgba(63, 53, 204, 1.0)";

		}

		// translate to the center of waterElement
		this.simInstance.ctx.translate((startX + 15), (startY + 15));
		// rotate 315Grad
		this.simInstance.ctx.rotate(grad * (Math.PI / 180));
		// translate the canvas back to 0,0
		this.simInstance.ctx.translate((-(startX + 15)), (-(startY + 15)));

		this.simInstance.ctx.fillRect(this.xPos, this.yPos, 30, 30);

		this.simInstance.ctx.restore();
	}

}

function wasserMarsch(nrOfFire) {

	DynObjekt.call(this, theSimulation);

	var wasser = new water(theSimulation, nrOfFire);
	theSimulation.addObjectToGroup(wasser, "water");
}

//TODO rauch erscheint nicht
//rauch
function rauch(x,y){
	StaticObjekt.call(this, theSimulation);
	this.draw = function() {
		this.simInstance.ctx.save();

		var smoke = new Image();
		smoke.src = "../images/rauch.png";
		this.simInstance.ctx.drawImage(smoke, x, y);
		this.simInstance.ctx.restore();
	}
}