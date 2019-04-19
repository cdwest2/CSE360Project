package todolist;
import java.util.*;
public class TaskList 
{
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	void add(Task task)
	{
		tasks.add(task);
	}
	
	void remove(int index)
	{
		tasks.remove(index);
	}
	
	int size()
	{
		return tasks.size();
	}
	
	Task get(int index)
	{
		return tasks.get(index);
	}
	
	void sortPriority()
	{
		Collections.sort(tasks, new Comparator<Task>()
				{			
					//sort by priority in ascending order
					public int compare(Task t1, Task t2)
					{
						return Integer.valueOf(t1.getPriority()).compareTo(t2.getPriority());
					}
				
				});
	}
	
	void sortName()
	{
		Collections.sort(tasks, new Comparator<Task>()
				{			
					//sort by name in ascending order
					public int compare(Task t1, Task t2)
					{
						return t1.getName().compareTo(t2.getName());
					}
				
				});
	}
	
	void sortDate()
	{
		Collections.sort(tasks, new Comparator<Task>()
				{			
					//sort by date in ascending order
					public int compare(Task t1, Task t2)
					{
						return t1.getDate().compareTo(t2.getDate());
					}
				
				});
	}
	
}
