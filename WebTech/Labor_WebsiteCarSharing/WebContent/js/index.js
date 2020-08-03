"use strict";

function initialize() {
	$("#one").slideUp();
}

function startButton() {
	$("#one").slideDown();
	$("#one").get(0).play();
}

function stopButton() {
	$("#one").slideUp();
	$("#one").get(0).pause();
}
