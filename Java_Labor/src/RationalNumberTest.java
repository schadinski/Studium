
public class RationalNumberTest {

	public static void main(String[] args) {
	
		System.out.println("Test default constructor");
		RationalNumber test1 = new RationalNumber();
		System.out.println("Soll: 0/1 Ist: "+test1);
		
		System.out.println("Test constructor");
		RationalNumber test2 = new RationalNumber(3,1);
		System.out.println("Soll: 3 Ist: "+test2);
		RationalNumber test3 = new RationalNumber(4,5);
		System.out.println("Soll: 4/5 Ist: "+test3);
		RationalNumber test4 = new RationalNumber(3,6);
		System.out.println("Soll: 1/2 Ist: "+test4);

		System.out.println("Test doubleValue");
		RationalNumber test5 = new RationalNumber(2,3);
		System.out.println("Soll: 0,66period Ist: "+test5.doubleValue());
		RationalNumber test6 = new RationalNumber(4,8);
		System.out.println("Soll: 0,5 Ist: "+test6.doubleValue());
		RationalNumber test7 = new RationalNumber(9,10);
		System.out.println("Soll: 0,9 Ist: "+test7.doubleValue());
		
		System.out.println("Test floatValue");
		RationalNumber test8 = new RationalNumber(8,11);
		System.out.println("Soll: 0,7272period Ist: "+test8.floatValue());
		RationalNumber test9 = new RationalNumber(8,25);
		System.out.println("Soll: 0,32 Ist: "+test9.floatValue());
		RationalNumber test10 = new RationalNumber(5,4);
		System.out.println("Soll: 1,25 Ist: "+test10.floatValue());
		
		System.out.println("Test intValue");
		System.out.println("Soll: 1 Ist: "+test10.intValue());
		RationalNumber test11 = new RationalNumber(26,5);
		System.out.println("Soll: 5 Ist: "+test11.intValue());
		RationalNumber test12 = new RationalNumber(17,9);
		System.out.println("Soll: 2 Ist: "+test12.intValue());
		
	}

}
