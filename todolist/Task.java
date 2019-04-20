package todolist;

public class Task 
{
	private String name;
	private String desc;
	private String date;
	private int status;
	private int priority;
	
	Task()
	{
		name = "";
		desc = "";
		date = "";
		status = 0;
		priority = 0;
	}
	
	Task(String nameI, String descI, int statusI, int priorityI, String dateI)
	{
		name = nameI;
		desc = descI;
		status = statusI;
		priority = priorityI;
		date = dateI;
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
		return date;
	}
	
	void setDate(String sDate)
	{
		date = sDate;
	}

	String printString()
	{
		return "Name: " + name + "\nDescription: " + desc + "\nStatus: " + status + "\nPriority: " + priority + "\nDate: " + date + "\n";
	}
}
