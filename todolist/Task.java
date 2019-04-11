package todolist;

public class Task 
{
	private String name;
	private String desc;
	private int status;
	private int priority;
	private int date;
	
	Task()
	{
		name = "";
		desc = "";
		status = 0;
		priority = 0;
		date = 0;
	}
	
	Task(String nameI, String descI, int statusI, int priorityI, int dateI)
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
	
	Int getStatus()
	{
		return status;
	}
	
	void setStatus(Int sStatus)
	{
		status = sStatus;
	}
	
	Int getPriority()
	{
		return priority;
	}
	
	void setPriority(Int sPriority)
	{
		priority = sPriority;
	}
	
	Int getDate()
	{
		return date;
	}
	
	void setDate(Int sDate)
	{
		date = sDate;
	}

}
