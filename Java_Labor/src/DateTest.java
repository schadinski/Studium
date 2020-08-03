
public class DateTest {

	public static void main(String[] args) {
		
			Date standard = new Date();
			System.out.println("Anzahl der DateObjects: "+standard.getNumberOfDateObjects());
			System.out.println(standard);
			System.out.println("Der "+standard + " ist ein "+standard.getWeekday().getGermanName()+".");
			
			//test Date() constructor
			int myDay = 23;
			int myMonth = 3;
			int myYear = 1984;
			Date myDate = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myDate.getNumberOfDateObjects());
			System.out.println(myDate);
			System.out.println("Der "+myDate+ " ist ein "+myDate.getWeekday().getGermanName()+".");
			
			myDay = 55;
			Date myDate2 = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myDate2.getNumberOfDateObjects());
			System.out.println(myDate2);
			System.out.println("Der "+myDate2 + " ist ein "+myDate2.getWeekday().getGermanName()+".");
			
			myDay = 23;
			myYear = 1200;
			Date myDate3 = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myDate3.getNumberOfDateObjects());
			System.out.println(myDate3);
			System.out.println("Der "+myDate3 + " ist ein "+myDate3.getWeekday().getGermanName()+".");
			
			myMonth = 15;
			myYear = 1984;
			Date myDate4 = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myDate4.getNumberOfDateObjects());
			System.out.println(myDate4);
			System.out.println("Der "+myDate4 + " ist ein "+myDate4.getWeekday().getGermanName()+".");
			
			//test Copy-Constructor
			System.out.println("test for Copy-Constructor");
			Date testDate = new Date(myDate);
			System.out.println("Anzahl der DateObjects: "+myDate.getNumberOfDateObjects());
			System.out.println("Copy is: "+testDate);
			System.out.println("Der "+testDate + " ist ein "+testDate.getWeekday().getGermanName()+".");
			
			Date testDate2 = new Date(standard);
			System.out.println("Anzahl der DateObjects: "+testDate2.getNumberOfDateObjects());
			System.out.println("Copy is: "+testDate2);
			System.out.println("Der "+testDate2 + " ist ein "+testDate2.getWeekday().getGermanName()+".");
						
			//test for setDate
			System.out.println("test for setDate");
			myDay = 24;
			myMonth = 8;
			myYear = 2013;
			Date myNewDate = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myNewDate.getNumberOfDateObjects());
			System.out.println("Der "+myNewDate + " ist ein "+myNewDate.getWeekday().getGermanName()+".");
			myDay = 30;
			myMonth = 3;
			myYear = 2016;
			System.out.println("given date is: "+myNewDate);
			System.out.println("should set to: "+myDay+" "+ myMonth+" "+ myYear);
			myNewDate.setDate(myDay, myMonth, myYear);
			System.out.println("after method: "+myNewDate);
			System.out.println("Der geänderte "+myNewDate + " ist ein "+myNewDate.getWeekday().getGermanName()+".");
			
			myYear = 1125;
			System.out.println("given date is: "+testDate);
			System.out.println("should set to :"+myDay+" "+ myMonth+" "+ myYear);
			testDate.setDate(myDay, myMonth, myYear);
			System.out.println("after method: "+testDate);
			System.out.println("Der "+testDate + " ist ein "+testDate.getWeekday().getGermanName()+".");
			
			myYear = 2016;
			myDay = 33;
			System.out.println("given date is: "+testDate);
			System.out.println("sholud set to :"+myDay+" "+ myMonth+" "+ myYear);
			testDate.setDate(myDay, myMonth, myYear);
			System.out.println("after method: "+testDate);
			System.out.println("Der "+testDate + " ist ein "+testDate.getWeekday().getGermanName()+".");
			
			//test for isValidDate
			System.out.println("test for isValidDate");
			boolean result;
			myDay = 23;
			myMonth = 3;
			myYear = 1984;
			System.out.println("given values are: "+myDay +" "+ myMonth +" "+myYear);
			System.out.println("given date is: "+testDate);
			result = testDate.isValidDate(myDay,myMonth,myYear);
			System.out.println(result);
			
			//test for isBefore
			System.out.println("test for isBefore");
			System.out.println("given dates are: "+testDate+" " +myNewDate);
			result = testDate.isBefore(myNewDate);
			System.out.println("method result: "+result);
			
			System.out.println("given dates are: "+standard+" " +myDate);
			result = standard.isBefore(myDate);
			System.out.println("method result: "+result);
			
			System.out.println("given dates are: "+testDate+" " +standard);
			result = testDate.isBefore(standard);
			System.out.println("method result: "+result);
			
			//test for nextDate
			System.out.println("test for nextDate");
			System.out.println("given date is: "+standard);
			Date nextTest = standard.nextDate();
			System.out.println("Anzahl der DateObjects: "+nextTest.getNumberOfDateObjects());
			System.out.println("after method: "+nextTest);
			System.out.println("Der "+nextTest + " ist ein "+nextTest.getWeekday().getGermanName()+".");
			
			myDay = 31;
			myMonth = 12;
			myYear = 2013;
			Date myNewDate2 = new Date(myDay, myMonth, myYear);
			System.out.println("Anzahl der DateObjects: "+myNewDate2.getNumberOfDateObjects());
			System.out.println("given date is: "+myNewDate2);
			Date nextTest2 = myNewDate2.nextDate();
			System.out.println("Anzahl der DateObjects: "+nextTest2.getNumberOfDateObjects());
			System.out.println("after method: "+nextTest2);
			System.out.println("Der "+nextTest2 + " ist ein "+nextTest2.getWeekday().getGermanName()+".");
			
			//test for getWeekday
//			System.out.println("test for getWeekday");
//			myDay = 4;
//			myMonth = 4;
//			myYear = 2016;
//			Date today = new Date(myDay, myMonth, myYear);
//			System.out.println("given date is: "+today);
//			today.getWeekday();
//			System.out.println("Der "+today + " ist ein "+today.getWeekday().getGermanName()+".");

	}

}
