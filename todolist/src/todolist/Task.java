package todolist;

public class Task 
{
	String name;
	String desc;
	int status;
	int priority;
	String date;
	
	Task()
	{
		name = "";
		desc = "";
		status = 0;
		priority = 0;
		date = "";
	}
	
	Task(String nameI, String descI, int statusI, int priorityI, String dateI)
	{
		name = nameI;
		desc = descI;
		status = statusI;
		priority = priorityI;
		date = dateI;
	}
	

}
