#include <stdio.h>
#include <Windows.h>

#define MY_MAKRO(a,b) (a)>(b) ? (a) : (b)

//int main(void) {
//
//int a = 3;
//int b = 42;
//
//printf("Der groessere Wert ist %d\n", MY_MAKRO(a,b));
//
//float fa = 3.33;
//float fb = 42.22;
//
//printf("Der groessere Wert ist %lf\n", MY_MAKRO(fa, fb));
//
//double da = 3.99;
//double db = 42.88;
//
//printf("Der groessere Wert ist %lf\n", MY_MAKRO(da, db));
//
//a = -1000;
//unsigned int uib = 1;
//
//printf("Der groessere Wert ist %d", MY_MAKRO(a, uib));
//
////nicht typsicher, erkennt Unterschied zwischen int -1000 und ui 1 nicht
//
//Sleep(600000);
//return 0;
//
///*Übung 5:
//   inline macht Sinn bei sehr kurzen Funktionen, bei längeren nicht.  Ein weiterer Vorteil ist die typsicherheit 
//von Inline Funktionen. Des weiteren werden inlines wie Makros vom Kompiler an jeder auftretenden Stelle 
//ersetzt, aber nur wenn dies sinnvoll ist. somit wird eine bessere performance gewährleistet.
//Es taucht einmal mehr auf als verwendet, weil es im header auch steht.
//*/
//
//}