/*******************************************************************************
 * Datei enthält die zentralen Funktionen
 ******************************************************************************/
// http://www.html5canvastutorials.com/
"use strict";

/*******************************************************************************
 * Initialisierung (wird bei onload aufgerufen)
 ******************************************************************************/
document.write('<script src="../js/SAT.js"></script>');

var theSimulation;

// https://github.com/sethladd/box2d-javascript-fun/blob/master/static/01/index.html
window.requestAnimFrame = (function() {
	return window.requestAnimationFrame || window.webkitRequestAnimationFrame
			|| window.mozRequestAnimationFrame || window.oRequestAnimationFrame
			|| window.msRequestAnimationFrame
			|| function(/* function */callback, /* DOMElement */element) {
				window.setTimeout(callback, 1000 / 60);
			};
})();

// public - Konstruktor
function Simulation(theCanvasID) {
	this.ctx;// Kontext des Canvas-Elements
	this.canvas; // CanvasElement
	this.listObjectGroups = new Array(); // Liste von Objektgruppen
	/***************************************************************************
	 * Zentraler Aufruf des Zeichnens. die draw-Aufrufe für die Unterbereiche
	 * erfolgt hier
	 **************************************************************************/

	// public
	this.getCanvasRect = function() {
		return (this.canvas.getBoundingClientRect());
	}

	// public
	this.animate = function() {
		this.calcNextStep();
		this.handleCollision();
		this.draw();
		window.requestAnimFrame(this.animate.bind(this));
	}

	this.draw = function() {
		this.ctx.save();
		this.drawBackground();
		// Zeichnen von Elementen
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			this.drawGroup(this.listObjectGroups[i]);
		}
		this.ctx.restore();
	};

	// muss in Childklasse überschrieben werden
	this.drawBackground = function() {
		this.ctx.save();
		var lingrad = this.ctx
				.createLinearGradient(0, 0, 0, this.canvas.height);
		lingrad.addColorStop(0, '#00ABEB');
		lingrad.addColorStop(0.5, '#fff');
		lingrad.addColorStop(0.5, '#26C000');
		lingrad.addColorStop(1, '#fff');

		// assign gradients to fill and stroke styles
		this.ctx.fillStyle = lingrad;
		this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);

		this.ctx.restore();
	}

	this.drawGroup = function(theGroup) {
		this.ctx.save();
		for (var i = 0; i < theGroup.objListe.length; i++) {
			if (theGroup.doDraw) {
				theGroup.objListe[i].draw();
			}
		}
		this.ctx.restore();
	}

	this.calcNextStep = function() {
		// Neuberechnen des nächsten Schritts bei Animationen
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			this.calcNextStepGroup(this.listObjectGroups[i]);
		}

	};

	this.calcNextStepGroup = function(theGroup) {

		for (var i = 0; i < theGroup.objListe.length; i++) {
			if (theGroup.doCalcNext) {
				theGroup.objListe[i].calcNextStep();
			}
		}

	}

	this.handleCollision = function() {
		// ES werden solange alle Kollisionen aufgelöst, bis es keine mehr gibt.
		// Eine Auflösung kann wiederum eine neue Kollision von einem bereits
		// verarbeiteten Objekt zur Folge haben
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			for (var j = 0; j < this.listObjectGroups[i].objListe.length; j++) {
				this
						.handleCollisionForObject(this.listObjectGroups[i].objListe[j]);
			}
		}
	}

	this.handleCollisionForObject = function(theObject) {
		var colResult;
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			for (var j = 0; j < this.listObjectGroups[i].objListe.length; j++) {
				if (theObject != this.listObjectGroups[i].objListe[j]) {
					 //Objekt muss sich nicht selber überprüfen
					colResult = theObject
							.checkCollisionWithObject(this.listObjectGroups[i].objListe[j]);
					if (colResult.isColliding) {
						// Kollision liegt vor.
						this.cb_solveCollision(theObject,
								this.listObjectGroups[i].objListe[j],
								colResult.dx, colResult.dy);
					}
				}
			}
		}
	}

	// muss überschrieben werden
	this.cb_solveCollision = function(objA, objTargeted, dx, dy) {
		// dx und dy ist der Vektor, wodurch objTargeted so verschoben wird,
		// dass beide sich nicht mehr überschneiden.
		objTargeted.cb_onCollisionTargeted(objA, dx, dy);
		objA.cb_onCollision(objTargeted, dx, dy);
	};

	// public
	this.addNewGroup = function(groupName, f_calcNext, f_draw) {
		// Neue Gruppe wird in Liste eingetragen.
		// f_calcNext: true/false ob eine neue Berechnung dür die Objekte der
		// Gruppe durchgeführt werden soll
		// f_draw: true/false ob die Objekte der Gruppe gezeichnet werden sollen
		var group = new Object();
		group.name = groupName;
		group.doCalcNext = f_calcNext;
		group.doDraw = f_draw;
		group.objListe = new Array();
		this.listObjectGroups.push(group);
		return (this.listObjectGroups.length - 1);// Rückgabe des Index
	}

	// public
	this.deleteGroup = function(groupName) {
		// Löscht eine Gruppe und alle enthaltenen Elemente
		alert("noch nicht implementiert");
		return (false);// Rückgabe des Index
	}

	// public
	this.changeGroupProp = function(groupName, f_calcNext, f_draw) {
		// Ändern der Gruppeneigenschaften.
		// f_calcNext: true/false ob eine neue Berechnung dür die Objekte der
		// Gruppe durchgeführt werden soll
		// f_draw: true/false ob die Objekte der Gruppe gezeichnet werden sollen
		var theGroup; // undefined
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			if (this.listObjectGroups[i].name == groupName)
				theGroup = this.listObjectGroups[i];
		}

		if (typeof theGroup !== 'undefined') {
			theGroup.doCalcNext = f_calcNext;
			theGroup.doDraw = f_draw;
			return (true);// erfolgreich
		}
		return (false);
	}

	// public
	this.addObjectToGroup = function(theObject, groupName) {
		// https://developer.mozilla.org/de/docs/Web/JavaScript/Reference/Global_Objects/Array/find
		var theGroup; // undefined
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			if (this.listObjectGroups[i].name == groupName)
				theGroup = this.listObjectGroups[i];
		}

		if (typeof theGroup !== 'undefined') {
			theGroup.objListe.push(theObject);
			return (true);// erfolgreich
		}
		return (false);
	}

	// public
	this.getObjektsOfGroup = function(groupName) {
		// gibt die Liste der Objekte für eine bestimmte Gruppe zurück
		// wenn Gruppe gefunden, wird das Array zurückgegeben
		// wenn nicht 'undefined'
		var theGroup; // undefined
		var theObjektList; // undefined
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			if (this.listObjectGroups[i].name == groupName)
				theGroup = this.listObjectGroups[i];
		}

		if (typeof theGroup !== 'undefined') {
			theObjektList = theGroup.objListe;
		}
		return (theObjektList);
	}

	// public
	this.getObjekt = function(objektName) {
		// gibt das letzte Objekt mit einem bestimmten Namen zurück
		// wenn Objekt gefunden, dann wird das Objekt zurückgegeben
		// wenn nicht 'undefined'
		var theObjekt; // undefined
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			for (var j = 0; j < this.listObjectGroups[i].objListe.length; j++) {
				if (this.listObjectGroups[i].objListe[j].name == objektName)
					theObjekt = this.listObjectGroups[i].objListe[j];
			}
		}
		return (theObjekt);
	}

	// public
	this.getObjekt_PointInObjekt = function(xPos, yPos) {
		// gibt das letzte Objekt mit einem bestimmten Namen zurück
		// wenn Objekt gefunden, dann wird das Objekt zurückgegeben
		// wenn nicht 'undefined'
		var theObjekt; // undefined
		for (var i = 0; i < this.listObjectGroups.length; i++) {
			for (var j = 0; j < this.listObjectGroups[i].objListe.length; j++) {
				if (this.listObjectGroups[i].objListe[j].pointInObjekt(xPos,
						yPos))
					theObjekt = this.listObjectGroups[i].objListe[j];
			}
		}
		return (theObjekt);
	}

	/** ************************************************************************ */
	/** ***************** Events *********************************************** */
	/** ************************************************************************ */
	// !!!!!!!!!!!!!!!!!!!! Closure für Callback:
	// http://molily.de/js/organisation-verfuegbarkeit.html
	// !!!!!!!!!!!!!!!!!!! guter Artikel
	this.getMousePos = function(evt) {
		// get mouse position in canvas-coordinates
		var rect = this.canvas.getBoundingClientRect();

		// return relative mouse position
		return {
			x : evt.clientX - rect.left,
			y : evt.clientY - rect.top
		};
	}

	this.objList_handleEvent = function(evt) {
		// Event wird an die Objektliste weitergegeben
		// true, wenn ein Objekt das Handling übernommen hat
		// false, wenn es kein Objekt gab, das das Handling übernommen hat.
		var mousePos = theSimulation.getMousePos(evt);// Todo globale Variable
		var selObject; // betroffenes Objekt
		// entfernen
		selObject = this.getObjekt_PointInObjekt(mousePos.x, mousePos.y);
		if ((typeof (selObject) != "undefined")
				&& (selObject.colEntity != "undefined")
				&& selObject.colEntity.isClickable) {

			switch (evt.type) {
			case 'mousedown':
				selObject.cb_mouseDown(evt);
				break;
			case 'mouseup':
				selObject.cb_mouseUp(evt);
				break;
			case 'mousemove':
				selObject.cb_mouseMove(evt);
				break;
			}
			return (true);
		}
		return (false);
	}

	// muss in Childklasse überschrieben werden
	this.cb_mouseMove = function(evt) {
		var mousePos = theSimulation.getMousePos(evt); // Todo globale Variable
		// entfernen
		alert("test Move x:" + mousePos.x + " y: " + mousePos.y);
	}

	// muss in Childklasse überschrieben werden
	this.cb_mouseDown = function(evt) {
		var mousePos = theSimulation.getMousePos(evt);// Todo globale Variable
		this.objList_handleEvent(evt);
	}

	// muss in Childklasse überschrieben werden
	this.cb_mouseUp = function(evt) {
		var mousePos = theSimulation.getMousePos(evt);// Todo globale Variable
		// entfernen
		alert("test Up x:" + mousePos.x + " y: " + mousePos.y);
	}

	// muss in Childklasse überschrieben werden
	this.cb_keyDown = function(evt) {
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
	}

	// public
	this.setEvent_mouseMove = function(on) {
		if (on) {
			this.canvas.addEventListener('mousemove', this.cb_mouseMove
					.bind(this), false);
		} else {
			this.canvas.removeEventListener('mousemove', this.cb_mouseMove
					.bind(this));
		}
	}

	// public
	this.setEvent_mouseDown = function(on) {
		if (on) {
			this.canvas.addEventListener('mousedown', this.cb_mouseDown
					.bind(this), false);
		} else {
			this.canvas.removeEventListener('mousedown', this.cb_mouseDown
					.bind(this));
		}
	}

	// public
	this.setEvent_mouseUp = function(on) {
		if (on) {
			this.canvas.addEventListener('mouseup', this.cb_mouseUp.bind(this),
					false);
		} else {
			this.canvas.removeEventListener('mouseup', this.cb_mouseUp
					.bind(this));
		}
	}

	// public
	this.setEvent_keyDown = function(on) {
		if (on) {
			window
					.addEventListener('keydown', this.cb_keyDown.bind(this),
							true);
		} else {
			window.removeEventListener('keydown', this.cb_keyDown.bind(this));
		}
	}

	// ------------------------------------------------------------------------
	// Initialisierung
	this.canvas = document.getElementById(theCanvasID); // CanvasElement
	if (this.canvas.getContext) {
		// HTML5 Canvas wird unterst�tzt
		this.ctx = this.canvas.getContext("2d");
	} else {
		// HTML5 Canvas wird vom Browser nicht unterstützt
		alert("Ihr Browser unterstützt leider keine HTML5-Canvaselemente!");
	}

}

/** ***************************************************** */
/** ************** Objekte **************************** */
/** ***************************************************** */

/** *** statische Objekte ******************** */

// public - Konstruktor
function StaticObjekt(theSimulation, objName) {
	// Deklaration der Objektattribute
	this.simInstance = theSimulation;
	this.xPos = 0;
	this.yPos = 0;
	this.rotation = 0 // Rotation in Degree in Uhrzeitrichtung
	this.xScale = 1;
	this.yScale = 1;
	this.name = objName; // Name für das Objekt
	this.colEntity;// collisionEntity

	// public********* Setzen des Namen
	this.setName = function(theName) {
		this.name = theName;
	}

	// public********* Setzen der Position in x- und y-Richtung
	this.setPos = function(x, y) {
		this.xPos = x;
		this.yPos = y;
	}

	// public********* Translation in x- und y-Richtung
	this.translate = function(dx, dy) {
		this.xPos = this.xPos + dx;
		this.yPos = this.yPos + dy;
	}

	// public********* zufällige Positionierung innerhalb eines Rechtecks (z.B.
	// ein Canvas-Ausschnitt)
	this.setPosRandom = function(x, y, width, height) {
		this.xPos = x + Math.random() * width;
		this.yPos = y + Math.random() * height;
	}

	// public********* Setzen der Rotation
	this.setRotation = function(theRotation) {
		// in Degree
		this.rotation = theRotation;
	}

	// public
	this.setRotationRandom = function(minDeg, maxDeg) {
		// Winkelbereich [-180,180]
		this.rotation = Math.random() * (maxDeg - minDeg) + minDeg;
	}

	// public********* Setzen der Position in x- und y-Richtung
	this.setScale = function(xDirection, yDirection) {
		this.xScale = xDirection;
		this.yScale = yDirection;
	}

	// public********* Setzen der Position in x- und y-Richtung
	this.setScaleRandom = function(xMin, xMax, yMin, yMax) {
		this.xScale = Math.random() * (xMax - xMin) + xMin;
		this.yScale = Math.random() * (yMax - yMin) + yMin;
	}

	this.pointInObjekt = function(x, y) {
		var collided = false;
		if (typeof (this.colEntity) != "undefined")
			collided = this.colEntity.pointInStruct(x, y);
		return (collided);
	};

	this.checkCollisionWithObject = function(theOtherObject) {
		var colResult;
		if ((typeof (this.colEntity) != "undefined") && this.colEntity.isSolid
				&& (typeof (theOtherObject.colEntity) != "undefined")
				&& theOtherObject.colEntity.isSolid)
			colResult = this.colEntity
					.checkCollisionWithCollisionEntity(theOtherObject.colEntity);
		else
			// kein KollisionsEntity
			return ({
				isColliding : false
			});

		return (colResult);
	};

	this.cb_onCollision = function(objTargeted, dx, dy) {
		// wird aufgerufen, wenn es das aktive Objekt ist, welches mit einem
		// anderen kollidiert.

	};

	this.cb_onCollisionTargeted = function(objA, dx, dy) {
		// wird aufgerufen, wenn es von einem anderen kollidiert wird.
		// objA ist das Objekt, welches die Kollsion verursacht
		// dx und dy beschreiben der Vektor, um den this bewegt werden müsste,
		// damit beide nicht mehr kollidieren.
		var tmpX = dx;
		var tmpY = dy;

		this.translate(tmpX, tmpY);

		// Der Vektor wurde vollständig verarbeitet.
		tmpX = 0;
		tmpY = 0;

		return ({
			restX : tmpX,
			restY : tmpY
		});

	};

	/**
	 * ********************** Kollisionsfunktionen
	 * ************************************
	 */
	// muss in Childklasse überschrieben werden
	// ********* Initialisierung der Kollisions-Struktur
	this.initColEntity = function(clickable_f, solid_f) {
		this.colEntity = new CollisionEntity(clickable_f, solid_f);
		this.colEntity.initColBox(0, 0, 10, 10);
	};

	this.syncColEntity = function() {
		if (typeof (this.colEntity) != "undefined")
			this.colEntity.syncColStruct(this.xPos, this.yPos, this.rotation,
					this.xScale, this.yScale);
	};

	// public
	this.pointInColEntity = function(xPos, yPos) {
		return (this.colEntity.pointInStruct(xPos, yPos));
	}

	/**
	 * ********************** Handling MouseEvents
	 * ************************************
	 */

	this.cb_mouseDown = function() {
		alert("DemoDown: " + this.name);
	}

	this.cb_mouseUp = function() {
		alert("DemoUp: " + this.name);
	}

	this.cb_mouseMove = function() {

	}

	this.cb_dragging = function() {

	}

	// muss in Childklasse überschrieben werden
	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {
		// Keine Positions Veränderung
		this.xPos = this.xPos;
		this.yPos = this.yPos;
		this.rotation = this.rotation;
		this.xScale = this.xScale;
		this.yScale = this.yScale;
		this.syncColEntity();
	}

	// muss in Childklasse überschrieben werden
	// ********* Zeichnen des Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		// Transformation
		this.simInstance.ctx.translate(this.xPos, this.yPos);
		this.simInstance.ctx.rotate(this.rotation * Math.PI / 180);
		this.simInstance.ctx.scale(this.xScale, this.yScale);

		// Farben bestimmen
		this.simInstance.ctx.fillStyle = "rgba(255,0,0,1.0)";

		// Objekt zeichnen
		this.simInstance.ctx.fillRect(0, 0, 10, 10);

		this.simInstance.ctx.restore();
	}

}

/** *** dynamischeObjekte ******************** */

// public - Konstruktor
function DynObjekt(theSimulation, objName) {
	// Vererbung
	StaticObjekt.call(this, theSimulation, objName);
	// Deklaration der ergänzenden Objektattribute

	this.xVel = 0; // Geschwindigkeit x-Richtung
	this.yVel = 0; // Geschwindigkeit y-Richtung
	this.xAccel = 0; // Beschleunigung in x-Richtung
	this.yAccel = 0; // Beschleunigung in y-Richtung
	this.rotationVel = 0 // Rotation in Degree in Uhrzeitrichtung
	this.rotationAccel = 0; // Beschleunigung Rotation
	this.xScaleVel = 0;
	this.yScaleVel = 0;
	this.xScaleAccel = 0; // Beschleunigung Skalierung x-Richtung
	this.yScaleAccel = 0;// Beschleunigung Skalierung y-Richtung

	// public ********* Setzen der Geschwindigkeit in x- und y-Richtung
	this.setVelocity = function(x, y) {
		this.xVel = x;
		this.yVel = y;
	}

	// public ********* Setzen der Geschwindigkeit in x- und y-Richtung per
	// Zufall im
	// Intervall
	this.setVelocityRandom = function(xMin, xMax, yMin, yMax) {
		this.xVel = xMin + Math.random() * (xMax - xMin);
		this.yVel = yMin + Math.random() * (yMax - yMin);
	}

	// public ********* Setzen der Beschleunigung in x- und y-Richtung
	this.setAcceleration = function(theXAcceleration, theYAcceleration) {
		this.xAccel = theXAcceleration;
		this.yAccel = theYAcceleration;
	}

	// public ********* Setzen der Beschleunigung in x- und y-Richtung per
	// Zufall im
	// Intervall
	this.setAccelerationRandom = function(xMin, xMax, yMin, yMax) {
		this.xAccel = xMin + Math.random() * (xMax - xMin);
		this.yAccel = yMin + Math.random() * (yMax - yMin);
	}

	// public ********* Setzen der Rotationsgeschwindigkeit
	this.setRotationVelocity = function(rotationVelocity) {
		this.rotationVel = rotationVelocity;
	}

	// public ********* Setzen der Rotationsgeschwindigkeit per Zufall im
	// Intervall
	this.setRotationVelocityRandom = function(minR, maxR) {
		this.rotationVel = minR + Math.random() * (maxR - minR);
	}

	// public ********* Setzen der Rotationsbeschleunigung
	this.setRotationAcceleration = function(rotationAcceleration) {
		this.rotationAccel = rotationAcceleration;
	}

	// public ********* Setzen der Rotationsbeschleunigung per Zufall im
	// Intervall
	this.setRotationAccelerationRandom = function(minR, maxR) {
		this.rotationAccel = minR + Math.random() * (maxR - minR);
	}

	// public ********* Setzen der Skalierungsgeschwindigkeit in x- und
	// y-Richtung
	this.setScaleVelocity = function(xScale, yScale) {
		this.xScaleVel = xScale;
		this.yScaleVel = yScale;
	}

	// public ********* Setzen der Skalierungsgeschwindigkeit in x- und
	// y-Richtung per
	// Zufall im Intervall
	this.setScaleVelocityRandom = function(xMin, xMax, yMin, yMax) {
		this.xScaleVel = xMin + Math.random() * (xMax - xMin);
		this.yScaleVel = yMin + Math.random() * (yMax - yMin);
	}

	// public ********* Setzen der Skalierungsbeschleunigung in x- und
	// y-Richtung
	this.setScaleAcceleration = function(xScaleAcceleration, yScaleAcceleration) {
		this.xScaleAccel = xScaleAcceleration;
		this.yScaleAccel = yScaleAcceleration;
	}

	// public ********* Setzen der Skalierungsbeschleunigung in x- und
	// y-Richtung per
	// Zufall im Intervall
	this.setScaleAccelerationRandom = function(xMin, xMax, yMin, yMax) {
		this.xScaleAccel = xMin + Math.random() * (xMax - xMin);
		this.yScaleAccel = yMin + Math.random() * (yMax - yMin);
	}

	// muss in Childklasse überschrieben werden
	// ********* Berechnung des nächsten Schritts
	this.calcNextStep = function() {

		this.xVel = this.xVel + this.xAccel;
		this.yVel = this.yVel + this.yAccel;
		this.rotationVel = this.rotationVel+this.rotationAccel;
		this.xScaleVel = this.xScaleVel + this.xScaleAccel;
		this.yScaleVel = this.yScaleVel + this.yScaleAccel;

		this.xPos = this.xPos + this.xVel;
		this.yPos = this.yPos + this.yVel;

		this.rotation = this.rotation + this.rotationVel;
		this.xScale = this.xScale + this.xScaleVel;
		this.yScale = this.yScale + this.yScaleVel;

		this.syncColEntity();
	}

	// muss in Childklasse überschrieben werden
	// ********* Zeichnen des Objekts
	this.draw = function() {
		this.simInstance.ctx.save();
		// Transformation

		this.simInstance.ctx.translate(this.xPos, this.yPos);
		this.simInstance.ctx.rotate(this.rotation * Math.PI / 180);
		this.simInstance.ctx.scale(this.xScale, this.yScale);

		// Farben bestimmen
		this.simInstance.ctx.fillStyle = "rgba(255,0,0,1.0)";

		// Objekt zeichnen
		this.simInstance.ctx.fillRect(0, 0, 10, 10);

		this.simInstance.ctx.restore();
	}

}

/** ******************************************************************************************* */
/**
 * *********** Objekt CollisionEntity - Außerhalb des canhog.js nicht sichtbar
 * ***************
 */
/** ******************************************************************************************* */
/**
 * @returns
 */
/**
 * @returns
 */
function CollisionEntity(clickable_f, solid_f) {
	// clickable_f, wenn Events wie MouseClick etc. an das Objekt weitergeben
	// werden sollen.
	// solid_f, wenn das Objekt für die Kollisionserkennung berücksichtigt
	// werden soll.

	this.colStruct;
	this.type;
	this.isClickable = clickable_f;
	this.isSolid = solid_f

	// public
	this.initColBox = function(x, y, theWidth, theHeight) {
		// https://jriecken.github.io/sat-js/
		var thePos = new SAT.Vector(x, y);
		this.colStruct = new SAT.Box(thePos, theWidth, theHeight).toPolygon();
		this.type = 'POLY';
	};

	// public
	this.initColCircle = function(x, y, radius) {
		// https://jriecken.github.io/sat-js/
		this.colStruct = new SAT.Circle(new SAT.Vector(x, y), radius);
		this.type = 'CIRCLE';
	};

	// public
	this.initColPolygon = function(x, y, pointList) {
		// https://jriecken.github.io/sat-js/

		var pointList2SAT = new Array();
		for (var i = 0; i < pointList.length; i++) {
			pointList2SAT.push(new SAT.Vector(pointList[i].x, pointList[i].y));
		}

		this.colStruct = new SAT.Polygon(new SAT.Vector(x, y), pointList2SAT);
		this.type = 'POLY';
	};

	// public
	this.syncColStruct = function(x, y, theRotation, theXScale, theYScale) {
		// https://jriecken.github.io/sat-js/
		switch (this.type) {
		case 'POLY':
			this.colStruct.pos = new SAT.Vector(x, y);
			this.colStruct.setAngle(theRotation * Math.PI / 180);
			break;
		case 'CIRCLE':
			this.colStruct.pos = new SAT.Vector(x, y);
			break;
		}
	};

	this.pointInStruct = function(xPos, yPos) {
		var collided = false;
		if (this.type == 'POLY') {
			collided = SAT.pointInPolygon(new SAT.Vector(xPos, yPos),
					this.colStruct);
		}

		if (this.type == 'CIRCLE') {
			collided = SAT.pointInCircle(new SAT.Vector(xPos, yPos),
					this.colStruct);
		}
		return (collided);
	}

	/**
	 * Überprüft Kollision
	 * 
	 * @checkCollisionWith
	 * @param {object}
	 *            theObject - das andere Objekt
	 * @return true/false und den Vektor
	 */
	this.checkCollisionWithCollisionEntity = function(theOtherEntity) {
		// Testet
		var collided = false;
		var response = new SAT.Response();
		if ((this.type == 'POLY') && (theOtherEntity.type == 'POLY')) {
			collided = SAT.testPolygonPolygon(this.colStruct,
					theOtherEntity.colStruct, response);
		}

		if ((this.type == 'CIRCLE') && (theOtherEntity.type == 'CIRCLE')) {
			collided = SAT.testCircleCircle(this.colStruct,
					theOtherEntity.colStruct, response);
		}

		if ((this.type == 'CIRCLE') && (theOtherEntity.type == 'POLY')) {
			collided = SAT.testCirclePolygon(this.colStruct,
					theOtherEntity.colStruct, response);
		}

		if ((this.type == 'POLY') && (theOtherEntity.type == 'CIRCLE')) {
			collided = SAT.testPolygonCircle(this.colStruct,
					theOtherEntity.colStruct, response);
		}

		return ({
			isColliding : collided,
			dx : response.overlapV.x,
			dy : response.overlapV.y
		});
	}

}

/*******************************************************************************
 * für die Zukunft **** function DragHandler(theSim) { this.sim = theSim;
 * this.startPos_x = -1; this.startPos_y = -1; this.listDraggingObjekts =
 * 'undefined';
 * 
 * this.startDragging = function(evt, xPos_can, yPos_can) { // Startpostion in
 * Canvas-Koordinaten if (this.listDraggingObjekts != 'undefined') // Wenn
 * DragHandler vorher nicht sauber beendet (z.B. MouseUp // außerhalb des
 * Canvas) delete (this.listDraggingObjekts); this.startPos_x = xPos_can;
 * this.startPos_y = yPos_can; this.listDraggingObjekts =
 * theSim.getObjectsToPoint(this.startPos_x, this.startPos_y); }
 * 
 * this.stopDragging = function(evt) {
 * 
 * delete (this.listDraggingObjekts); } }
 ******************************************************************************/
