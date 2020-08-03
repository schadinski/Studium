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
	var baum;
	var car;
	theSimulation = new demoSimulation(theCanvasID);

	// Bsp: Objekte in eine Liste hinzufügen
	theSimulation.addNewGroup("baeume", false, true);
	for (var i = 0; i < 10; i++) {
		baum = new baumElement(theSimulation);
		baum.setPosRandom(0, 0, 945, 600);
		baum.setScaleRandom(0.5, 2, 0.5, 2);
		theSimulation.addObjectToGroup(baum, "baeume");
	}
	theSimulation.addNewGroup("autos", true, true);
	for (var i = 0; i < 4; i++) {
		car = new autoElement(theSimulation);
		car.setPosRandom(0, 0, 670, 487);
		theSimulation.addObjectToGroup(car, "autos");
	}

	/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
	theSimulation.animate();
	//window.requestAnimFrame(frameSim);
}

function demoSimulation(theCanvasID) {
	simulation.call(this, theCanvasID);

	this.drawBackground = function() {

		this.ctx.save();
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
		// TODO:
		// draw my background here
		var verlauf = this.ctx
				.createLinearGradient(0, 0, 0, this.canvas.height);
		verlauf.addColorStop(0, "rgb(0, 0, 153)");// dunkelblau
		verlauf.addColorStop(0.2, "rgb(153, 235, 255)");// hellblau
		verlauf.addColorStop(0.5, "rgb(51, 204, 51)");// hellgrün
		verlauf.addColorStop(0.8, "rgb(25, 102, 25)");// dunkelgrün
		this.ctx.fillStyle = verlauf;
		this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
		/** <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
		this.ctx.restore();

	}
	// setInterval( (.isEndOfCanvas(theSimulation) ),16);
	// this.canvas.getBoundingClientRect();autoElement
	// setInterval( ( this.listObjectGroups[2].isEndOfCanvas(this.canvas,
	// this.listObjectGroups[2] ) ),500);

}

function baumElement(theSimulation) {
	// Vererbung, entweder staticObjekt oder dynObjekt oder eigene
	// ObjektTemplates
	staticObjekt.call(this, theSimulation);

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {
		this.simInstance.ctx.save();

		/** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
		var imageObj = new Image();
		imageObj.src = "../images/carsAndTrees/baum.png";
		// imageObj.width = 50% ;
		this.simInstance.ctx.scale(this.xScale, this.yScale);
		this.simInstance.ctx.drawImage(imageObj, this.xPos, this.yPos);

		this.simInstance.ctx.restore();
	}
}

/** Beispielobjekt * */
function autoElement(theSimulation) {

	dynObjekt.call(this, theSimulation);
	var xv = (Math.floor(Math.random() * 8)) + 1;
	var yv = (Math.floor(Math.random() * 4)) + 1;
	var mySources = [ "../images/carsAndTrees/autoGruen.png",
			"../images/carsAndTrees/autoRot.png",
			"../images/carsAndTrees/autoBlau.png" ];
	var i = Math.floor(Math.random() * 3);

	// var direction = true; // true = vorwärts; false = rückwärts

	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {
		// /** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
		// aufprall rechts
		if (this.xPos > 680) {
			// yv bleibt gleich!
			xv = xv * (-1);
		}
		if (this.xPos < 0) {
			xv = xv * (-1);
		}
		if (this.yPos > 487) {
			yv = yv * (-1);

		}
		if (this.yPos < 0) {
			yv = yv * (-1);
		}


		//		
		// if( (this.xPos > 680) && ( direction == true) ){
		// this.xPos = 679;
		// xv = -xv;
		// direction = false;
		// }
		//			
		// if( this.xPos < 0 && ( direction == false)){
		// xv = 5;
		// direction = true;
		// }
		this.xPos = this.xPos + xv;
		this.yPos = this.yPos + yv;

		// /** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */
	}

	// ********* Zeichnen des eigenen Objekts
	this.draw = function() {

		this.simInstance.ctx.save();

		var auto = new Image();

		auto.src = mySources[i];// "../images/carsAndTrees/autoGruen.png"
		this.simInstance.ctx.drawImage(auto, this.xPos, this.yPos);
		// // Transformation
		// this.simInstance.ctx.translate(this.xPos, this.yPos);
		// this.simInstance.ctx.rotate(this.rotation*Math.PI/180);
		// this.simInstance.ctx.scale(this.xScale, this.yScale);
		//
		// /** *<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<** */
		// // Farben bestimmen
		// this.simInstance.ctx.fillStyle = "rgba(255,0,0,1.0)";
		//
		// // Objekt zeichnen
		// this.simInstance.ctx.fillRect(0, 0, 10, 10);
		/** *>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>** */

		this.simInstance.ctx.restore();
	}

}