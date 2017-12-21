import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/*
 * Name: Tony Misic
 */

/*
 * class Appointment used to create all the Appointment objects
 * includes all methods to access the Appointment variables
 */

public class Appointment implements Comparable<Appointment>
{
	
	//instance variables
	public Calendar calendar;
	
	private SimpleDateFormat sdf;
	
	private String description;
	
	private Person person;
	
	// appointment constructor method
	public Appointment(String desc, int year, int month, int day, int hour, int minute, Person person)
	{
		
		sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		calendar = new GregorianCalendar(year,month,day,hour,minute);
		description = desc;
		this.person = person;
	}
	
	//returns description of appointment
	public String getDescription()
	{
		return description;
	}
	
	//sets new description
	public void setDescription(String s)
	{
		description = s;
	}
	
	//prints out all appointment info
	public String printAppointment()
	{
		sdf = new SimpleDateFormat("HH:mm");
		if (person.equals(null))
		return sdf.format(calendar.getTime()) + " " + getDescription();
		else
			return sdf.format(calendar.getTime()) + " " + getDescription() + Person.toString(person);
	}

	//returns time of day
	public String getTimeOfDay()
	{
		sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(calendar.getTime());
	}
	
	//sets the time of day
	public void setTime(int hr, int min)
	{
		calendar.set(Calendar.HOUR_OF_DAY, hr);
		calendar.set(Calendar.MINUTE, min);
	}
	
	//sets new date
	public void setDate(int yr, int mnth, int dy)
	{
		calendar.set(Calendar.YEAR, yr);
		calendar.set(Calendar.MONTH, mnth);
		calendar.set(Calendar.DAY_OF_MONTH, dy);
	}
	
	//returns date and time
	public Date getTime()
	{
		sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		return calendar.getTime();
	}

	//checks if dates entered are conflicting with any others
	public boolean occursOn(Appointment other)
	{
		boolean check = false;
		if (this.getTime().equals(other.getTime()))
		{
			check = true;
		}
		return check;
	}
	
	//compareTo override method
	public int compareTo(Appointment other)
	{
		if (this.getTime().before(other.getTime())) return -1;
		if (this.getTime().after(other.getTime())) return 1;
		return 0;
	}
	
}
