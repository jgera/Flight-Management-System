//:MyQuery.java
/**
 *The programme used to realized query function module for the "Plane Query and Ticket_Book System"
 *@author HaiXian Wu in Software College Grade 2003 Class 2  ID:1033710201
 *@version 1.0
 *Start Time:2004-12-21  
 *End Time  :2004-12-25
 */
package query; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

//查询面板
public class MyQuery extends JFrame implements ActionListener,WindowListener
{	
	public MyQuery()
	{				
		JTabbedPane jtp = new JTabbedPane();
		jtp.add(" 普 通 查 询 ",new CommonQuery());
		jtp.add(" 综 合 查 询",new ComprehenQuery());
		
		jtp.setBorder(new MatteBorder(new ImageIcon("./images/f.gif")));
		this.getContentPane().add(jtp);
		
		this.addWindowListener(new WindowAdapter()
		                          {
		                          	public void windowClosing(WindowEvent e)
		                          	{
		                          		MyQuery.this.setVisible(false);
		                          		MyQuery.this.dispose();
		                          	}
		                          }
		                      );		
	}
	
	public void actionPerformed(ActionEvent e)
	{   
	}
	
	   //Realize the abstract methods of the WindowListener
	    
	public void windowClosing(WindowEvent e)
	{	
	    //closeDataBase();	
	}
	
	   //When exit the program,the database should be closed 
	   //to avoid engross the resourse of the computer!
	/*public void closeDataBase()
	{
		FlightQuery.sqlConnection.CloseDataBase();
		AirFirmQuery.sqlConnection.CloseDataBase();
		DestinQuery.sqlConnection.CloseDataBase();
		ComprehenQuery.sqlConnection.CloseDataBase();
	}*/
	
	public void windowClosed(WindowEvent e)
	{		
	}
	
	public void windowOpened(WindowEvent e)
	{		
	}
	
	public void windowIconified(WindowEvent e)
	{		
	}
	
	public void windowDeiconified(WindowEvent e)
	{		
	}
	
	public void windowDeactivated(WindowEvent e)
	{		
	}
	
	public void windowActivated(WindowEvent e)
	{		
	}	

}