package BirthdayCalendar;

public class Person implements Comparable<Person>{
	private String name;
	private BirthdayCalender_Date birthDate;

	public Person(String aName, BirthdayCalender_Date aDateOfBirth){
		this.name = aName;
		this.birthDate = aDateOfBirth;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String newName) {
		this.name = newName;
		
	}

	
	public BirthdayCalender_Date getDateOfBirthday() {
		return birthDate;
	}

	
	public void setDateOfBirthday(BirthdayCalender_Date newDateOfBirth) {
		this.birthDate = newDateOfBirth;
		
	}

	@Override
	public int compareTo(Person other) {
		int result = 0;
		if(birthDate.month < other.birthDate.month){
			result = -1;
		}
		else if(birthDate.month > other.birthDate.month){
			result = 1;
		}
		else if (birthDate.month == other.birthDate.month && birthDate.day < other.birthDate.day ){
			result = -1;
		}
		else if( birthDate.month== other.birthDate.month&& birthDate.day > other.birthDate.day){
			result = 1;
		}
		else if( birthDate.month == other.birthDate.month && birthDate.day == other.birthDate.day){
			int temp = name.compareToIgnoreCase(other.name);
			
			if(temp == 0){
				if(birthDate.year == other.birthDate.year){
					result = 0;
				}
				else if(birthDate.year < other.birthDate.year){
					result = 1;
				}
				else if(birthDate.year > other.birthDate.year){
					result = -1;
				}
			}
			else if(temp == -1){
				result = -1;
			}
			else if(temp == 1){
				result = 1;
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(this== o){
			return true;
		}
		if(! (o instanceof Person)){
			return false;
		}
		
		Person other = (Person) o;
		
		return 		name.compareToIgnoreCase(other.name) == 0
				&&  birthDate.equals(other);
	}
	
	@Override
	public String toString(){
		String result = name.toString()+" "+birthDate.toString();
		return result;
	}
	
	
}
