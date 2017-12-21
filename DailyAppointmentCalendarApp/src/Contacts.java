import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Name: Tony Misic     Student#: 500759917 
 */


public class Contacts 
{
	
	private LinkedList<Person> list;
	private ListIterator<Person> iter;
	private Person person;
	private Scanner scanner;
	
	public Contacts()
	{
		list = new LinkedList<Person>();
		list = readContactsFile();
	}
	
	public LinkedList<Person> readContactsFile()
	{
		int neededContactCount = 0;
		int contactCount = 0;
		try {
			list = new LinkedList<Person>();
			File file = new File("contacts.txt");
			scanner = new Scanner(file);
			if (!scanner.hasNextInt())
				throw new NumberFormatException("File does not begin with an integer!");
			neededContactCount = Integer.parseInt(scanner.nextLine());
			
			while (scanner.hasNextLine())
			{
				Person p = new Person(null,null,null,null,null);
				
				p.setlastName(scanner.nextLine());  
				p.setfirstName(scanner.nextLine()); 
				p.setAddress(scanner.nextLine());   
				p.setTelephone(scanner.nextLine()); 
				p.setEmail(scanner.nextLine());  
				contactCount++;
				
				if (p.isNotValid())
				{
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "All of the information is not filled in!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				list.add(p);
			}
			if (neededContactCount != contactCount)
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Incorrect amount of contacts!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e)
		{
			String message = "File not found! File may also be incorrect!`";
			System.out.print(message);
		}
		return list;
		
	}
	
	public Person findPersonLastName(String lastname)
	{	
		try 
		{
			Person person;
			iter = list.listIterator();
				
			while (iter.hasNext())
			{
				person = iter.next();
					
				if (person.getLastName().equals(lastname))
					return person;
				else
					iter.next();
			}
		} 
		catch (Exception e) 
		{
			throw new NoSuchElementException();
		}
		return null;
	}
		
	public Person findPersonFirstName(String firstname)
	{
		try 
		{
			Person person;
			iter = list.listIterator();
				
			while (iter.hasNext())
			{
				person = iter.next();
					
				if (person.getFirstName().equals(firstname))
					return person;
				else
					iter.next();
			}
		} 
		catch (Exception e) 
		{
			throw new NoSuchElementException();
		}
		return null;
	}
		
	public Person findPersonEmail(String email)
	{
		try 
		{
			Person person;
			iter = list.listIterator();
				
			while (iter.hasNext())
			{
				person = iter.next();
					
				if (person.getEmail().equals(email))
					return person;
				else
					iter.next();
			}
		} 
		catch (Exception e) 
		{
			throw new NoSuchElementException();
		}
		return null;
	}
		
	public Person findPersonTel(String tel)
	{
		try 
		{
			Person person;
			iter = list.listIterator();
				
			while (iter.hasNext())
			{
				person = iter.next();
					
				if (person.getTelephone().equals(tel))
					return person;
				else
					iter.next();
			}
		} 
		catch (Exception e) 
		{
			throw new NoSuchElementException();
		}
		return null;
	}
		
	public Person findPersonAddress(String address)
	{
		try 
		{
			Person person;
			iter = list.listIterator();
				
			while (iter.hasNext())
			{
				person = iter.next();
					
				if (person.getAddress().equals(address))
					return person;
				else
					iter.next();
			}
		} 
		catch (Exception e) 
		{
			throw new NoSuchElementException();
		}
		return null;
	}
	
}
