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

public class ToDoList {
	
	static JFrame frame;
	static JPanel leftPanel, rightPanel;
	static Task selectedTask = new Task();
	static TaskList taskList = new TaskList();
	
	char sortingMethod;
	public static void main(String[] args)
	{
		frame = initializeHome();
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
        		
        		refreshLeftPanel();
        		refreshRightPanel();
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
        		
        		refreshLeftPanel();
        		refreshRightPanel();
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
		
		
		JLabel errorLabel1 = new JLabel("Sorry, you have encountered an error:");
		errorLabel1.setFont(new Font("Arial", Font.PLAIN, 40));
        
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
		noticeFrame.setSize(1100, 500);
		
		
		JLabel noticeLabel1 = new JLabel("Notice:");
		noticeLabel1.setFont(new Font("Arial", Font.PLAIN, 40));
        
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
        		
        		taskList = taskList.sortName(taskList);
        		System.out.println("Sorted by name");
        		
        		if (taskList.size() != 0)
        			selectedTask = taskList.get(0);
        		else
        			selectedTask = new Task();
        		
        		refreshLeftPanel();
        		refreshRightPanel();
        		
        		//System.out.println(taskList.get(0).getName());
        	}
        });
        
        //date sorting menu option
        JMenuItem Date = new JMenuItem("Date");
        Date.setPreferredSize(new Dimension(130, 50));
        Date.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Date);
        Date.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//Sory by date code
        		
        	}
        });
        
        //priority sorting menu option
        JMenuItem Priority = new JMenuItem("Priority");
        Priority.setPreferredSize(new Dimension(130, 50));
        Priority.setFont(new Font("Arial", Font.PLAIN, 30));
        sortingButton.add(Priority);
        Priority.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//Sort by priority code

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
}
