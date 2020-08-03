package BirthdayCalendar;

public class birtdayTEST {

	public static void main(String[] args) {
		BirthdayCalendar myCalendar = new BirthdayCalendar();
		
		BirthdayCalender_Date myDate = new BirthdayCalender_Date(23,3,1984);
		Person myPerson = new Person("Kathi Schwab", myDate);
		
		BirthdayCalender_Date date1 = new BirthdayCalender_Date (2,8,1973);
		Person person1 = new Person ("Sam Zander", date1);
		
		BirthdayCalender_Date date2 = new BirthdayCalender_Date(6,7,2004);
		Person person2 = new Person ("Basti Schwab", date2);
		
		BirthdayCalender_Date date3 = new BirthdayCalender_Date(12,6,2005);
		Person person3 = new Person("Tabea Schwab", date3);
		
		BirthdayCalender_Date date4 = new BirthdayCalender_Date(24,8,2013);
		Person person4 = new Person("Raphael Schwab", date4);
		
		myCalendar.addPerson(myPerson);
		myCalendar.addPerson(person1);
		myCalendar.addPerson(person2);
		myCalendar.addPerson(person3);
		myCalendar.addPerson(person4);
		System.out.println(myCalendar);
		
		myCalendar.removePerson(person4);
		System.out.println(myCalendar);
		
		myCalendar.addPerson(person4);
		System.out.println(myCalendar.persons());
		
		System.out.println(myCalendar.celebrators(myDate)); 
		
		BirthdayCalender_Date testdate= new BirthdayCalender_Date(27,2,1966);
		System.out.println(myCalendar.celebrators(testdate)); 
		
		BirthdayCalender_Date testdate3 = new BirthdayCalender_Date(1,5,1985);
		Person testperson = new Person("Manuel Mustermann", testdate3);
//		myCalendar.addPerson(testperson);
		
		BirthdayCalender_Date testdate2= new BirthdayCalender_Date(27,4,2016);
		Person test;
		test = myCalendar.nextBirthday(testdate2);
		System.out.println(test);
		}

}
