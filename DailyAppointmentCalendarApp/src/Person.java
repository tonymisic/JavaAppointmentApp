import java.util.Comparator;

/*
 * Name: Tony Misic     Student#: 500759917 
 */


public class Person implements Comparable<Person>
{
	
	private String lastName;
	private String firstName;
	private String telephone;
	private String address;
	private String email;
	
	public Person(String lastName, String firstName, String tel, String email, String address) 
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.telephone = tel;
		this.email = email;
		this.address = address;
	}
	
	public Person(){}
	
	public String getLastName()
	{
		String temp;
		temp = this.lastName;
		return temp;
	}	
	
	public String getFirstName()
	{
		String temp;
		temp = this.firstName;
		return temp;
	}
	
	public String getEmail()
	{
		String temp;
		temp = this.email;
		return temp;
	}
	
	public String getTelephone()
	{
		String temp;
		temp = this.telephone;
		return temp;
	}
	
	public String getAddress()
	{
		String temp;
		temp = this.address;
		return temp;
	}
	
	public void setlastName(String s)
	{
		this.lastName = s;
	}
	
	public void setfirstName(String s)
	{
		this.firstName = s;
	}
	
	public void setEmail(String s)
	{
		this.email = s;
	}
	
	public void setAddress(String s)
	{
		this.address = s;
	}
	
	public void setTelephone(String s)
	{
		this.telephone = s;
	}
	
	public boolean isNotValid()
	{
		boolean flag = false;
		if (this.firstName.equals("") || this.lastName.equals("") || this.address.equals("") || this.telephone.equals("") || this.email.equals(""))
			flag = true;
		return flag;
	}
	
	class CompareEmails implements Comparator<Person>
	{
		public int compare(Person person0, Person person1) 
		{
			if (person0.email.equals(person1.email))
				return 0;
			return 1;
		}
		public int compareTel(Person person0, Person person1) 
		{
			if (person0.telephone.equals(person1.telephone))
				return 0;
			return 1;
		}
	}
	
	class CompareTelephone implements Comparator<Person>
	{
		public int compare(Person person0, Person person1)
		{
			if (person0.telephone.equals(person1.telephone))
				return 0;
			return 1;
		}
	}
	
	public static String toString(Person person)
	{
		String result = " WITH: "+person.lastName+" "+person.firstName+" "+person.telephone+" "+person.email+" "+person.address;
		return result;
	}
	
	public int compareTo(Person other) 
	{
		if (!this.lastName.equals(other.lastName))
		{
			if (this.lastName.compareTo(other.lastName) < 0) 
				return 1;
			
			if (this.lastName.compareTo(other.lastName) > 0) 
				return -1;
		}
		
		if (this.firstName.compareTo(other.firstName) < 0) 
			return 1;
		
		if (this.firstName.compareTo(other.firstName) > 0) 
			return -1;
		else
			return 0;
	}
}
