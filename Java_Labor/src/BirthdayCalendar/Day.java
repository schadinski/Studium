package BirthdayCalendar;

public enum Day {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; 

	public String getGermanName(){
		String germanName;
		
		switch(this){
			case SUNDAY:
				germanName ="Sonntag";
				break;
			case MONDAY:
				germanName ="Montag";
				break;
			case TUESDAY:
				germanName ="Dienstag";
				break;
			case WEDNESDAY:
				germanName ="Mittwoch";
				break;
			case THURSDAY:
				germanName ="Donnerstag";
				break;
			case FRIDAY:
				germanName ="Freitag";
				break;
			case SATURDAY:
				germanName ="Samstag";
				break;
			default:
				germanName ="This day may not exist.";
		}
		return germanName;
	}
}
