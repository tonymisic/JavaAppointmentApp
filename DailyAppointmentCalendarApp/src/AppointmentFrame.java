import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.util.Calendar;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*
 * Name: Tony Misic     Student#: 500759917 
 */

/*
 *Making the frame for the Appointment class
 */

public class AppointmentFrame extends JFrame 
{
	
	//size instance variables
	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 900;
	
	private static final int AREA_ROWS = 17;
	private static final int AREA_COLUNMS = 35;
	
	private static final int BUTTON_W = 200;
	private static final int BUTTON_H = 40;
	
	private static final int PANEL_W = 330;
	private static final int PANEL_H = 275;
	
	private static final int DESC_R = 5;
	private static final int DESC_C = 25;
	
	private static final int FIELD_SIZE = 18;
	
	private static final int CALENDAR_BUTTON_WIDTH = 5;
	private static final int CALENDAR_BUTTON_HEIGHT = 12;
	
	private static final int CALENDAR_SIZE = 42;
	
	//JFrame and other instance variables
	
	private JFrame frame;
	private JPanel titlePanel;
	
	private JPanel panel;
	private JLabel titleLabel;
	private JLabel titleMonth;
	private JPanel temp;
	
	private JPanel CONTROL_PANEL;
	
	private JPanel westPanel;
	private JPanel eastPanel;
	
	private JPanel dateSubPanel;
	private JPanel actionSubPanel;
	private JPanel descriptionSubPanel;
	private JPanel contactSubPanel;
	private JPanel southEastPanel;
	private JPanel monthButtonPanel;
	
	private JPanel buttonPanel;
	private JPanel subControlPanel;
	private JPanel showButtonPanel;
	private JPanel contactButtonPanel;
	private JPanel calendarSubPanel;
	
	private JTextField lastNameField;
	private JTextField firstNameField;
	private JTextField telField;
	private JTextField emailField;
	private JTextField addressField;
	
	private JTextField dayText;
	private JTextField monthText;
	private JTextField yearText;
	private JTextField hourText;
	private JTextField minuteText;
	private JLabel day;
	private JLabel month;
	private JLabel year;
	private JLabel hourLabel;
	private JLabel minuteLabel;
	
	private JLabel calendarTitle;
	
	private Stack<Appointment> stack = new Stack<Appointment>();
	
	private LinkedList<Person> list;
	private Contacts contacts;
	
	private JTextArea descTextArea;
	
	private JButton backButton;
	private JButton forwardButton;
	private JButton showButton;
	private JButton createButton;
	private JButton cancelButton;
	private JButton recallButton;
	private JScrollPane scrollPane;
	private JButton findButton;
	private JButton clearButton;
	private JButton currentButton;
	
	private ArrayList<Appointment> appList;
	
	private JTextArea textArea;
	
	private Calendar calendar = new GregorianCalendar(2017, 3, 12, 13, 0);
	
	private SimpleDateFormat sdf;
	
	private Appointment appointment;
	
	private int previousMonth = calendar.get(Calendar.MONTH);
	private JButton[] buttons = new JButton[42];
	

	//constructor method
	
	public AppointmentFrame() 
	{
		//creating arraylist and sdf
		sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
		appList = new ArrayList<Appointment>();
		contacts = new Contacts();
		list = contacts.readContactsFile();
		//creating components
		createFrame();
		createWestPanel();
		createEastPanel(); 
		createTitleText();
		createTextArea();
		createControlPanel();
		createContactPanel();
		createDescSubPanel();
		createCalendarPanel();
		createSouthEastPanel();
		
		//sets size of application
		frame.setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	
	}
	
	//action listener for the ">" button
	class ForwardListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
	    {
			sdf = new SimpleDateFormat("EEE MMM dd yyyy");
			calendar.add(Calendar.DATE, 1);
			titleLabel.setText(sdf.format(calendar.getTime()));
			sdf = new SimpleDateFormat("MMM");
			calendarTitle.setText("" + sdf.format(calendar.getTime()));
			createCalendarPanel();
			showAppointments();
	    }
	}
	
	//action listener for the "<" button
	class BackListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			sdf = new SimpleDateFormat("EEE MMM dd yyyy");
			calendar.add(Calendar.DATE, -1);
			titleLabel.setText("" + sdf.format(calendar.getTime()));
			sdf = new SimpleDateFormat("MMM");
			calendarTitle.setText("" + sdf.format(calendar.getTime()));
			createCalendarPanel();
			showAppointments();
		}
	}
	
	//action listener for the create button
	class CreateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
			sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
			int minute = 00;
			if(!minuteText.getText().equals(""))
			{
				if (Integer.parseInt(minuteText.getText()) < 0 || Integer.parseInt(minuteText.getText()) > 59)
				{
					JOptionPane.showMessageDialog(frame, "Minute is not Valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
					minuteText.setText(null);
					throw new Exception();
				}
				minute = Integer.parseInt(minuteText.getText());
			}
			
			String desc = descTextArea.getText();
			String hourTxt = hourText.getText();
			
			int hour = Integer.parseInt(hourTxt);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if (Integer.parseInt(hourText.getText()) < 0 || Integer.parseInt(hourText.getText()) > 23)
			{
				JOptionPane.showMessageDialog(frame, "Hour is not Valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
				hourText.setText(null);
				throw new Exception();
			}
			int month = calendar.get(Calendar.MONTH);
			int year = calendar.get(Calendar.YEAR);
			
			Person tempPerson;
			
			if (firstNameField == null && lastNameField == null && telField == null && emailField == null && addressField == null )
				tempPerson = new Person();
			else 
				tempPerson = new Person(lastNameField.getText(), lastNameField.getText(), telField.getText(), emailField.getText(), addressField.getText());
			
			Appointment tempApp = new Appointment(desc, year, month, day, hour, minute, tempPerson);
			
			boolean find = false;
			for (int i = 0; i < appList.size(); i++)
			{
				if (tempApp.occursOn(appList.get(i)))
				{
					find = true;
					descTextArea.setText(null);
					hourText.setText(null);
					minuteText.setText(null);
					descTextArea.setText("CONFLICT!!!");
					showAppointments();
				} 
			}
			if (!find)
			{
				appList.add(tempApp);
				stack.push(tempApp);
				descTextArea.setText(null);
				hourText.setText(null);
				minuteText.setText(null);
				showAppointments();
			}
			
			} catch (Exception e3)
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Creation is not Valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//action listener for the cancel button
	class CancelListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
			String hourTxt = hourText.getText();
			String minuteTxt = minuteText.getText();
			String desc = descTextArea.getText();
			
			int hour = Integer.parseInt(hourTxt);
			int minute = Integer.parseInt(minuteTxt);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH);
			int year = calendar.get(Calendar.YEAR);
			
			Person person = new Person();
			
			Appointment tempApp = new Appointment(desc, year, month, day, hour, minute, person);
			
			for (int i = 0; i < appList.size(); i++)
			{
				if (tempApp.occursOn(appList.get(i)))
				{
					appList.remove(i);
					stack.pop();
					showAppointments();
					hourText.setText(null);
					minuteText.setText(null);
					descTextArea.setText(null);
				}
				hourText.setText(null);
				minuteText.setText(null);
			}
			showAppointments();
			} catch (Exception e3)
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Cancellation is not Valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//action listener for the show button
	class ShowListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
			String dayTxt = dayText.getText();
			String monthTxt = monthText.getText();
			String yearTxt = yearText.getText();
			int day = Integer.parseInt(dayTxt);
			int month = Integer.parseInt(monthTxt) - 1;
			int year = Integer.parseInt(yearTxt);
			
			if (month < 1 || month > 12 || day < 1 || day > calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			{
				JFrame frame = new JFrame();
				dayText.setText(null);
				monthText.setText(null);
				yearText.setText(null);
				JOptionPane.showMessageDialog(frame, "Month is not valid!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DAY_OF_MONTH, day);
			}
			
			sdf = new SimpleDateFormat("EEE MMM dd yyyy");
			titleLabel.setText(sdf.format(calendar.getTime()));
			sdf = new SimpleDateFormat("MMM");
			calendarTitle.setText("" + sdf.format(calendar.getTime()));
			
			dayText.setText(null);
			monthText.setText(null);
			yearText.setText(null);
			showAppointments();
			createCalendarPanel();
			} catch (Exception e2)
			{
				System.out.println("Month or Day is not Valid!");
				dayText.setText(null);
				monthText.setText(null);
				yearText.setText(null);
			}
		}
	}
	
	//action listener for find button
	class FindListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try {
			Person inputPerson = new Person(lastNameField.getText(), firstNameField.getText(), telField.getText(), emailField.getText(), addressField.getText());
			Person outputPerson = new Person();
			for (Person person : list)
			{
				if (inputPerson.getLastName().equals(person.getLastName()))
					outputPerson = person;
				
				if (inputPerson.getFirstName().equals(person.getFirstName()))
					outputPerson = person;
				
				if (inputPerson.getAddress().equals(person.getAddress()))
					outputPerson = person;
				
				if (inputPerson.getTelephone().equals(person.getTelephone()))
					outputPerson = person;
				
				if (inputPerson.getEmail().equals(person.getEmail()))
					outputPerson = person;	
			}
			
			if (outputPerson.isNotValid())
			{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Contact Not Found!", "CAUTION", JOptionPane.WARNING_MESSAGE);
				lastNameField.setText(null);
				firstNameField.setText(null);
				telField.setText(null);
				addressField.setText(null);
				emailField.setText(null);
			}
				
			firstNameField.setText(outputPerson.getFirstName());
			lastNameField.setText(outputPerson.getLastName());
			emailField.setText(outputPerson.getEmail());
			telField.setText(outputPerson.getTelephone());
			addressField.setText(outputPerson.getAddress());
			} catch (Exception exception)
			{
				JOptionPane.showMessageDialog(frame, "Contact Not Found!", "CAUTION", JOptionPane.WARNING_MESSAGE);
				lastNameField.setText(null);
				firstNameField.setText(null);
				telField.setText(null);
				addressField.setText(null);
				emailField.setText(null);
			}
		}
	}
	
	//action listener for clear button
	class ClearListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			lastNameField.setText(null);
			firstNameField.setText(null);
			telField.setText(null);
			addressField.setText(null);
			emailField.setText(null);
		}
	}
	
	//action listener for recall button
	class RecallListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			try {
			Appointment temp = stack.peek();
			sdf = new SimpleDateFormat("HH");
			hourText.setText(sdf.format(temp.getTime()));
			sdf = new SimpleDateFormat("mm");
			minuteText.setText(sdf.format(temp.getTime()));
			descTextArea.setText(temp.getDescription());
			sdf = new SimpleDateFormat("yyyy");
			calendar.set(Calendar.YEAR, Integer.parseInt(sdf.format(temp.getTime())));
			sdf = new SimpleDateFormat("MM");
			calendar.set(Calendar.MONTH, Integer.parseInt(sdf.format(temp.getTime())) - 1);
			sdf = new SimpleDateFormat("dd");
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sdf.format(temp.getTime())));
			sdf = new SimpleDateFormat("EEE MMM dd yyyy");
			titleLabel.setText("" + sdf.format(calendar.getTime()));
			sdf = new SimpleDateFormat("MMM");
			calendarTitle.setText("" + sdf.format(calendar.getTime()));
			createCalendarPanel();
			showAppointments();
			} catch (EmptyStackException e1)
			{
				System.out.print("No Appointments to recall!\n");
			}
		}	
	}
	
	//action listener for current Button
	class CurrentListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Calendar tempCal = Calendar.getInstance();
			sdf = new SimpleDateFormat("yyyy");
			calendar.set(Calendar.YEAR, Integer.parseInt(sdf.format(tempCal.getTime())));
			sdf = new SimpleDateFormat("MM");
			calendar.set(Calendar.MONTH, Integer.parseInt(sdf.format(tempCal.getTime())) - 1);
			sdf = new SimpleDateFormat("dd");
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sdf.format(tempCal.getTime())));
			sdf = new SimpleDateFormat("EEE MMM dd yyyy");
			titleLabel.setText("" + sdf.format(calendar.getTime()));
			showAppointments();
			createCalendarPanel();
		}
	}
	
	//action listener for calendar buttons
	class DayListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();
			if (source instanceof JButton) 
			{
			    JButton button = (JButton) source;
			    calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(button.getText()));
			    sdf = new SimpleDateFormat("EEE MMM dd yyyy");
				titleLabel.setText("" + sdf.format(calendar.getTime()));
			    button.setBackground(Color.RED);
			    createCalendarPanel();
			    showAppointments();
			}
	
		}
	}
	
	//makes the frame
	public void createFrame()
	{
		//initialization
		frame = new JFrame();
	}
	
	//makes left panel
	public void createWestPanel()
	{
		westPanel = new JPanel();
		westPanel.setLayout(new BorderLayout());
		this.add(westPanel, BorderLayout.WEST);
	}
	
	//makes right panel
	public void createEastPanel()
	{
		eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout());
		this.add(eastPanel, BorderLayout.CENTER);
	}
	
	//creates the subtitle which holds the current date
	public void createTitleText()
	{
		//initialization
		temp = new JPanel();
		temp.setLayout(new BorderLayout());
		
		titleLabel = new JLabel();
		
		//title date showing currrent date
		sdf = new SimpleDateFormat("EEE MMM dd yyyy");
		titleLabel.setText("" + sdf.format(calendar.getTime()));
		
		temp.add(titleLabel, BorderLayout.WEST);
		westPanel.add(temp, BorderLayout.NORTH);
	}
	
	//creates text area for the appointments to be shown
	public void createTextArea()
	{
		//initialization
		panel = new JPanel();
		textArea = new JTextArea(AREA_ROWS, AREA_COLUNMS);
		scrollPane = new JScrollPane(textArea);
		
		//typing in the text area toggle
		textArea.setEditable(false);
		
		//prints appointments
		showAppointments();
		
		//keeps the scroll bars visible
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//add components
		panel.add(scrollPane);
		westPanel.add(panel, BorderLayout.CENTER);
	}
	
	//creating the control Panel
	public void createControlPanel()
	{
		CONTROL_PANEL = new JPanel();
		CONTROL_PANEL.setLayout(new BorderLayout());
		
		//creating components of control panel
		createDateSubPanel();
		createActionSubPanel();
		
		CONTROL_PANEL.add(dateSubPanel, BorderLayout.NORTH);
		CONTROL_PANEL.add(actionSubPanel, BorderLayout.SOUTH);
		
		westPanel.add(CONTROL_PANEL, BorderLayout.SOUTH);
	}
	
	//date sub panel
	public void createDateSubPanel()
	{
		dateSubPanel = new JPanel();
		dateSubPanel.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		subControlPanel = new JPanel();
		showButtonPanel = new JPanel();
		
		showButton = new JButton("Show");
		dayText = new JTextField(2);
		day = new JLabel("Day");
		monthText = new JTextField(2);
		month = new JLabel("Month");
		yearText = new JTextField(4);
		year = new JLabel("Year");
		
		dateSubPanel.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
		buttonPanel.setLayout(new GridLayout(1, 2));
		
		backButton = new JButton("<");
		forwardButton = new JButton(">");
		currentButton = new JButton("Current Date");
		
		ForwardListener forwardListener = new ForwardListener();
		forwardButton.addActionListener(forwardListener);
		
		BackListener backListener = new BackListener();
		backButton.addActionListener(backListener);
		
		ShowListener showListener = new ShowListener();
		showButton.addActionListener(showListener);
		
		CurrentListener currentListener = new CurrentListener();
		currentButton.addActionListener(currentListener);
		
		buttonPanel.add(backButton);
		buttonPanel.add(forwardButton);
		
		showButtonPanel.add(showButton);
		showButtonPanel.add(currentButton);
		
		subControlPanel.add(day);
		subControlPanel.add(dayText);
		subControlPanel.add(month);
		subControlPanel.add(monthText);
		subControlPanel.add(year);
		subControlPanel.add(yearText);
		
		Border border = null;
		Border padding = BorderFactory.createEmptyBorder(70, 0, 20, 10);
		subControlPanel.setBorder(BorderFactory.createCompoundBorder(border, padding));
		
		buttonPanel.setPreferredSize(new Dimension( BUTTON_W, BUTTON_H ));
		dateSubPanel.add(buttonPanel, BorderLayout.NORTH);
		dateSubPanel.add(subControlPanel, BorderLayout.CENTER);
		dateSubPanel.add(showButtonPanel,BorderLayout.SOUTH);
		dateSubPanel.setPreferredSize(new Dimension( PANEL_W, PANEL_H ));
	}
	
	//action sub panel
	public void createActionSubPanel()
	{
		actionSubPanel = new JPanel();
		actionSubPanel.setLayout(new BorderLayout());
		actionSubPanel.setBorder(new TitledBorder(new EtchedBorder(), "Appointment"));
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		
		hourText = new JTextField(5);
		minuteText = new JTextField(5);
		
		hourLabel = new JLabel("Hour");
		minuteLabel = new JLabel("Minute");
		
		createButton = new JButton("CREATE");
		cancelButton = new JButton("CANCEL");
		recallButton = new JButton("RECALL");
		
		Border border = null;
		Border padding = BorderFactory.createEmptyBorder(20, 0, 20, 10);
		
		upperPanel.setBorder(BorderFactory.createCompoundBorder(border, padding));
		lowerPanel.setBorder(BorderFactory.createCompoundBorder(border, padding));
		
		CreateListener createListener = new CreateListener();
		createButton.addActionListener(createListener);
		
		CancelListener cancelListener = new CancelListener();
		cancelButton.addActionListener(cancelListener);
		
		RecallListener recallListener = new RecallListener();
		recallButton.addActionListener(recallListener);
		
		upperPanel.add(hourLabel);
		upperPanel.add(hourText);
		upperPanel.add(minuteLabel);
		upperPanel.add(minuteText);
		
		lowerPanel.add(createButton);
		lowerPanel.add(cancelButton);
		lowerPanel.add(recallButton);
		
		actionSubPanel.add(upperPanel, BorderLayout.NORTH);
		actionSubPanel.add(lowerPanel, BorderLayout.CENTER);
		actionSubPanel.setPreferredSize(new Dimension( PANEL_W, PANEL_H ));
	}
	
	//description sub panel
	public void createDescSubPanel()
	{
		descriptionSubPanel = new JPanel();
		descriptionSubPanel.setBorder(new TitledBorder(new EtchedBorder(), "Description"));
		
		descTextArea = new JTextArea(DESC_R, DESC_C);
		descTextArea.setLineWrap(true);
		descriptionSubPanel.add(descTextArea);
		descriptionSubPanel.setPreferredSize(new Dimension( PANEL_W, PANEL_H ));
		
	}
	
	//contact info sub panel
	public void createContactPanel()
	{
		JPanel subpanel3 = new JPanel(); 
		subpanel3.setLayout(new BorderLayout());
		
		JPanel subpanel1 = new JPanel(); 
		subpanel1.setLayout(new GridLayout(1, 2));
		
		JPanel subpanel2 = new JPanel(); 
		subpanel2.setLayout(new GridLayout(1, 2));
		
		JPanel subpanel4 = new JPanel(); 
		subpanel4.setLayout(new BorderLayout());
		
		JPanel subpanel5 = new JPanel(); 
		subpanel5.setLayout(new BorderLayout());
		
		JPanel subpanel6 = new JPanel(); 
		subpanel6.setLayout(new BorderLayout());
		
		JPanel subpanel7 = new JPanel();
		subpanel7.setLayout(new BorderLayout());
		
		contactButtonPanel = new JPanel();
		
		Border border = null;
		Border padding = BorderFactory.createEmptyBorder(10, 0, 10, 10);
		
		final JLabel lastName = new JLabel("Last Name                                             ");
		subpanel3.add(lastName, BorderLayout.WEST);
		final JLabel firstName = new JLabel("First Name");
		subpanel3.add(firstName, BorderLayout.CENTER);
		
		final JLabel tel = new JLabel("Telephone                                              ");
		subpanel6.add(tel, BorderLayout.WEST);
		final JLabel email = new JLabel("e-Mail");
		subpanel6.add(email, BorderLayout.CENTER);
		
		final JLabel address = new JLabel("Address");
		subpanel7.add(address, BorderLayout.WEST);
		
		lastName.setBorder(BorderFactory.createCompoundBorder(border, padding));
		firstName.setBorder(BorderFactory.createCompoundBorder(border, padding));
		tel.setBorder(BorderFactory.createCompoundBorder(border, padding));
		email.setBorder(BorderFactory.createCompoundBorder(border, padding));
		address.setBorder(BorderFactory.createCompoundBorder(border, padding));
		
		findButton = new JButton("FIND");
		clearButton = new JButton("CLEAR");
		
		ClearListener clearListener = new ClearListener();
		clearButton.addActionListener(clearListener);
		
		FindListener findListener = new FindListener();
		findButton.addActionListener(findListener);
		
		contactSubPanel = new JPanel();
		contactSubPanel.setLayout(new BorderLayout());
		contactSubPanel.setBorder(new TitledBorder(new EtchedBorder(), "Contact"));
		
		lastNameField = new JTextField(FIELD_SIZE); 
		lastNameField.setPreferredSize(new Dimension(FIELD_SIZE, 35));
		
		firstNameField = new JTextField(FIELD_SIZE);
		firstNameField.setPreferredSize(new Dimension(FIELD_SIZE, 35));
		
		telField = new JTextField(FIELD_SIZE); 
		telField.setPreferredSize(new Dimension(FIELD_SIZE, 35));
		
		emailField = new JTextField(FIELD_SIZE); 
		emailField.setPreferredSize(new Dimension(FIELD_SIZE, 35));
		
		addressField = new JTextField(FIELD_SIZE * 2);
		addressField.setPreferredSize(new Dimension(FIELD_SIZE * 2, 35));
		
		contactButtonPanel.add(findButton);
		contactButtonPanel.add(clearButton);
		
		subpanel1.add(lastNameField);
		subpanel1.add(firstNameField);
		subpanel2.add(telField);
		subpanel2.add(emailField);
		
		subpanel4.add(subpanel6, BorderLayout.NORTH);
		subpanel4.add(subpanel2, BorderLayout.CENTER);
		subpanel4.add(subpanel7, BorderLayout.SOUTH);
		
		subpanel5.add(subpanel1, BorderLayout.NORTH);
		subpanel5.add(subpanel4, BorderLayout.CENTER);
		subpanel5.add(addressField, BorderLayout.SOUTH);
		
		
		contactSubPanel.add(subpanel3, BorderLayout.NORTH);
		contactSubPanel.add(subpanel5, BorderLayout.CENTER);
		contactSubPanel.add(contactButtonPanel, BorderLayout.SOUTH);
		
		contactSubPanel.setPreferredSize(new Dimension( PANEL_W, PANEL_H ));
	}
	
	//calendar panel created
	public void createCalendarPanel() 
	{
		sdf = new SimpleDateFormat("MMM");
		
		calendarSubPanel = new JPanel();
		calendarSubPanel.setLayout(new BorderLayout());
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		
		monthButtonPanel = new JPanel();
		monthButtonPanel.setLayout(new GridLayout(6, 7));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		JPanel subTitle = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		int count = 1;
		Calendar beginCal = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0,0);
		int firstDay = beginCal.get(Calendar.DAY_OF_WEEK);
		int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < CALENDAR_SIZE; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setVisible(false);
			DayListener listener = new DayListener();
			buttons[i].addActionListener(listener);
			
			monthButtonPanel.add(buttons[i]);
		}
		
		for (int j = firstDay - 1; j < end + firstDay - 1; j++)
		{
			buttons[j].setVisible(true);
			buttons[j].setText(Integer.toString(count));
			sdf = new SimpleDateFormat("dd");
			
			if (Integer.valueOf((buttons[j].getText())).equals(Integer.valueOf(((sdf.format(calendar.getTime()))))))
				buttons[j].setBackground(Color.RED);
			
			count++;
		}
		
		sdf = new SimpleDateFormat("MMM");
		
		String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		
		Border border = null;
		Border padding = BorderFactory.createEmptyBorder(5, 0, 1, 32);
		
		JLabel day1 = new JLabel(days[0]);
		day1.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day2 = new JLabel(days[1]);
		day2.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day3 = new JLabel(days[2]);
		day3.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day4 = new JLabel(days[3]);
		day4.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day5 = new JLabel(days[4]);
		day5.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day6 = new JLabel(days[5]);
		day6.setBorder(BorderFactory.createCompoundBorder(border, padding));
		JLabel day7 = new JLabel(days[6]);
		day7.setBorder(BorderFactory.createCompoundBorder(border, padding));
		
		subTitle.add(day1);
		subTitle.add(day2);
		subTitle.add(day3);
		subTitle.add(day4);
		subTitle.add(day5);
		subTitle.add(day6);
		subTitle.add(day7);
		
		calendarTitle = new JLabel();
		calendarTitle.setText("" + sdf.format(calendar.getTime()));
		
		titlePanel.add(calendarTitle, BorderLayout.WEST);
		
		centerPanel.add(subTitle, BorderLayout.NORTH);
		centerPanel.add(monthButtonPanel, BorderLayout.CENTER);
		
		calendarSubPanel.add(titlePanel, BorderLayout.NORTH);
		calendarSubPanel.add(centerPanel, BorderLayout.CENTER);
	
		eastPanel.add(calendarSubPanel, BorderLayout.NORTH);
	}
	
	//creates bottom right panel
	public void createSouthEastPanel()
	{
		createContactPanel();
		createDescSubPanel();
		
		southEastPanel = new JPanel();
		southEastPanel.setLayout(new BorderLayout());
		southEastPanel.add(descriptionSubPanel, BorderLayout.SOUTH);
		southEastPanel.add(contactSubPanel, BorderLayout.NORTH);
		eastPanel.add(southEastPanel, BorderLayout.SOUTH);
	}
	
	//checks if appointments match current date
	public boolean findAppointment(Appointment app) 
	{
		for (int i = 0; i < appList.size(); i++)
		{
			if (app.getTime() == appList.get(i).getTime())
			{
				if (app.occursOn(appList.get(i)))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	//method for creating appointments
	public void createAppointment(String desc, int yr, int mnth, int day, int hr, int min, Person person)
	{
		appointment = new Appointment(desc, yr, mnth, day, hr, min, person);
		appList.add(appointment);
	}
	
	//prints appointments to the textarea
	public void showAppointments()
	{
		Collections.sort(appList);
		sdf = new SimpleDateFormat("yyyy MMM dd");
		String temp;
		textArea.setText("");
		for (int i = 0; i < appList.size(); i++)
		{
			if (sdf.format(appList.get(i).getTime()).equals(sdf.format(calendar.getTime())))
			{
				temp = appList.get(i).printAppointment();
				textArea.append(temp + "\n\n");
				
			}
		}
	}
	
	//updates the calendar
	public void updateCalendar()
	{
		int count = 1;
		Calendar beginCal = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0,0);
		int firstDay = beginCal.get(Calendar.DAY_OF_WEEK);
		int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < CALENDAR_SIZE; i++)
		{
			buttons[i].setVisible(false);
		}
		
		for (int j = firstDay - 1; j < end + firstDay - 1; j++)
		{
			boolean flag = false;
			buttons[j].setText(Integer.toString(count));
			for( ActionListener listener : buttons[j].getActionListeners()) 
			{
			        buttons[j].removeActionListener(listener);
			}
			DayListener listener = new DayListener();
			buttons[j].addActionListener(listener);
			count++;
		}
		
	}
}







