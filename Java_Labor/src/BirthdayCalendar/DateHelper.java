package BirthdayCalendar;

public class DateHelper {

	// Exercise 2a
	public static boolean isLeapYear(int year) {
		boolean leapYear = false;

		if (year % 4 == 0) {
			leapYear = true;
		}

		if (leapYear == true) {
			if (year % 100 == 0) {
				leapYear = false;
			}
		}

		if (leapYear == false) {
			if (year % 400 == 0) {
				leapYear = true;
			}
		}
		return leapYear;
	}

	// exercise 2b
	public static int numberOfDays(int month, int year) {
		int daysOfMonth;
		if (year < 1583 ) {
			daysOfMonth = 0;
			return daysOfMonth;
		}

		if (month < 1 || month > 12) {
			daysOfMonth = 0;
			return daysOfMonth;
		}

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			daysOfMonth = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			daysOfMonth = 30;
			break;
		case 2:
			boolean leapYear = isLeapYear(year);
			if (leapYear == true) {
				daysOfMonth = 29;
			} else {
				daysOfMonth = 28;
			}
			break;
		default:
			daysOfMonth = 0;
		}
		return daysOfMonth;
	}

	public static void main(String[] args) {
		// loop for exercise 2a
		for (int i = 1900; i <= 2000; i++) {
			boolean leapYear = isLeapYear(i);

			if (leapYear == true) {
				System.out.println(i + " ist ein Schaltjahr.");
			} else {
				System.out.println(i + " ist kein Schaltjahr.");
			}

		}

		// loop for exercise 2b
		for (int year = 2015; year <= 2016; year++) {
			for (int month = 1; month <= 12; month++) {
				int day = numberOfDays(month, year);
				System.out.println(month + "." + year + " hat " + day + " Tage.");
			}
		}

	}
}
