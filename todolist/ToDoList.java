package todolist;
import javax.swing.*;
import java.awt.*;

public class ToDoList {

	public static void main(String[] args) 
	{
		TaskList list = new TaskList();
		
		//Creating the Frame
        JFrame frame = new JFrame("To-Do List Unlimited 2019");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        //Creating the MenuBar and adding components
        JMenuBar topMenuBar = new JMenuBar();
        
        JMenu fileButton = new JMenu("FILE");
        fileButton.setPreferredSize(new Dimension(200, 60));
        fileButton.setFont(new Font("Arial", Font.PLAIN, 40));
        
        JMenuItem save = new JMenuItem("Save");
        save.setPreferredSize(new Dimension(200, 50));
        save.setFont(new Font("Arial", Font.PLAIN, 30));
        
        JMenuItem restore = new JMenuItem("Restore");
        restore.setPreferredSize(new Dimension(200, 50));
        restore.setFont(new Font("Arial", Font.PLAIN, 30));
        
        JMenuItem print = new JMenuItem("Print");
        print.setPreferredSize(new Dimension(200, 50));
        print.setFont(new Font("Arial", Font.PLAIN, 30));
        
        topMenuBar.add(Box.createHorizontalGlue());
        topMenuBar.add(fileButton);
        fileButton.add(save);
        fileButton.add(restore);
        fileButton.add(print);
        
        JPanel bottomBar = new JPanel();
        JButton add = new JButton("ADD");
        JButton edit = new JButton("EDIT");
        JButton delete = new JButton("DELETE");
        
        bottomBar.add(add);
        bottomBar.add(edit);
        bottomBar.add(delete);
        
        JTextArea ta = new JTextArea();
        
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomBar);
        frame.getContentPane().add(BorderLayout.NORTH, topMenuBar);
        frame.setVisible(true);
	}

}
