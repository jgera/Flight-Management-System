package main;

import assist.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame
{
    private UpdateComboBox update;
    private Interface ui;
    	
	public Main()
	{
		update = new UpdateComboBox();
		
		ui = new Interface();
		
		this.getContentPane().add(ui);		
	}
	
	public static void main(String args[])
	{
		   //Get the System's look for the GUI
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	   
		Main frame = new Main();
		frame.setSize(600,440);
		frame.setResizable(false);
		frame.setTitle("航班订票管理信息系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}