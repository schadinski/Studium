package BirthdayCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class BirthdayCalendar {

	List<Person> birthdayCalendar;
	int numberOfPersons = 0;
	
	
	public BirthdayCalendar() {
		birthdayCalendar = new ArrayList<Person>();
	}
	
	public boolean addPerson(Person p){
		boolean result = birthdayCalendar.add(p);
		if(result == false){
		//TODO ?? muss hier noch was gemacht werden ?	
		}
		numberOfPersons++;
		return result;
	}
	
	public boolean removePerson(Person p){
		boolean result = true;
		if(birthdayCalendar.isEmpty()){
			return false;
		}
		result = birthdayCalendar.remove(p);
		
		//TODO muss result noch gechecked werden?
		numberOfPersons--;
		return result;
	}
	
	public Collection<Person> persons(){
		Collections.sort(birthdayCalendar);
		return birthdayCalendar; 
	}
	
	public Collection<Person> celebrators(BirthdayCalender_Date date){
		List<Person> celebrators = new ArrayList<Person>();
		for(Person p : birthdayCalendar ){
			if(p.getDateOfBirthday().equals(date)){
				celebrators.add(p);
			}
		}
		return celebrators;
	}
	
	public Person nextBirthday(BirthdayCalender_Date date){
		Collections.sort(birthdayCalendar);
		Person result = null;
		BirthdayCalender_Date tempDate = date;
		
		while(result == null){
			for(Person p : birthdayCalendar ){
				if(p.getDateOfBirthday().equals(tempDate)){
					result = p;
				}
			}
			tempDate = tempDate.nextDate();
		}	
		return result;	
	}

	public String toString(){
		String result = "My birthday calender \n";
		Iterator<Person> it = birthdayCalendar.iterator();
				
		for (int i = 0; i < numberOfPersons; i++) {
			result = result.concat(it.next().toString()+"\n");
		}
		return result;
	}
}
