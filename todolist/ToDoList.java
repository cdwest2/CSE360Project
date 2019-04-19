package todolist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList {

	public static void main(String[] args) 
	{
		TaskList list = new TaskList();
		
		initializeHome();
	}
	
	static void addPanel()
	{
		JFrame addFrame = new JFrame("ADD TASK");
		addFrame.setSize(1000, 600);
		addFrame.setLocationRelativeTo(null);
		addFrame.setVisible(true);
	}
	
	static void editPanel()
	{
		JFrame editFrame = new JFrame("EDIT TASK");
		editFrame.setSize(1000, 600);
		editFrame.setLocationRelativeTo(null);
		editFrame.setVisible(true);
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
        		addPanel();
        	}
        });
        
        JButton edit = new JButton("EDIT");
        edit.setPreferredSize(new Dimension(200, 50));
        edit.setFont(new Font("Arial", Font.PLAIN, 30));
        edit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editPanel();
        	}
        });
        
        JButton delete = new JButton("DELETE");
        delete.setPreferredSize(new Dimension(200, 50));
        delete.setFont(new Font("Arial", Font.PLAIN, 30));
        
        //Add Task Buttons to Bottom Panel
        bottomBar.add(add);
        bottomBar.add(edit);
        bottomBar.add(delete);
        
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
