package todolist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ToDoList {
	
	static Task selectedTask = new Task();
	static TaskList taskList = new TaskList();
	char sortingMethod;
	
	public static void main(String[] args)
	{
		selectedTask.setDate("69 69 4200");
		selectedTask.setDesc("THIS IS JUST TEST DATA");
		selectedTask.setName("::TEST NAME::");
		selectedTask.setPriority(1);
		
		taskList.add(selectedTask);
		
		initializeHome();
		//showErrorMessage("fake error that is now super long and has a super long string to see if its cenetered");
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
		descTextField.setPreferredSize(new Dimension(635, 50));
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
		JTextField dateTextField = new JTextField("YYYY MM DD");
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
        		
        		taskList.add(newTask);
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
	
	static Task editPanel(Task task, int index)
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
		descTextField.setPreferredSize(new Dimension(635, 50));
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
        		
        		taskList.remove(index);
        		taskList.add(task);
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
	
	static void showErrorMessage(String msg)
	{
		JFrame errorFrame = new JFrame("ERROR");
		errorFrame.setSize(1100, 500);
		
		
		String errorText = ("Sorry, you have encountered an error:");
		JLabel errorLabel1 = new JLabel(errorText);
		errorLabel1.setPreferredSize(new Dimension(204, 60));
        errorLabel1.setFont(new Font("Arial", Font.PLAIN, 40));
        
		JLabel errorLabel = new JLabel(msg);
		errorLabel.setPreferredSize(new Dimension(204, 60));
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        
		errorFrame.add(BorderLayout.PAGE_START, errorLabel1);
		errorFrame.add(BorderLayout.CENTER, errorLabel);
		
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setVisible(true);
	}
	
	static void initializeHome()
	{
		//Creating the main Frame element
        JFrame frame = new JFrame("To-Do List Unlimited 2019");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Creating the Top Menu Bar and File Button
        JMenuBar topMenuBar = new JMenuBar();
        topMenuBar.setLayout( new FlowLayout(FlowLayout.LEFT, 20, 0) );
        
        //OPTIONS menu
        JMenu fileButton = new JMenu("OPTIONS");
        fileButton.setPreferredSize(new Dimension(204, 60));
        fileButton.setFont(new Font("Arial", Font.PLAIN, 40));
        
        //reset menu option
        JMenuItem newList = new JMenuItem("New");
        newList.setPreferredSize(new Dimension(200, 50));
        newList.setFont(new Font("Arial", Font.PLAIN, 30));
        fileButton.add(newList);
		newList.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                taskList.tasks.clear();
            }
        });
        
        //save menu option
        JMenuItem save = new JMenuItem("Save");
        save.setPreferredSize(new Dimension(200, 50));
        save.setFont(new Font("Arial", Font.PLAIN, 30));
        fileButton.addSeparator();
        fileButton.add(save);
        
        //print menu option
        JMenuItem print = new JMenuItem("Print");
        print.setPreferredSize(new Dimension(200, 50));
        print.setFont(new Font("Arial", Font.PLAIN, 30));
		print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                File file = new File("D:/data/report.txt");

                FileWriter wr;
                BufferedWriter bw;

                try {
                    int number = taskList.size();
                    wr = new FileWriter(file);
                    bw = new BufferedWriter(wr);

                    for(int i =0; i < number; i++) 
                    {
                        String out = taskList.get(i).toString();
                        bw.write(out);
                        bw.newLine();
                    }
                    bw.close();
                    wr.close();
                }
                catch (IOException e)
                {
                    System.err.println("There is something wrong, file coulnt create");
                    e.printStackTrace();
                }
            }
        });
        fileButton.addSeparator();
        fileButton.add(print);
        
        //sorting sub-menu option
        JMenu sortingButton = new JMenu("Sorting Type");
        sortingButton.setPreferredSize(new Dimension(200, 50));
        sortingButton.setFont(new Font("Arial", Font.PLAIN, 30));
        
        //name sorting menu option
        JMenuItem Name = new JMenuItem("Name");
        Name.setPreferredSize(new Dimension(130, 50));
        Name.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Name);
        
        //date sorting menu option
        JMenuItem Date = new JMenuItem("Date");
        Date.setPreferredSize(new Dimension(130, 50));
        Date.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Date);
        
        //priority sorting menu option
        JMenuItem Priority = new JMenuItem("Priority");
        Priority.setPreferredSize(new Dimension(130, 50));
        Priority.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Priority);
        
        //Add File Button to Top Menu Bar
        topMenuBar.add(fileButton);
        
        //Create Bottom bar and set its layout
        JPanel bottomBar = new JPanel();
        FlowLayout fLay = new FlowLayout(FlowLayout.LEADING, 95, 10);
        bottomBar.setLayout(fLay);
        
        //add button
        JButton add = new JButton("ADD");
        add.setPreferredSize(new Dimension(200, 50));
        add.setFont(new Font("Arial", Font.PLAIN, 30));
        add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedTask = addPanel();
        	}
        });
        
        //edit button
        JButton edit = new JButton("EDIT");
        edit.setPreferredSize(new Dimension(200, 50));
        edit.setFont(new Font("Arial", Font.PLAIN, 30));
        edit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selectedTask = editPanel(selectedTask, taskList.tasks.indexOf(selectedTask));
        	}
        });
        
        //delete button
        JButton delete = new JButton("DELETE");
        delete.setPreferredSize(new Dimension(200, 50));
        delete.setFont(new Font("Arial", Font.PLAIN, 30));
        edit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Delete code
        	}
        });
        
        //Add Task Buttons to Bottom Panel
        bottomBar.add(add);
        bottomBar.add(edit);
        bottomBar.add(delete);
        fileButton.addSeparator();
        fileButton.add(sortingButton);
        
        //Selected Task panel area
        JPanel rightBackground = new JPanel();
        rightBackground.setBackground(Color.lightGray);
        rightBackground.setOpaque(true);
        rightBackground.setPreferredSize(new Dimension(900, 10));
        
        JLabel taskName = new JLabel(selectedTask.getName());
        JLabel taskDate = new JLabel(selectedTask.getDate());
        JLabel taskPrio = new JLabel(String.valueOf(selectedTask.getPriority()));
        JLabel taskDesc = new JLabel(selectedTask.getDesc());
        
        rightBackground.add(taskName);
        rightBackground.add(taskDate);
        rightBackground.add(taskPrio);
        rightBackground.add(taskDesc);
        
        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        for(int i = 0; i < taskList.size(); i++)
        {
        	listModel.addElement(taskList.get(i).getName());
        }
        
        list = new JList(listModel);
        
        JScrollPane listScroller = new JScrollPane(list);
        list.setPreferredSize(new Dimension(200, 50));
        list.setFont(new Font("Arial", Font.PLAIN, 30));
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setViewportBorder(new LineBorder(Color.BLACK));
        listScroller.getViewport().add(list);
        
        frame.add(BorderLayout.CENTER, listScroller);
        frame.add(BorderLayout.SOUTH, bottomBar);
        frame.add(BorderLayout.NORTH, topMenuBar);
        frame.add(BorderLayout.EAST, rightBackground);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
