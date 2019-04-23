package todolist;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task 
{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
	private String name;
	private String desc;
	private Date date;
	private int status;
	private int priority;
	
	Task()
	{
		name = "";
		desc = "";
		setDate("");
		status = 0;
		priority = 0;
	}
	
	Task(String nameI, String descI, int statusI, int priorityI, String dateI)
	{
		name = nameI;
		desc = descI;
		status = statusI;
		priority = priorityI;
		setDate(dateI);
	}
	
	String getName()
	{
		return name;
	}
	
	void setName(String sName)
	{
		name = sName;
	}
	
	String getDesc()
	{
		return desc;
	}
	
	void setDesc(String sDesc)
	{
		desc = sDesc;
	}
	
	int getStatus()
	{
		return status;
	}
	
	void setStatus(int sStatus)
	{
		status = sStatus;
	}
	
	int getPriority()
	{
		return priority;
	}
	
	void setPriority(int sPriority)
	{
		priority = sPriority;
	}
	
	String getDate()
	{
		try {
		return sdf.format(date);
		} catch (NullPointerException e) {
			
		}
		return "";
	}
	String printDate()
	{
		String month = "";
		String text = "";
		try {
		String rawText = sdf.format(date);
		String textArr[] = rawText.split(" ");

		if(textArr[1].equals("1") || textArr[1].equals("01"))
		{
			month = "January";
		}
		if(textArr[1].equals("2") || textArr[1].equals("02"))
		{
			month = "February";
		}
		if(textArr[1].equals("3") || textArr[1].equals("03"))
		{
			month = "March";
		}
		if(textArr[1].equals("4") || textArr[1].equals("04"))
		{
			month = "April";
		}
		if(textArr[1].equals("5") || textArr[1].equals("05"))
		{
			month = "May";
		}
		if(textArr[1].equals("6") || textArr[1].equals("06"))
		{
			month = "June";
		}
		if(textArr[1].equals("7") || textArr[1].equals("07"))
		{
			month = "July";
		}
		if(textArr[1].equals("8") || textArr[1].equals("08"))
		{
			month = "August";
		}
		if(textArr[1].equals("9") || textArr[1].equals("09"))
		{
			month = "September";
		}
		if(textArr[1].equals(Integer.toString(10)))
		{
			month = "October";
		}
		if(textArr[1].equals(Integer.toString(11)))
		{
			month = "November";
		}
		if(textArr[1].equals(Integer.toString(12)))
		{
			month = "December";
		}
		text = month + " " + textArr[2] + ", " + textArr[0];
		} catch (NullPointerException e) {}
		return text;
	}
	
	void setDate(String sDate)
	{
		try {
			date = sdf.parse(sDate);
		} catch (ParseException e) {
		}
	}

	String printString()
	{
		String output = "";
		String statusStr = "";
		if(status == 0)
		{
			statusStr = "Not started";
		}
		else if(status == 1)
		{
			statusStr = "In progress";
		}
		else
		{
			statusStr = "Complete";
		}
		output ="Name: " + name + ". Description: " + desc + ". Status: " + statusStr + ". Priority: " + Integer.toString(priority) + ". Date: " + date + ".\n\n";
		return output;
	}
}
