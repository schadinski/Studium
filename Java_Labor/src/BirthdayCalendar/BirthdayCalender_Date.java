package BirthdayCalendar;
/**
 * 
 * @author Katharina Schwab
 *
 */
public class BirthdayCalender_Date {

	// Attribute,Felder
	public int day;
	public int month;
	public int year;
	public Day weekday;
	static int numberOfDateObjects = 0;
	

	/**
	 * constructor for date object January, first, 1970.
	 */
	BirthdayCalender_Date() {
		day = 1;
		month = 1;
		year = 1970;
		numberOfDateObjects++;
	}

	/**
	 * constructor for date object with values from parameters.
	 * 
	 * @param int
	 *            myDay,myMonth,myYear
	 */
	BirthdayCalender_Date(int myDay, int myMonth, int myYear) {
		boolean validValues = areValidValues(myDay, myMonth, myYear);
		if (!validValues) {
			this.day = 1;
			this.month = 1;
			this.year = 1970;
			numberOfDateObjects = numberOfDateObjects+1;
			return;
		}
		int daysOfMonth = numberOfDays(myMonth, myYear);
		if (daysOfMonth >= myDay) {
			this.day = myDay;
			this.month = myMonth;
			this.year = myYear;
		}
		else{
			this.day = 1;
			this.month = 1;
			this.year = 1970;
		}
		numberOfDateObjects++;
	}

	/**
	 * constructor to copy a date.
	 * 
	 * @param otherDate
	 */
	BirthdayCalender_Date(BirthdayCalender_Date otherDate) {
		this.day = otherDate.day;
		this.month = otherDate.month;
		this.year = otherDate.year;
		numberOfDateObjects++;
	}
	
	protected void finalize(){
		numberOfDateObjects--;
	}

	/**
	 * method to set a date on parameters if they are a valid date
	 * 
	 * @param newDay
	 * @param newMonth
	 * @param newYear
	 * @return true or false
	 */
	boolean setDate(int newDay, int newMonth, int newYear) {
		boolean validValues = areValidValues(newDay, newMonth, newYear);
		if (!validValues) {
			return false;
		}
		int daysOfMonth = numberOfDays(newMonth, newYear);
		if (daysOfMonth >= newDay) {
			this.day = newDay;
			this.month = newMonth;
			this.year = newYear;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * method to check the parameters are a valid date
	 * 
	 * @param anyDay
	 * @param anyMonth
	 * @param anyYear
	 * @return true or false
	 */
	boolean isValidDate(int anyDay, int anyMonth, int anyYear) {
		boolean validValues = areValidValues(anyDay, anyMonth, anyYear);
		if (!validValues) {
			return false;
		}

		int daysOfMonth = numberOfDays(anyMonth, anyYear);
		if (daysOfMonth >= anyDay) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * method to check given values are correct
	 * 
	 * @param aDay
	 * @param aMonth
	 * @param aYear
	 * @return true or false
	 */
	boolean areValidValues(int aDay, int aMonth, int aYear) {
		if (aDay < 0 || aDay > 31 || aMonth < 0 || aMonth > 12 || aYear < 1583) {
			return false;
		} else {
			return true;
		}
	}

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
		if (year < 1) {
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

	// @overwrite
	public String toString() {
		String Date = new String();

		if (day < 10 && month > 10) {
			Date = "0" + day + "." + month + "." + year;
		} else if (day > 10 && month < 10) {
			Date = day + "." + "0" + month + "." + year;
		} else if (day < 10 && month < 10) {
			Date = "0" + day + "." + "0" + month + "." + year;
		}
		else{
			Date = day + "." + month + "." + year;
		}
		return Date;
	}

	/**
	 * method to find is is the first date before the date from parameter
	 * 
	 * @param otherDate
	 * @return true or false
	 */
	boolean isBefore(BirthdayCalender_Date otherDate) {
		if (otherDate.year > this.year) {
			return true;
		} else if (otherDate.year >= this.year && otherDate.month > this.month) {
			return true;
		} else if (otherDate.year >= this.year && otherDate.month >= this.month && otherDate.day > this.day) {
			return true;
		} else {
			return false;
		}
	}

	public BirthdayCalender_Date nextDate() {
		BirthdayCalender_Date newDate = new BirthdayCalender_Date(this);

		int daysOfMonth = numberOfDays(this.month, this.year);
		if (this.day < daysOfMonth) {
			newDate.day++;
		} else {
			if (this.month < 12) {
				newDate.month++;
				newDate.day = 1;
			} else {
				newDate.year++;
				newDate.month = 1;
				newDate.day = 1;
			}

		}
		numberOfDateObjects++;
		return newDate;
	}
	
	
	public Day getWeekday(){
		int digitOfDay = this.day;
		
		int digitOfMonth = this.month;
		int digitOfYear = this.year;
		if(this.month<=2){
			digitOfMonth = this.month+12;
			digitOfYear = this.year -1;
		}
		
		int temp1 = ((3*digitOfMonth+3)/5);
		int temp2= digitOfYear/4;
		int temp3 = digitOfYear/100;
		int temp4 = digitOfYear/400;
		
		int cipherOfDay = (digitOfDay + 2*digitOfMonth +temp1 +digitOfYear +temp2 - temp3+ temp4 +1)%7;
	
		switch(cipherOfDay){
		case 0:
			this.weekday = Day.SUNDAY;
			break;
		case 1:
			this.weekday = Day.MONDAY;
			break;
		case 2:
			this.weekday = Day.TUESDAY;
			break;
		case 3:
			this.weekday = Day.WEDNESDAY;
			break;
		case 4:
			this.weekday = Day.THURSDAY;
			break;
		case 5:
			this.weekday = Day.FRIDAY;
			break;
		default: 
			this.weekday = Day.SATURDAY;
			break;
		}
		
		return this.weekday;
	
	}
	public int getNumberOfDateObjects(){
		return numberOfDateObjects;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(this== o){
			return true;
		}
		if(! (o instanceof BirthdayCalender_Date)){
			return false;
		}
		
		BirthdayCalender_Date other = (BirthdayCalender_Date) o;
		
		return 		this.day == other.day
				&&	this.month == other.month;
				
	
}
}
