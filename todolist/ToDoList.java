package todolist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class ToDoList {
	
	static JFrame frame;
	static JPanel leftPanel, rightPanel;
	static Task selectedTask = new Task();
	static TaskList taskList = new TaskList();
	static int status = 0;
	static char sortingMethod = 'n';
	
	public static void main(String[] args)
	{
		frame = initializeHome();
		try {
			read();
		} catch (IOException e) {
		}
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
		JTextField priorityTextField = new JTextField("0");
		priorityTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		priorityTextField.setPreferredSize(new Dimension(70, 50));
		priorityDatePanel.add(priority);
		priorityDatePanel.add(priorityTextField);
		
		JLabel date = new JLabel("  DUE DATE: ");
		date.setFont(new Font("Arial", Font.PLAIN, 40));
		JTextField dateTextField = new JTextField("MM DD YYYY");
		dateTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		dateTextField.setPreferredSize(new Dimension(395, 50));
		priorityDatePanel.add(date);
		priorityDatePanel.add(dateTextField);
		
		JButton addTaskButton = new JButton("Add Task");
		addTaskButton.setPreferredSize(new Dimension(900, 60));
        addTaskButton.setFont(new Font("Arial", Font.PLAIN, 40));
        addTaskButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		addFrame.dispatchEvent(new WindowEvent(addFrame, WindowEvent.WINDOW_CLOSING));
        		
        		String rawText = dateTextField.getText();
        		String textArr[] = rawText.split(" ");
        		String text = textArr[2] + " " + textArr[0] + " " + textArr[1];
        		newTask.setDate(text);
        		
        		text = nameTextField.getText();
        		newTask.setName(text);
        		
        		try
        		{
        		
	        		if(descNotUnique(descTextField.getText(), false, 0))
	        		{
	        			throw new ArithmeticException();
	        		}
	        		
	        		
        		
	        		text = descTextField.getText();
	        		newTask.setDesc(text);
        		
	        		int priority = Integer.parseInt(priorityTextField.getText());
	        		while(priorityNotUnique(priority, false, 0))
	        		{
	        			priority++;
	        		}
	        		newTask.setPriority(priority);
        		
	        		newTask.setStatus(0);
	        		
	        		taskList.add(newTask);
	        		
	        		refreshLeftPanel();
	        		refreshRightPanel();
        		}
        		catch(NumberFormatException nfe)
        		{
        			showErrorMessage("Please enter a valid integer for priority.");
        		}
        		catch(ArithmeticException aex)
        		{
        			showErrorMessage("Please enter a unique description.");
        		}
        		catch(Exception ex)
        		{
        			showErrorMessage("Something went wrong.");
        		}
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
		
		JMenuBar bar = new JMenuBar();
		JMenu statusButton = new JMenu("STATUS");
        statusButton.setPreferredSize(new Dimension(175, 50));
        statusButton.setFont(new Font("Arial", Font.PLAIN, 40));
        
        JMenuItem notStarted = new JMenuItem("Not Started");
        notStarted.setPreferredSize(new Dimension(175, 50));
        notStarted.setFont(new Font("Arial", Font.PLAIN, 30));
        statusButton.add(notStarted);
        notStarted.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                status = 0;
            }
        });
        
        //status menu options
        JMenuItem inProg = new JMenuItem("In Progress");
        inProg.setPreferredSize(new Dimension(175, 50));
        inProg.setFont(new Font("Arial", Font.PLAIN, 30));
        statusButton.add(inProg);
        inProg.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
                status = 1;
            }
        });
        

        JMenuItem complete = new JMenuItem("Completed");
        complete.setPreferredSize(new Dimension(175, 50));
        complete.setFont(new Font("Arial", Font.PLAIN, 30));
        statusButton.add(complete);
        complete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                status = 2;
            }
        });
        bar.add(statusButton);
		                              
		JPanel priorityStatusPanel = new JPanel();
		//priorityStatusPanel.setLayout(new BoxLayout(priorityStatusPanel, BoxLayout.X_AXIS));
		JLabel priority = new JLabel("PRIORITY: ");
		priority.setFont(new Font("Arial", Font.PLAIN, 40));
		String priorityString = Integer.toString(task.getPriority());
		JTextField priorityTextField = new JTextField(priorityString);
		priorityTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		priorityTextField.setPreferredSize(new Dimension(500, 50));
		priorityStatusPanel.add(priority);
		priorityStatusPanel.add(priorityTextField);
		priorityStatusPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		priorityStatusPanel.add(bar);
		
		JPanel datePanel = new JPanel();
		JLabel date = new JLabel("DUE DATE: ");
		date.setFont(new Font("Arial", Font.PLAIN, 40));
		String dateString = task.getDate();
		JTextField dateTextField = new JTextField(dateString);
		dateTextField.setFont(new Font("Arial", Font.PLAIN, 30));
		dateTextField.setPreferredSize(new Dimension(700, 50));
		datePanel.add(date);
		datePanel.add(dateTextField);
		
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
        		try
        		{
	        		if(descNotUnique(descTextField.getText(), true, index))
	        		{
	        			throw new ArithmeticException();
	        		}
        		
	        		text = descTextField.getText();
	    			task.setDesc(text);
	        		
	    			int priority = Integer.parseInt(priorityTextField.getText());
	        		while(priorityNotUnique(priority, true, index))
	        		{
	        			priority++;
	        		}
	        		task.setPriority(priority);
	        		
	        		task.setStatus(status);
	        		status = 0;
	        		
	        		taskList.remove(index);
	        		taskList.add(task);
	        		
	        		refreshLeftPanel();
	        		refreshRightPanel();
        		}
        		catch(NumberFormatException nfe)
        		{
        			showErrorMessage("Please enter a valid integer for priority.");
        			refreshLeftPanel();
	        		refreshRightPanel();
        		}
        		catch(ArithmeticException aex)
        		{
        			showErrorMessage("Please enter a unique description.");
        			refreshLeftPanel();
	        		refreshRightPanel();
        		}
        		catch(Exception ex)
        		{
        			showErrorMessage("Something went wrong.");
        			refreshLeftPanel();
	        		refreshRightPanel();
        		}
        	}
        });
		
		wholePan.add(Box.createRigidArea(new Dimension(1, 20)));
		wholePan.add(namePanel);
		wholePan.add(descPanel);
		wholePan.add(datePanel);
		wholePan.add(priorityStatusPanel);
		
		
		editFrame.add(wholePan);
		editFrame.add(BorderLayout.SOUTH, editTaskButton);
		editFrame.setLocationRelativeTo(null);
		editFrame.setVisible(true);
		
		return task;
	}
	
	static void showErrorMessage(String msg)
	{
		JFrame errorFrame = new JFrame("ERROR");
		errorFrame.setSize(800, 300);
		
		
		JLabel errorLabel1 = new JLabel("Sorry, you have encountered an error:");
		errorLabel1.setFont(new Font("Arial", Font.BOLD, 40));
        
		JLabel errorLabel = new JLabel(msg);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        
		errorFrame.add(BorderLayout.PAGE_START, errorLabel1);
		errorFrame.add(BorderLayout.CENTER, errorLabel);
		
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setVisible(true);
	}
	
	static void showNoticeMessage(String msg)
	{
		JFrame noticeFrame = new JFrame("NOTICE");
		noticeFrame.setSize(1100, 300);
		
		
		JLabel noticeLabel1 = new JLabel("Notice:");
		noticeLabel1.setFont(new Font("Arial", Font.BOLD, 40));
        
		JLabel noticeLabel = new JLabel(msg);
		noticeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        
		noticeFrame.add(BorderLayout.PAGE_START, noticeLabel1);
		noticeFrame.add(BorderLayout.CENTER, noticeLabel);
		
		noticeFrame.setLocationRelativeTo(null);
		noticeFrame.setVisible(true);
	}
	
	static JList getTaskListData()
	{
		DefaultListModel listModel = new DefaultListModel();
        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        switch (sortingMethod)
        {
        case 'n':
        {
        	taskList = taskList.sortName(taskList);
        	break;
        }
        case 'd':
        {
        	taskList = taskList.sortDate(taskList);
        	break;
        }
        case 'p':
        {
        	taskList = taskList.sortPriority(taskList);
        	break;
        }
        }
        
        
        for(int i = 0; i < taskList.size(); i++)
        {
        	listModel.addElement("  " + (i+1) + ") " + taskList.get(i).getName());
        }
        
        list = new JList(listModel);
        list.setFixedCellHeight(75);
        
        
        
        
        return list;
	}
	
	static void refreshLeftPanel()
	{
		leftPanel.removeAll();
		leftPanel.repaint();
		leftPanel.revalidate();
		
		leftPanel.add(updateLeftPanel());
		leftPanel.repaint();
		leftPanel.revalidate();
	}
	
	static JPanel updateLeftPanel()
	{
		JPanel leftBackground = new JPanel();
		leftBackground.setLayout(new BorderLayout());
		
		JList list = getTaskListData();
        
        list.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		if (e.getValueIsAdjusting())
        		{
        			selectedTask = taskList.get(list.getSelectedIndex());
        			refreshRightPanel();
        		}
        	}
        });
        
        JScrollPane listScroller = new JScrollPane(list);
        list.setPreferredSize(new Dimension(200, 50));
        list.setFont(new Font("Arial", Font.PLAIN, 35));
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroller.setViewportBorder(new LineBorder(Color.BLACK));
        listScroller.getViewport().add(list);
		
		leftBackground.add(listScroller, BorderLayout.CENTER);
		
		return leftBackground;
	}
	
	static void refreshRightPanel()
	{
		rightPanel.removeAll();
		rightPanel.repaint();
		rightPanel.revalidate();
		
		rightPanel.add(updateRightPanel());
		rightPanel.repaint();
		rightPanel.revalidate();
	}
	
	static JPanel updateRightPanel()
	{
		JPanel rightBackground = new JPanel();
        rightBackground.setBackground(Color.lightGray);
        rightBackground.setOpaque(true);
        rightBackground.setPreferredSize(new Dimension(900, 10));
        
        JLabel emptyList = new JLabel("Please enter a task to get started.");
        emptyList.setFont(new Font("Arial", Font.PLAIN, 35));
        
        JLabel taskName = new JLabel(selectedTask.getName());
        taskName.setFont(new Font("Arial", Font.BOLD, 45));
        JLabel taskDate = new JLabel("Date: " + selectedTask.getDate());
        taskDate.setFont(new Font("Arial", Font.PLAIN, 35));
        JLabel taskPrio = new JLabel("Priority: " + String.valueOf(selectedTask.getPriority()));
        taskPrio.setFont(new Font("Arial", Font.PLAIN, 35));
        JLabel taskDesc = new JLabel("Description: " + selectedTask.getDesc());
        taskDesc.setFont(new Font("Arial", Font.PLAIN, 35));
        
        JLabel taskStatus = new JLabel();
        switch(selectedTask.getStatus())
        {
        case 0:
        {
        	taskStatus = new JLabel("Status: Not Started");
        	break;
            
        }
        case 1:
        {
        	taskStatus = new JLabel("Status: In Progress");
        	break;
        }
        case 2:
        {
        	taskStatus = new JLabel("Status: Completed");
        	break;
        }
        }
        taskStatus.setFont(new Font("Arial", Font.PLAIN, 35));
        
        rightBackground.setLayout(new BoxLayout(rightBackground, BoxLayout.PAGE_AXIS));
        rightBackground.add(Box.createRigidArea(new Dimension(10, 20)));
        
        if (selectedTask.getName() == "")
        {
        	rightBackground.add(emptyList, BorderLayout.NORTH);
        }
        else
        {
            rightBackground.add(taskName);
        	rightBackground.add(Box.createRigidArea(new Dimension(10, 20)));
        	rightBackground.add(taskDesc);
        	rightBackground.add(Box.createRigidArea(new Dimension(10, 20)));
            rightBackground.add(taskPrio);
            rightBackground.add(Box.createRigidArea(new Dimension(10, 20)));
            rightBackground.add(taskDate);
            rightBackground.add(Box.createRigidArea(new Dimension(10, 20)));
            rightBackground.add(taskStatus);
        }
        
        
        return rightBackground;
	}
	
	static void print()
	{
		File file = new File("report.txt");

        FileWriter wr;
        BufferedWriter bw;

        try {
            int number = taskList.size();
            wr = new FileWriter(file);
            bw = new BufferedWriter(wr);

            for(int i =0; i < number; i++) 
            {
                String out = taskList.get(i).printString();
                bw.write(out);
                bw.newLine();
            }
            bw.close();
            wr.close();
            showNoticeMessage("Printable file 'report.txt' created in application folder.");
        }
        catch (IOException e)
        {
            System.err.println("There is something wrong, file couldn't create");
            e.printStackTrace();
            showErrorMessage("Creation of 'report.txt' unsuccesful.");
        }
	}
	
	static void read() throws IOException
	{
		Path path = Paths.get("save.bin");
		byte[] dataBin = Files.readAllBytes(path);
		String dataStr = new String(dataBin);
		String[] dataArr = dataStr.split("\n");
		Task tempTask;
		String tempName;
		String tempDesc;
		String tempDate;
		int tempStatus;
		int tempPriority;
		
		int numTasks = Integer.parseInt(dataArr[0]);
		for(int i = 1; i <= numTasks; i++)
		{
			tempName = dataArr[(i*5)-4];
			tempDesc = dataArr[(i*5)-3];
			tempStatus = Integer.parseInt(dataArr[(i*5)-1]);
			tempPriority = Integer.parseInt(dataArr[(i*5)-2]);
			tempDate = dataArr[i*5];
			tempTask = new Task(tempName, tempDesc, tempPriority, tempStatus, tempDate);
			taskList.add(tempTask);
		}
		selectedTask = taskList.get(0);
		refreshLeftPanel();
		refreshRightPanel();
	}
	static void save() throws IOException
	{
		String targetFile = "save.bin";
		try (OutputStream output = openFile(targetFile)) {
            output.write(getBytes((Integer.toString(taskList.size()) + "\n"))); 
        }
		for(int i = 0; i < taskList.size(); i++)
		{
			try (OutputStream output = openFile(targetFile, true)) {
	            output.write(getBytes(taskList.get(i).getName()));
	            output.write(getBytes("\n"));
	            output.write(getBytes(taskList.get(i).getDesc()));
	            output.write(getBytes("\n"));
	            output.write(getBytes(Integer.toString(taskList.get(i).getStatus())));
	            output.write(getBytes("\n"));
	            output.write(getBytes(Integer.toString(taskList.get(i).getPriority())));
	            output.write(getBytes("\n"));
	            output.write(getBytes(taskList.get(i).getDate())); 
	            output.write(getBytes("\n"));
	        }
		}
		showNoticeMessage("Binary file 'save.bin' updated in application folder.");
	}
	private static byte[] getBytes(String s)
	{
        return s.getBytes(StandardCharsets.UTF_8);
    }
 
    private static BufferedOutputStream openFile(String fileName) throws IOException 
    {
        return openFile(fileName, false);
    }
 
    private static BufferedOutputStream openFile(String fileName, boolean append) throws IOException 
    {
        return new BufferedOutputStream(new FileOutputStream(fileName, append));
    }
	
	static JFrame initializeHome()
	{
		//Creating the main Frame element
		JFrame frame = new JFrame ("ToDo List Unlimited 2019");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Creating the Top Menu Bar and File Button
        JMenuBar topMenuBar = new JMenuBar();
        
        //OPTIONS menu
        JMenu fileButton = new JMenu("OPTIONS");
        fileButton.setPreferredSize(new Dimension(204, 60));
        fileButton.setFont(new Font("Arial", Font.BOLD, 40));
        
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
                selectedTask = new Task();
                
                refreshLeftPanel();
                refreshRightPanel();
            }
        });
        
        //save menu option
        JMenuItem save = new JMenuItem("Save");
        save.setPreferredSize(new Dimension(200, 50));
        save.setFont(new Font("Arial", Font.PLAIN, 30));
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					save();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
        fileButton.addSeparator();
        fileButton.add(save);
        
        //print menu option
        JMenuItem print = new JMenuItem("Print");
        print.setPreferredSize(new Dimension(200, 50));
        print.setFont(new Font("Arial", Font.PLAIN, 30));
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                print();
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
        Name.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		sortingMethod = 'n';
        		System.out.println("Sorted by name");
        		
        		if (taskList.size() != 0)
        			selectedTask = taskList.get(0);
        		else
        			selectedTask = new Task();
        		
        		refreshLeftPanel();
        		refreshRightPanel();
        	}
        });
        
        //date sorting menu option
        JMenuItem Date = new JMenuItem("Date");
        Date.setPreferredSize(new Dimension(130, 50));
        Date.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Date);
        Date.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		sortingMethod = 'd';
        		System.out.println("Sorted by date");
        		
        		if (taskList.size() != 0)
        			selectedTask = taskList.get(0);
        		else
        			selectedTask = new Task();
        		
        		refreshLeftPanel();
        		refreshRightPanel();
        		
        	}
        });
        
        //priority sorting menu option
        JMenuItem Priority = new JMenuItem("Priority");
        Priority.setPreferredSize(new Dimension(130, 50));
        Priority.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Priority);
        Priority.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		sortingMethod = 'p';
        		System.out.println("Sorted by priority");
        		
        		if (taskList.size() != 0)
        			selectedTask = taskList.get(0);
        		else
        			selectedTask = new Task();
        		
        		refreshLeftPanel();
        		refreshRightPanel();

        	}
        });
        
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
        delete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		taskList.remove(taskList.tasks.indexOf(selectedTask));
        		
        		if (taskList.size() != 0)
        			selectedTask = taskList.get(0);
        		else
        			selectedTask = new Task();
        		
        		refreshLeftPanel();
        		refreshRightPanel();
        	}
        });
        
        
        
        //Add Task Buttons to Bottom Panel
        bottomBar.add(add);
        bottomBar.add(edit);
        bottomBar.add(delete);
        fileButton.addSeparator();
        fileButton.add(sortingButton);
        
        rightPanel = updateRightPanel();
        leftPanel = updateLeftPanel();
        
        
        frame.add(BorderLayout.CENTER, leftPanel);
        frame.add(BorderLayout.SOUTH, bottomBar);
        frame.add(BorderLayout.NORTH, topMenuBar);
        frame.add(BorderLayout.EAST, rightPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        return frame;
	}
	
	//Checks if a description given in the parameter is unique in the list. 
	static boolean descNotUnique(String description, boolean isEdit, int index)
	{
		boolean same = false;
		for(int i = 0; i < taskList.size(); i++)
		{
			if((taskList.get(i).getDesc()).contentEquals(description))
			{
				if(isEdit)
				{
					if(i != index)
					{
						same = true;
					}
				}
				else
				{
					same = true;
				}
			}
		}
		
		if(same)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	static boolean priorityNotUnique(int priority, boolean isEdit, int index)
	{
		boolean same = false;
		for(int i = 0; i < taskList.size(); i++)
		{
			if(taskList.get(i).getPriority() == priority)
			{
				if(isEdit)
				{
					if(i != index)
					{
						same = true;
					}
				}
				else
				{
					same = true;
				}
			}
		}
		
		if(same)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}