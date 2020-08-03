package Bank;

public class Person implements IPerson{
	private String name;
	private Date birthDate;

	public Person(String aName, Date aDateOfBirth){
		this.name = aName;
		this.birthDate = aDateOfBirth;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String newName) {
		this.name = newName;
		
	}

	@Override
	public Date getDateOfBirth() {
		return birthDate;
	}

	@Override
	public void setDateOfBirth(Date newDateOfBirth) {
		this.birthDate = newDateOfBirth;
		
	}
	

}
