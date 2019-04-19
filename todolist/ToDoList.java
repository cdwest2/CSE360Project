package todolist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;

public class ToDoList {
	
	static Task selectedTask = new Task();
	
	public static void main(String[] args) 
	{
		selectedTask.setDate("11 20 1998");
		selectedTask.setDesc("This is my best try");
		selectedTask.setName("Task Attempt");
		selectedTask.setPriority(1);
		
		initializeHome();
	}
	
	static Task addPanel()
	{
		final Task newTask = new Task();
		
		JFrame addFrame = new JFrame("ADD TASK");
		addFrame.setSize(1100, 500);
		
		JPanel wholePan = new JPanel();
		wholePan.setLayout(new BoxLayout(wholePan, BoxLayout.PAGE_AXIS));
		
		JPanel namePanel = new JPanel();
		JLabel name = new JLabel("NAME: ");
		name.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField nameTextField = new JTextField("Task Name");
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		nameTextField.setPreferredSize(new Dimension(790, 50));
		namePanel.add(name);
		namePanel.add(nameTextField);
		
		JPanel descPanel = new JPanel();
		JLabel desc = new JLabel("DESCRIPTION: ");
		desc.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField descTextField = new JTextField("Short Description");
		descTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		descTextField.setPreferredSize(new Dimension(635, 100));
		descPanel.add(desc);
		descPanel.add(descTextField);
		
		JPanel priorityDatePanel = new JPanel();
		JLabel priority = new JLabel("PRIORITY: ");
		priority.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField priorityTextField = new JTextField("#");
		priorityTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		priorityTextField.setPreferredSize(new Dimension(70, 50));
		priorityDatePanel.add(priority);
		priorityDatePanel.add(priorityTextField);
		
		JLabel date = new JLabel("          DATE: ");
		date.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField dateTextField = new JTextField("MM DD YYYY");
		dateTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		dateTextField.setPreferredSize(new Dimension(400, 50));
		priorityDatePanel.add(date);
		priorityDatePanel.add(dateTextField);
		
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.setPreferredSize(new Dimension(900, 60));
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 40));
        addTaskButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
        		
        		String text = dateTextField.getText();
        		newTask.setDate(text);
        		
        		text = nameTextField.getText();
        		newTask.setName(text);
        		
        		text = descTextField.getText();
        		newTask.setDesc(text);
        		
        		text = priorityTextField.getText();
        		newTask.setPriority(Integer.parseInt(text));
        		
        		newTask.setStatus(0);
        	}
        });
		
		wholePan.add(Box.createRigidArea(new Dimension(1, 20)));
		wholePan.add(namePanel);
		wholePan.add(descPanel);
		wholePan.add(priorityDatePanel);
		
		addFrame.add(wholePan);
		addFrame.add(BorderLayout.SOUTH, addTaskButton);
		addFrame.setLocationRelativeTo(null);
		addFrame.setVisible(true);
		return newTask;
	}
	
	static Task editPanel(Task task)
	{
		JFrame editFrame = new JFrame("EDIT TASK");
		editFrame.setSize(1100, 500);
		
		JPanel wholePan = new JPanel();
		wholePan.setLayout(new BoxLayout(wholePan, BoxLayout.PAGE_AXIS));
		
		JPanel namePanel = new JPanel();
		JLabel name = new JLabel("NAME: ");
		name.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField nameTextField = new JTextField(task.getName());
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		nameTextField.setPreferredSize(new Dimension(790, 50));
		namePanel.add(name);
		namePanel.add(nameTextField);
		
		JPanel descPanel = new JPanel();
		JLabel desc = new JLabel("DESCRIPTION: ");
		desc.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField descTextField = new JTextField(task.getDesc());
		descTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		descTextField.setPreferredSize(new Dimension(635, 100));
		descPanel.add(desc);
		descPanel.add(descTextField);
		
		JPanel priorityDatePanel = new JPanel();
		JLabel priority = new JLabel("PRIORITY: ");
		priority.setFont(new Font("Arial", Font.PLAIN, 40));
		String priorityString = Integer.toString(task.getPriority());
		JTextField priorityTextField = new JTextField(priorityString);
		priorityTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		priorityTextField.setPreferredSize(new Dimension(70, 50));
		priorityDatePanel.add(priority);
		priorityDatePanel.add(priorityTextField);
		
		JLabel date = new JLabel("          DATE: ");
		date.setFont(new Font("Arial", Font.PLAIN, 40));
		String dateString = task.getDate();
		JTextField dateTextField = new JTextField(dateString);
		dateTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		dateTextField.setPreferredSize(new Dimension(400, 50));
		priorityDatePanel.add(date);
		priorityDatePanel.add(dateTextField);
		
		JButton editTaskButton = new JButton("Save Task");
		editTaskButton.setPreferredSize(new Dimension(900, 60));
        editTaskButton.setFont(new Font("Arial", Font.PLAIN, 40));
        editTaskButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editFrame.dispatchEvent(new WindowEvent(editFrame, WindowEvent.WINDOW_CLOSING));
        		
        		String text = dateTextField.getText();
        		task.setDate(text);
        		
        		text = nameTextField.getText();
        		task.setName(text);
        		
        		text = descTextField.getText();
        		task.setDesc(text);
        		
        		text = priorityTextField.getText();
        		task.setPriority(Integer.parseInt(text));
        		
        		task.setStatus(0);
        	}
        });
		
		wholePan.add(Box.createRigidArea(new Dimension(1, 20)));
		wholePan.add(namePanel);
		wholePan.add(descPanel);
		wholePan.add(priorityDatePanel);
		
		editFrame.add(wholePan);
		editFrame.add(BorderLayout.SOUTH, editTaskButton);
		editFrame.setLocationRelativeTo(null);
		editFrame.setVisible(true);
		
		return task;
	}
	
	static void initializeHome()
	{
		//Creating the Frame
        JFrame frame = new JFrame("To-Do List Unlimited 2019");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Creating the Top Menu Bar and File Button
        JMenuBar topMenuBar = new JMenuBar();
        
        JMenu fileButton = new JMenu("OPTIONS");
        fileButton.setPreferredSize(new Dimension(190, 60));
        fileButton.setFont(new Font("Arial", Font.PLAIN, 40));
        
        JMenuItem save = new JMenuItem("Save");
        save.setPreferredSize(new Dimension(190, 50));
        save.setFont(new Font("Arial", Font.PLAIN, 30));
        fileButton.add(save);
        
        JMenuItem restore = new JMenuItem("Restore");
        restore.setPreferredSize(new Dimension(190, 50));
        restore.setFont(new Font("Arial", Font.PLAIN, 30));
        fileButton.add(restore);
        
        JMenuItem print = new JMenuItem("Print");
        print.setPreferredSize(new Dimension(190, 50));
        print.setFont(new Font("Arial", Font.PLAIN, 30));
        fileButton.add(print);
        
        //Add File Button to Top Menu Bar
        topMenuBar.add(Box.createHorizontalGlue());
        topMenuBar.add(fileButton);
        
        //Create Bottom Panel and Task Buttons
        JPanel bottomBar = new JPanel();
        FlowLayout fLay = new FlowLayout(FlowLayout.LEADING, 100, 10);
        bottomBar.setLayout(fLay);
        
        JButton add = new JButton("ADD");
        add.setPreferredSize(new Dimension(200, 50));
        add.setFont(new Font("Arial", Font.PLAIN, 30));
        add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedTask = addPanel();
        	}
        });
        
        JButton edit = new JButton("EDIT");
        edit.setPreferredSize(new Dimension(200, 50));
        edit.setFont(new Font("Arial", Font.PLAIN, 30));
        edit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedTask = editPanel(selectedTask);
        	}
        });
        
        JButton delete = new JButton("DELETE");
        delete.setPreferredSize(new Dimension(200, 50));
        delete.setFont(new Font("Arial", Font.PLAIN, 30));
        //////////////////////////////
        JMenu sortingButton = new JMenu("Sorting Method");
        sortingButton.setPreferredSize(new Dimension(300, 60));
        sortingButton.setFont(new Font("Arial", Font.PLAIN, 40));
        
        JMenuItem Name = new JMenuItem("Name");
        Name.setPreferredSize(new Dimension(190, 50));
        Name.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Name);
        
        JMenuItem Date = new JMenuItem("Date");
        Date.setPreferredSize(new Dimension(190, 50));
        Date.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Date);
        
        JMenuItem Priority = new JMenuItem("Print");
        Priority.setPreferredSize(new Dimension(190, 50));
        Priority.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Priority);
        ///////////////////////////
        
        //Add Task Buttons to Bottom Panel
        bottomBar.add(add);
        bottomBar.add(edit);
        bottomBar.add(delete);
        topMenuBar.add(sortingButton);
        
        //Middle text area in the middle
        JTextArea ta = new JTextArea();
        
        //Selected Task panel area
        JPanel rightBackground = new JPanel();
        rightBackground.setBackground(Color.lightGray);
        rightBackground.setOpaque(true);
        rightBackground.setPreferredSize(new Dimension(900, 10));
        
        frame.add(BorderLayout.CENTER, ta);
        frame.add(BorderLayout.SOUTH, bottomBar);
        frame.add(BorderLayout.NORTH, topMenuBar);
        frame.add(BorderLayout.EAST, rightBackground);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
