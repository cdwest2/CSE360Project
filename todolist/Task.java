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
	

}
