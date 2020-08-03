

public class ControlStructures {

	// Aufgabe 2
	public static int sum(int a, int b) {
		// Rückgabewert ist ein int.
		// Die Übergabeparameter heißen int a und int b.
		// Beides sind ints, also Ganzzahlen.
		int result = a + b;
		return result;
	}

	// Aufgabe 5
	public static int calcCubicNumber(int a) {
		int cubic = a * a;
		return cubic;
	}

	// Aufgabe 6
	public static double calcMWSt(double d) {
		double MwSt;
		double temp;
		temp = d / 119;
		MwSt = temp * 19;
		return MwSt;
	}

	public static void main(String[] args) {
		// Aufgabe 4
		int a = 5;
		int b = 10;
		int sumResult = sum(a, b);
		System.out.println(sumResult);

		// Aufgabe 7
		int cubicResult = calcCubicNumber(a);
		System.out.println(cubicResult);
		int neg = -6;
		cubicResult = calcCubicNumber(neg);
		System.out.println(cubicResult);

		double d = 119.0;
		double resultMwSt = calcMWSt(d);
		System.out.println(resultMwSt);

		d = -119.0;
		resultMwSt = calcMWSt(d);
		System.out.println(resultMwSt);

	}
}
