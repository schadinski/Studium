/*******************************************************************************
 * Datei enthält die zentralen Funktionen
 ******************************************************************************/
// http://www.html5canvastutorials.com/
"use strict";

/*******************************************************************************
 * Initialisierung (wird bei onload aufgerufen)
 ******************************************************************************/
// !!!!!!!!!!!!!!!!!!!! Closure für Callback:
// http://molily.de/js/organisation-verfuegbarkeit.html
// !!!!!!!!!!!!!!!!!!! guter Artikel
function initialize(theCanvasID) {
	var gras;
	var haus;
	var truck;
	var man;
	theSimulation = new demoSimulation(theCanvasID);

	/** >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
	// TODO:
	theSimulation.setEvent_mouseDown(true);
	// theSimulation.setEvent_mouseMove(true);
	// theSimulation.setEvent_mouseUp(true);
	// theSimulation.setEvent_keyDown(true);
	// Bsp: Objekte in eine Liste hinzufügen
	// theSimulation.addNewGroup("Haupt", true, true);
	// for (var i = 0; i < 20; i++) {
	// object1 = new myObjekt(theSimulation);
	// object1.setPosRandom(0, 0, 500, 500);
	// object1.setRotationRandom(-90, 90);
	// object1.setScaleRandom(0, 2, 0, 2);
	// theSimulation.addObjectToGroup(object1, "Haupt");
	// }
	theSimulation.addNewGroup("Gras", false, true);
	for (var i = 0; i < 5; i++) {
	gras = new grasElement(theSimulation);
	gras.setPosRandom(0, 480, 980, 60);
	theSimulation.addObjectToGroup(gras, "Gras");
	}
	
	 theSimulation.addNewGroup("Haeuser", false, true);
	 for (var i = 0; i < 5; i++) {
	 haus = new hausElement(theSimulation,i);
	 //haus.setPos(0, 0);
	 theSimulation.addObjectToGroup(haus, "Haeuser");
	 }

	 theSimulation.addNewGroup("feuerwehr", true, true);
		truck = new firetruck(theSimulation);
		theSimulation.addObjectToGroup(truck, "feuerwehr");
	 theSimulation.addNewGroup("feuerwehrmann", false, true);
		man = new firefighter(theSimulation);
		theSimulation.addObjectToGroup(man, "feuerwehrmann");

	/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	//theSimulation.animate();
	window.requestAnimFrame(frameSim);
}

function demoSimulation(theCanvasID) {
	Simulation.call(this, theCanvasID);

	this.drawBackground = function() {

		this.ctx.save();
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		// draw my background here
		var verlauf = this.ctx
				.createLinearGradient(0, 0, 0, this.canvas.height);
		
		//if(night) evtl.sehr transparentes blau oder schwarz drüberlegen, random sterne
		verlauf.addColorStop(0, "rgb(0, 0, 153)");// dunkelblau
		//if(day)
		//verlauf.addColorStop(0, "rgb(153, 235, 255)");// hellblau
		
		verlauf.addColorStop(0.5, "rgb(37, 160, 9)");// Grünstreifen
		this.ctx.fillStyle = verlauf;
		this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
		this.ctx.restore();

		//graue Straße
		this.ctx.save();
		this.ctx.fillStyle = "rgb(104, 109, 103)";
		this.ctx.fillRect(0, 330, this.canvas.width, 180);
		this.ctx.restore();

		//Mittelstreifen
		this.ctx.save();
		this.ctx.fillStyle = "rgb(255, 255, 255)";
		this.ctx.fillRect(0, 410, this.canvas.width, 25);
		this.ctx.restore();
	}

	/** ************************************************************************ */
	/** ******* Bereich für weitere Funktionen der Simulation******************* */
	/** ************************************************************************ */
	/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
	// TODO:
	// function drawBackground(){
	// var theCanvas = document.getElementById("firstCanvas");
	// }
	/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

	/** ************************************************************************ */
	/** ***************** Events *********************************************** */
	/** ************************************************************************ */
	this.cb_mouseMove = function(evt) {
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		var mousePos = theSimulation.getMousePos(evt);
		alert("test Move x:" + mousePos.x + " y: " + mousePos.y);
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	}

	this.cb_mouseDown = function(evt) {
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		
		setIntervall(feuer1Element(theSimulation, mousePos.x, mousePos.y), 200);
		
		setIntervall(feuer2Element(theSimulation, mousePos.x, mousePos.y), 100);
		

//		var mousePos = theSimulation.getMousePos(evt);
//		alert("test Down x:" + mousePos.x + " y: " + mousePos.y);
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	}

	this.cb_mouseUp = function(evt) {
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		var mousePos = theSimulation.getMousePos(evt);
		alert("test Up x:" + mousePos.x + " y: " + mousePos.y);
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	}

	this.cb_keyDown = function(evt) {
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		alert("test KeyCode" + evt.keyCode);

		switch (evt.keyCode) {
		case 38: /* Up arrow was pressed */

			break;
		case 40: /* Down arrow was pressed */

			break;
		case 37: /* Left arrow was pressed */

			break;
		case 39: /* Right arrow was pressed */

			break;
		}
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	}
}

/** ******************************************************************** */
/** ********** Objekt ************************************************** */
/** ******************************************************************** */
/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */

/** Beispielobjekt * */
function grasElement(theSimulation) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	 StaticObjekt.call(this,theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		
		var gras = new Image();
		gras.src = "../images/gras.png";
		this.simInstance.ctx.drawImage(gras, this.xPos, this.yPos);

		this.simInstance.ctx.restore();
	}
}

function hausElement(theSimulation,i) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	 StaticObjekt.call(this,theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		
		var mySources = [ "../images/haus2.png",
		      			"../images/haus1.png",
		      			"../images/haus3.png",
		      			"../images/haus4.png",
		      			"../images/haus5.png"];
		
		var myX = i *200;
		
		var haus = new Image();
		haus.src = mySources[i];
//		this.simInstance.ctx.scale(50, 50);
		this.simInstance.ctx.drawImage(haus, myX, 86);

		this.simInstance.ctx.restore();
	}
}

function feuer1Element(theSimulation,myX,myY) {
// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
// ObjektTemplates
StaticObjekt.call(this,theSimulation);

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

function feuer2Element(theSimulation,myX,myY) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	StaticObjekt.call(this,theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
			var feuer2 = new Image();
			feuer2.src = "../images/fire2.png";
			this.simInstance.ctx.drawImage(feuer2, myX, myY);
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */

		this.simInstance.ctx.restore();
	}

	}

function firetruck(theSimulation) {
// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
// ObjektTemplates
//// staticObjekt.call(this,theSimulation);
DynObjekt.call(this, theSimulation);

var xv = 3;

// ********* Berechnung des nächsten Schritts
this.calcNextStep = function() {
	/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
	if (this.xPos > 200) {
		xv = 0;
	}
	
	this.xPos = this.xPos + xv;
	//this.yPos = this.yPos;
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
	 StaticObjekt.call(this,theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		
		var man = new Image();
		man.src = "../images/firefight.png";
		if(truckstop == true){
			this.simInstance.ctx.drawImage(man, 0, 295);
		}
		this.simInstance.ctx.restore();
	}

	}

function water(theSimulation) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	//staticObjekt.call(this,theSimulation);
	DynObjekt.call(this, theSimulation);
		var xv = 3;
		var yv = 3;
	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {
		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
		
		
		this.xPos = this.xPos + xv;
		this.yPos = this.yPos + yv
		//this.yPos = this.yPos;
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
	}

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		var drop;
		drop
		
		this.simInstance.ctx.draw(truck, this.xPos, 300);

		this.simInstance.ctx.restore();
	}

	}


// TODO:
/** Beispielobjekt * */
//function myObjekt(theSimulation) {
//	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
//	// ObjektTemplates
//	// staticObjekt.call(this,theSimulation);
//	dynObjekt.call(this, theSimulation);
//
//	// ********* Berechnung des nächsten Schritts
//	this.calcNextStep = function() {
//		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
//		this.xVel = this.xVel + (this.xVel * this.xAccel);
//		this.yVel = this.yVel + (this.yVel * this.yAccel);
//		this.rotationVel = this.rotationVel
//				+ (this.rotationVel * this.rotationAccel);
//		this.xScaleVel = this.xScaleVel + (this.xScaleVel * this.xScaleAccel);
//		this.yScaleVel = this.yScaleVel + (this.yScaleVel * this.yScaleAccel);
//
//		this.xPos = this.xPos + this.xVel;
//		this.yPos = this.yPos + this.yVel;
//
//		this.rotation = this.rotation + this.rotationVel;
//		this.xScale = this.xScale + this.xScaleVel;
//		this.yScale = this.yScale + this.yScaleVel;
//		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
//	}
//
//	// ********* Zeichnen des eigenen Objekts
//	this.draw = function() {
//		this.simInstance.ctx.save();
//		// Transformation
//		this.simInstance.ctx.translate(this.xPos, this.yPos);
//		this.simInstance.ctx.rotate(this.rotation * Math.PI / 180);
//		this.simInstance.ctx.scale(this.xScale, this.yScale);
//
//		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
//		// Farben bestimmen
//		this.simInstance.ctx.fillStyle = "rgba(255,0,0,1.0)";
//
//		// Objekt zeichnen
//		this.simInstance.ctx.fillRect(0, 0, 10, 10);
//		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
//
//		this.simInstance.ctx.restore();
//	}

//}
/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
