package todolist;
import java.util.*;
//Will manage the list of tasks
public class TaskList 
{
	private ArrayList<Task> tasks;
	
	void insert(Task task)
	{
		tasks.add(task);
	}
	
}
