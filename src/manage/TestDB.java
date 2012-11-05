package manage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class TestDB extends JFrame
{
	private JTabbedPane tab = new JTabbedPane();
	
	public TestDB()
	{
		tab.add(new InsertPanel(),"    插   入    ");
		tab.add(new DeletePanel(),"    删   除    ");
		tab.add(new UpdatePanel(),"    更   新    ");
		tab.add(new Show(),"  查 看 数 据 库  ");
		tab.setBorder(new MatteBorder(new ImageIcon("./images/f.gif")));
		getContentPane().add(tab);
		
		this.addWindowListener(new WindowAdapter()
	                          {
	                          	public void windowClosing(WindowEvent e)
	                          	{
	                          		TestDB.this.setVisible(false);
	                          		TestDB.this.dispose();
	                          	}
	                          }
	                      );	
		
	}
}